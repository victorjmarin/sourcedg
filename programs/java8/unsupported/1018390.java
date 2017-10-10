package org.eclipse.tptp.test.tools.web.ui.test.editor.form;

import java.util.List;
import org.eclipse.hyades.models.common.facades.behavioral.ITestCase;
import org.eclipse.hyades.models.common.testprofile.Common_TestprofilePackage;
import org.eclipse.hyades.test.http.runner.HttpExecutor;
import org.eclipse.hyades.test.http.runner.HttpRequest;
import org.eclipse.hyades.test.http.runner.HttpResponse;
import org.eclipse.hyades.test.tools.core.common.TestCommon;
import org.eclipse.hyades.test.tools.core.http.util.HttpConstants;
import org.eclipse.hyades.test.tools.core.http.util.RequestHelper;
import org.eclipse.hyades.test.tools.ui.common.internal.editor.TestCasesForm;
import org.eclipse.hyades.test.tools.ui.common.internal.editor.action.AddTestCase;
import org.eclipse.hyades.test.tools.ui.http.TestHttpImages;
import org.eclipse.hyades.test.tools.ui.http.internal.preferences.HttpPreferenceUtil;
import org.eclipse.hyades.test.tools.ui.internal.resources.ToolsUiPluginResourceBundle;
import org.eclipse.hyades.test.ui.editor.form.util.IDetailPage;
import org.eclipse.hyades.test.ui.editor.form.util.NamedElementSection;
import org.eclipse.hyades.test.ui.editor.form.util.WidgetFactory;
import org.eclipse.hyades.test.ui.internal.editor.form.base.FormWidgetFactory;
import org.eclipse.hyades.test.ui.internal.editor.form.util.DetailSection;
import org.eclipse.hyades.test.ui.internal.editor.form.util.EObjectTreeSection;
import org.eclipse.hyades.test.ui.internal.editor.form.util.StaticDetailPageProvider;
import org.eclipse.hyades.test.ui.internal.editor.form.util.TreeSection;
import org.eclipse.hyades.test.ui.internal.resources.UiPluginResourceBundle;
import org.eclipse.hyades.ui.internal.util.GridDataUtil;
import org.eclipse.hyades.ui.util.IDisposable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.tptp.test.tools.web.runner.util.WebRequestHelper;
import org.eclipse.tptp.test.tools.web.ui.editor.StaticUtils;
import org.eclipse.tptp.test.tools.web.ui.test.editor.WebTestEditorExtension;
import org.eclipse.tptp.test.tools.web.ui.test.editor.detailpage.WebTestCaseDetailPage;

/**
 * @author marcelop
 * @since 1.0.2
 */
public class WebTestCasesForm extends TestCasesForm {

    private TreeSection testCasesSection;

    private boolean firstActivation = true;

    private NamedElementSection namedElementSection;

    protected ITestCase activeTestCase;

    protected static class CheckTestCaseDialog extends Dialog implements Listener {

        protected HttpRequest request;

        private Text resultText;

        private Cursor cursor;

        private boolean started;

        public CheckTestCaseDialog(HttpRequest request) {
            super(Display.getCurrent().getActiveShell());
            this.request = request;
            this.started = false;
        }

        protected Control createDialogArea(Composite parent) {
            getShell().setText(ToolsUiPluginResourceBundle.W_TEST);
            Composite result = new Composite(parent, SWT.NULL);
            GridData gridData = GridDataUtil.createFill();
            gridData.heightHint = 250;
            gridData.widthHint = 400;
            result.setLayoutData(gridData);
            result.setLayout(new GridLayout());
            this.resultText = new Text(result, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
            this.resultText.setLayoutData(GridDataUtil.createFill());
            this.resultText.setEditable(false);
            getShell().addListener(SWT.Activate, this);
            return result;
        }

        protected void createButtonsForButtonBar(Composite parent) {
            createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
        }

        public void handleEvent(Event event) {
            execute();
        }

        protected void execute() {
            if (this.started) {
                return;
            }
            this.started = true;
            this.cursor = new Cursor(Display.getDefault(), SWT.CURSOR_WAIT);
            getShell().setCursor(this.cursor);
            this.resultText.setText(ToolsUiPluginResourceBundle.test_WaitMessage);
            Thread thread = new Thread("HyadesHttpRequestTester") {

                public void run() {
                    String checkerReturn = null;
                    try {
                        HttpResponse response = new HttpExecutor().execute(CheckTestCaseDialog.this.request);
                        checkerReturn = NLS.bind(ToolsUiPluginResourceBundle.test_Response, (new String[] { response.getCode() + "", response.getContentType(), "" + response.getContentLength() }));
                        if (response.getDetail() != null) {
                            checkerReturn = checkerReturn + "\n\n" + response.getDetail();
                        }
                        if (response.getBody() != null) {
                            checkerReturn = checkerReturn + "\n\n" + response.getBody();
                        }
                    } catch (Throwable t) {
                        checkerReturn = ToolsUiPluginResourceBundle.test_Exception + "\n" + org.eclipse.tptp.platform.common.internal.util.CoreUtil.getStackTrace(t);
                    }
                    final String cr = checkerReturn;
                    Display.getDefault().asyncExec(new Runnable() {

                        public void run() {
                            testFinished(cr);
                        }
                    });
                }
            };
            thread.start();
        }

        protected void testFinished(String text) {
            if ((getShell() == null) || this.resultText.isDisposed()) {
                return;
            }
            this.resultText.setText(text);
            getButton(IDialogConstants.CANCEL_ID).setText(IDialogConstants.OK_LABEL);
            getShell().setCursor(null);
            this.cursor.dispose();
        }
    }

    protected static class CheckTestCaseAction extends Action implements IDisposable {

        private WebRequestHelper requestHelper;

        /**
		 * Constructor for CheckTestCaseAction
		 */
        public CheckTestCaseAction() {
            super(ToolsUiPluginResourceBundle.W_TEST, TestHttpImages.INSTANCE.getImageDescriptor(TestHttpImages.IMG_CHECK));
        }

        /**
		 * @see org.eclipse.hyades.ui.util.IDisposable#dispose()
		 */
        public void dispose() {
            if (this.requestHelper != null) {
                this.requestHelper.dispose();
                this.requestHelper = null;
            }
        }

        public boolean isAvailable() {
            return ((this.requestHelper != null) && (this.requestHelper.getTestCase() != null));
        }

        public void setStructuredSelection(IStructuredSelection structuredSelection) {
            if (this.requestHelper != null) {
                this.requestHelper.dispose();
            }
            if ((structuredSelection != null) && (!structuredSelection.isEmpty())) {
                Object object = structuredSelection.getFirstElement();
                if (object instanceof ITestCase) {
                    setTestCase((ITestCase) object);
                }
            }
            setEnabled(isAvailable());
        }

        public void setTestCase(ITestCase testCase) {
            if (this.requestHelper == null) {
                this.requestHelper = new WebRequestHelper();
            } else {
                this.requestHelper.dispose();
            }
            this.requestHelper.setTestCase(testCase);
        }

        public ITestCase getTestCase() {
            if (this.requestHelper == null) {
                return null;
            }
            return this.requestHelper.getTestCase();
        }

        /**
		 * @see org.eclipse.jface.action.Action#run()
		 */
        public void run() {
            if (this.requestHelper == null) {
                return;
            }
            String value = this.requestHelper.getAttribute(RequestHelper.ATT_HOST);
            if (value == null) {
                openErrorMessage(ToolsUiPluginResourceBundle.Host_Is_Blank, null);
                return;
            }
            value = this.requestHelper.getAttribute(RequestHelper.ATT_ABS_PATH);
            if ((value == null) || (!value.startsWith("/"))) {
                openErrorMessage(ToolsUiPluginResourceBundle.Path_Needs_Slash, null);
                return;
            }
            HttpRequest request = this.requestHelper.createHttpRequest();
            if (request == null) {
                openErrorMessage(ToolsUiPluginResourceBundle.TEST_ERR_MSG, null);
                return;
            }
            CheckTestCaseDialog dialog = new CheckTestCaseDialog(request);
            dialog.open();
        }

        protected void openErrorMessage(String text, String detail) {
            if (detail != null) {
                text = text + " \n\n" + detail;
            }
            MessageDialog.openError(Display.getDefault().getActiveShell(), UiPluginResourceBundle.W_ERROR, text);
        }
    }

    private StaticDetailPageProvider detailPageProvider;

    /**
	 * Constructor for WebHttpTestCasesForm
	 * @param httpEditorExtension
	 * @param widgetFactory
	 */
    public WebTestCasesForm(WebTestEditorExtension httpEditorExtension, WidgetFactory widgetFactory) {
        super(httpEditorExtension, widgetFactory);
        setHeadingText("TPTP Web TEST");
    }

    /**
	 * @see org.eclipse.hyades.test.common.internal.editor.TestCasesForm#createLeftColumn(org.eclipse.swt.widgets.Composite)
	 */
    protected void createLeftColumn(Composite parent) {
        createTestCaseSection(parent);
        createNamedElementSection(parent);
    }

    /**
	 * @see org.eclipse.hyades.test.common.internal.editor.TestCasesForm#createRightColumn(org.eclipse.swt.widgets.Composite)
	 */
    protected void createRightColumn(Composite parent) {
        createDetailSection(parent);
    }

    /**
	 * @see org.eclipse.hyades.test.java.internal.junit.editor.TestCasesForm#getAddTestCaseAction()
	 */
    protected IAction getAddTestCaseAction() {
        return new AddTestCase(this, TestCommon.HTTP_JUNIT_TEST_CASE_TYPE, ToolsUiPluginResourceBundle.W_HTTP_REQUEST.toLowerCase()) {

            protected void adjusTestCase(ITestCase testCase) {
                WebRequestHelper requestHelper = new WebRequestHelper();
                requestHelper.setTestCase(testCase);
                requestHelper.setAttribute(RequestHelper.ATT_HOST, HttpPreferenceUtil.getInstance().getDefaultHostName());
                requestHelper.setAttribute(RequestHelper.ATT_PORT, HttpPreferenceUtil.getInstance().getDefaultHostPort());
                requestHelper.setAttribute(RequestHelper.ATT_ABS_PATH, HttpPreferenceUtil.getInstance().getDefaultAbsolutePath());
                requestHelper.setAttribute(RequestHelper.ATT_METHOD, HttpConstants.SUPPORTED_REQUEST_METHODS[0]);
                requestHelper.setAttribute(RequestHelper.ATT_VERSION, HttpConstants.HTTP_VERSION);
                requestHelper.setAttribute(RequestHelper.ATT_THINK_TIME, HttpPreferenceUtil.getInstance().getDefaultThinkTime());
            }
        };
    }

    /**
	 * @see org.eclipse.hyades.test.java.internal.junit.editor.TestCasesForm#getTestCasesSectionHeaderText()
	 */
    protected String getTestCasesSectionHeaderText() {
        return ToolsUiPluginResourceBundle.W_REQUESTS;
    }

    /**
	 * @see org.eclipse.hyades.test.common.internal.editor.TestCasesForm#registerHelp(int, java.lang.Object)
	 */
    protected void registerHelp(int flag, Object object) {
        switch(flag) {
            case HELP_NAMED_ELEMENT_CONTROL:
                break;
            case HELP_TEST_CASES_SECTION_CONTROL:
                break;
            case HELP_TEST_CASES_SECTION_TREE_CONTROL:
                break;
        }
    }

    /**
	 * @see org.eclipse.hyades.test.common.internal.editor.TestCasesForm#addDetails(org.eclipse.hyades.test.ui.internal.editor.form.util.DetailSection)
	 */
    protected void addDetails(StaticDetailPageProvider detailPageProvider) {
        this.detailPageProvider = detailPageProvider;
        detailPageProvider.addDetailPage(ITestCase.class, new WebTestCaseDetailPage());
    }

    protected void createDetailSection(Composite parent) {
        super.createDetailSection(parent);
        DetailSection detailSection = getDetailSection();
        if (detailSection != null && this.detailPageProvider != null) {
            IDetailPage detailPage = this.detailPageProvider.getDetailPage(ITestCase.class);
            Composite client = detailSection.getClient();
            if (!(client.getLayout() instanceof GridLayout)) {
                client.setLayout(new GridLayout());
            }
            Control pageControl = detailPage.createControl(client, getWidgetFactory(), detailSection);
            StaticUtils.removeNoDetailComp(detailSection.getClient());
            pageControl.setLayoutData(GridDataUtil.createFill());
            getDetailSection().addControlWithDetailPage(detailPage, pageControl);
        }
    }

    /**
	 * @see org.eclipse.hyades.test.common.internal.editor.TestCasesForm#createTestCasesSection()
	 */
    protected TreeSection createTestCasesSection() {
        return new EObjectTreeSection(this, Common_TestprofilePackage.eINSTANCE.getTPFTestSuite_TestCases(), getAddTestCaseAction()) {

            private CheckTestCaseAction checkTestCaseAction;

            protected void createActions() {
                super.createActions();
                this.checkTestCaseAction = new CheckTestCaseAction();
            }

            /**
			 * @see org.eclipse.hyades.test.ui.internal.editor.form.util.EObjectTreeSection#fillContextMenu(org.eclipse.jface.action.IMenuManager)
			 */
            protected void fillContextMenu(IMenuManager menuManager) {
                super.fillContextMenu(menuManager);
                menuManager.add(new Separator());
                menuManager.add(this.checkTestCaseAction);
                this.checkTestCaseAction.setStructuredSelection(getStructuredSelection());
            }
        };
    }

    public TreeSection getTestCasesSection() {
        return this.testCasesSection;
    }

    protected void createTestCaseSection(Composite parent) {
        this.testCasesSection = createTestCasesSection();
        registerSection(this.testCasesSection);
        this.testCasesSection.setHeaderText(getTestCasesSectionHeaderText());
        this.testCasesSection.setCollapsable(true);
        Control control = this.testCasesSection.createControl(parent, getWidgetFactory());
        control.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_BEGINNING));
        registerHelp(TestCasesForm.HELP_TEST_CASES_SECTION_CONTROL, control);
        registerHelp(TestCasesForm.HELP_TEST_CASES_SECTION_TREE_CONTROL, this.testCasesSection.getTreeViewer().getControl());
    }

    public boolean activated() {
        if (this.firstActivation) {
            this.firstActivation = false;
            this.testCasesSection.setFocus();
        }
        if (activeTestCase != null && activeTestCase != getSelectedTestCase()) {
            this.testCasesSection.getTreeViewer().setSelection(new StructuredSelection(activeTestCase));
        }
        return true;
    }

    public void dispose() {
        this.testCasesSection.getTreeViewer().removeSelectionChangedListener(this);
        this.testCasesSection.dispose();
        this.namedElementSection.dispose();
        if (super.getDetailSection() != null) {
            super.getDetailSection().dispose();
        }
    }

    public ISelection getSelection() {
        return this.testCasesSection.getTreeViewer().getSelection();
    }

    private ITestCase getSelectedTestCase() {
        ISelection selection = getSelection();
        if (!(selection instanceof IStructuredSelection)) return null;
        IStructuredSelection sel = (IStructuredSelection) selection;
        if (sel == null || sel.isEmpty()) {
            return null;
        }
        Object obj = sel.getFirstElement();
        if (obj != null && obj instanceof ITestCase) {
            return (ITestCase) obj;
        } else {
            return null;
        }
    }

    public void load() {
        this.testCasesSection.getTreeViewer().removeSelectionChangedListener(this);
        this.testCasesSection.setInput(getTestSuite());
        this.testCasesSection.getTreeViewer().addSelectionChangedListener(this);
        Tree tree = this.testCasesSection.getTreeViewer().getTree();
        if (tree.getItemCount() > 0) {
            selectReveal(new StructuredSelection(tree.getItem(0).getData()));
        }
    }

    /**
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
    public void selectionChanged(SelectionChangedEvent event) {
        if (event.getSource() == this.testCasesSection.getTreeViewer()) {
            IStructuredSelection structuredSelection = (IStructuredSelection) getSelection();
            this.namedElementSection.setInput(structuredSelection);
            setActiveTestCase(structuredSelection);
            if (getDetailSection() != null) {
                try {
                    getDetailSection().setInput(structuredSelection);
                } catch (SWTException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void setActiveTestCase(IStructuredSelection structuredSelection) {
        if (structuredSelection == null || structuredSelection.isEmpty()) {
            activeTestCase = null;
        }
        Object obj = structuredSelection.getFirstElement();
        if (obj != null && obj instanceof ITestCase) {
            activeTestCase = (ITestCase) obj;
        } else {
            activeTestCase = null;
        }
    }

    public void selectReveal(ISelection selection) {
        this.testCasesSection.selectReveal(selection);
    }

    protected void createNamedElementSection(Composite parent) {
        class MyNamedElementSection extends NamedElementSection {

            public MyNamedElementSection(TestCasesForm form) {
                super(form);
            }

            public Composite createClient(Composite parent, FormWidgetFactory formWidgetFactory) {
                Composite comp = super.createClient(parent, formWidgetFactory);
                StyledText text = getDescriptionTextField();
                text.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_BEGINNING | GridData.GRAB_VERTICAL));
                return comp;
            }
        }
        this.namedElementSection = new MyNamedElementSection(this);
        this.namedElementSection.setHeaderText(UiPluginResourceBundle.EDT_GENERAL_INFO);
        registerSection(this.namedElementSection);
        Control control = this.namedElementSection.createControl(parent, getWidgetFactory());
        GridData gridData = new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_BEGINNING);
        control.setLayoutData(gridData);
        registerHelp(TestCasesForm.HELP_NAMED_ELEMENT_CONTROL, control);
    }

    public ITestCase activateTestCase(int relPos, WebRequestHelper requestHelper, int pageToShow) {
        this.activeTestCase = requestHelper.moveToTestCase(relPos);
        return activeTestCase;
    }

    public ITestCase getActTestCase() {
        return activeTestCase;
    }
}
