package com.isa.jump.plugin;

import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Enumeration;
import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Deflater;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import com.vividsolutions.jump.util.FileUtil;
import com.vividsolutions.jump.util.java2xml.Java2XML;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jump.feature.AttributeType;
import com.vividsolutions.jump.feature.BasicFeature;
import com.vividsolutions.jump.feature.Feature;
import com.vividsolutions.jump.feature.FeatureCollectionWrapper;
import com.vividsolutions.jump.feature.FeatureDataset;
import com.vividsolutions.jump.feature.FeatureSchema;
import com.vividsolutions.jump.io.DriverProperties;
import com.vividsolutions.jump.io.FMEGMLWriter;
import com.vividsolutions.jump.io.GMLWriter;
import com.vividsolutions.jump.io.JMLWriter;
import com.vividsolutions.jump.io.ShapefileWriter;
import com.vividsolutions.jump.io.WKTWriter;
import com.vividsolutions.jump.io.datasource.DataSource;
import com.vividsolutions.jump.io.datasource.DataSourceQuery;
import com.vividsolutions.jump.io.datasource.StandardReaderWriterFileDataSource;
import com.vividsolutions.jump.workbench.WorkbenchContext;
import com.vividsolutions.jump.workbench.model.Layer;
import com.vividsolutions.jump.workbench.model.LayerManager;
import com.vividsolutions.jump.workbench.model.StandardCategoryNames;
import com.vividsolutions.jump.workbench.plugin.AbstractPlugIn;
import com.vividsolutions.jump.workbench.plugin.EnableCheckFactory;
import com.vividsolutions.jump.workbench.plugin.MultiEnableCheck;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;
import com.vividsolutions.jump.workbench.ui.images.IconLoader;
import com.vividsolutions.jump.workbench.ui.plugin.FeatureInstaller;
import com.vividsolutions.jump.workbench.ui.plugin.SaveProjectPlugIn;
import com.vividsolutions.jump.workbench.ui.plugin.SaveProjectAsPlugIn;
import com.vividsolutions.jump.workbench.ui.GUIUtil;

public class SaveDatasetsPlugIn extends AbstractPlugIn {

    private boolean saveAll = false;

    private int saveReadOnlySources = -1;

    private String pathToSaveReadOnlySources = "";

    private String extToSaveReadOnlySources = "";

    private int saveNewLayerSources = -1;

    private String pathToSaveNewLayerSources = "";

    private String extToSaveNewLayerSources = "";

    private static JFileChooser fileChooser;

    public void initialize(PlugInContext context) throws Exception {
        WorkbenchContext workbenchContext = context.getWorkbenchContext();
        FeatureInstaller featureInstaller = new FeatureInstaller(workbenchContext);
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save files from read-only sources");
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setMultiSelectionEnabled(false);
        GUIUtil.removeChoosableFileFilters(fileChooser);
        FileFilter fileFilter1 = GUIUtil.createFileFilter("SHP Files", new String[] { "shp" });
        fileChooser.addChoosableFileFilter(fileFilter1);
        FileFilter fileFilter2 = GUIUtil.createFileFilter("GML Files", new String[] { "gml" });
        fileChooser.addChoosableFileFilter(fileFilter2);
        FileFilter fileFilter3 = GUIUtil.createFileFilter("JML Files", new String[] { "jml" });
        fileChooser.addChoosableFileFilter(fileFilter3);
        FileFilter fileFilter4 = GUIUtil.createFileFilter("FME Files", new String[] { "fme" });
        fileChooser.addChoosableFileFilter(fileFilter4);
        FileFilter fileFilter5 = GUIUtil.createFileFilter("WKT Files", new String[] { "wkt" });
        fileChooser.addChoosableFileFilter(fileFilter5);
        fileChooser.setFileFilter(fileFilter1);
        JPopupMenu layerNamePopupMenu = workbenchContext.getWorkbench().getFrame().getLayerNamePopupMenu();
        featureInstaller.addPopupMenuItem(layerNamePopupMenu, this, "Save Selected Datasets", false, ICON, SaveDatasetsPlugIn.createEnableCheck(workbenchContext));
    }

    public static final ImageIcon ICON = IconLoader.icon("disk_multiple.png");

    private boolean noSource(Layer layer) {
        DataSourceQuery dsq = layer.getDataSourceQuery();
        if (dsq == null) return true;
        DataSource ds = dsq.getDataSource();
        if (ds == null) return true;
        if (ds.getProperties().get("File") == null) return true;
        return false;
    }

    private boolean isWritable(Layer layer) {
        DataSourceQuery dsq = layer.getDataSourceQuery();
        if (dsq != null) return dsq.getDataSource().isWritable();
        return true;
    }

    public boolean execute(PlugInContext context) throws Exception {
        WorkbenchContext workbenchContext = context.getWorkbenchContext();
        try {
            boolean writeWarning = false;
            String newLine = "";
            context.getWorkbenchFrame().getOutputFrame().createNewDocument();
            LayerManager layerManager = context.getLayerManager();
            Collection allLayers = layerManager.getLayers();
            Collection initialLayersToSave = (Collection) context.getWorkbenchContext().getLayerNamePanel().selectedNodes(Layer.class);
            if (saveAll) initialLayersToSave = layerManager.getLayers();
            List layersToSave = new ArrayList();
            for (Iterator i = initialLayersToSave.iterator(); i.hasNext(); ) {
                Layer layer = (Layer) i.next();
                if ((layer.isFeatureCollectionModified()) || (!isWritable(layer) || (noSource(layer)))) {
                    layersToSave.add(layer);
                }
            }
            for (int i = layersToSave.size() - 1; i >= 0; i--) {
                Layer layer = (Layer) layersToSave.get(i);
                if (layer.getFeatureCollectionWrapper().getFeatures().isEmpty()) {
                    context.getWorkbenchFrame().getOutputFrame().addText("Empty layer not saved: " + layer.getName());
                    writeWarning = true;
                    layersToSave.remove(i);
                    newLine = "\n";
                }
            }
            saveNewLayerSources = -1;
            for (int i = layersToSave.size() - 1; i >= 0; i--) {
                Layer layer = (Layer) layersToSave.get(i);
                if (noSource(layer)) {
                    if (saveNewLayerSources == -1) {
                        int response = JOptionPane.showConfirmDialog(workbenchContext.getLayerViewPanel(), "Do you want to save the new layers?\n(Note: layer name will be filename)", "JUMP", JOptionPane.YES_NO_OPTION);
                        saveNewLayerSources = 0;
                        if (response == JOptionPane.YES_OPTION) {
                            if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(workbenchContext.getLayerViewPanel())) {
                                File file = fileChooser.getSelectedFile();
                                String ext = FileUtil.getExtension(file);
                                if (ext.isEmpty()) ext = "shp";
                                pathToSaveNewLayerSources = file.getParent() + File.separator;
                                extToSaveNewLayerSources = "." + ext;
                                saveNewLayerSources = 1;
                            }
                        }
                    }
                    if (saveNewLayerSources == 0) {
                        context.getWorkbenchFrame().getOutputFrame().addText("Did not save new layer: " + layer.getName());
                        writeWarning = true;
                        layersToSave.remove(i);
                        newLine = "\n";
                    }
                }
            }
            if (saveAll) {
                for (int i = layersToSave.size() - 1; i >= 0; i--) {
                    Layer layer = (Layer) layersToSave.get(i);
                    if (noSource(layer)) continue;
                    if (!isWritable(layer) && !layer.isFeatureCollectionModified()) {
                        layersToSave.remove(i);
                    }
                }
            }
            saveReadOnlySources = -1;
            for (int i = layersToSave.size() - 1; i >= 0; i--) {
                Layer layer = (Layer) layersToSave.get(i);
                if (noSource(layer)) continue;
                if (!isWritable(layer)) {
                    if (saveReadOnlySources == -1) {
                        String prompt = "Do you want to save the read-only layers?\n(Note: layer name will be filename)";
                        if (saveAll) prompt = "Do you want to save the modified read-only layers?\n(Note: layer name will be filename)";
                        int response = JOptionPane.showConfirmDialog(workbenchContext.getLayerViewPanel(), prompt, "JUMP", JOptionPane.YES_NO_OPTION);
                        saveReadOnlySources = 0;
                        if (response == JOptionPane.YES_OPTION) {
                            if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(workbenchContext.getLayerViewPanel())) {
                                File file = fileChooser.getSelectedFile();
                                String ext = FileUtil.getExtension(file);
                                if (ext.isEmpty()) ext = "shp";
                                pathToSaveReadOnlySources = file.getParent() + File.separator;
                                extToSaveReadOnlySources = "." + ext;
                                saveReadOnlySources = 1;
                            }
                        }
                    }
                    if (saveReadOnlySources == 0) {
                        context.getWorkbenchFrame().getOutputFrame().addText(newLine + "Did not save read-only layer: " + layer.getName());
                        writeWarning = true;
                        layersToSave.remove(i);
                    }
                }
            }
            for (int i = layersToSave.size() - 1; i >= 0; i--) {
                Layer saveLayer = (Layer) layersToSave.get(i);
                String destToSave = getDestinationFileName(saveLayer);
                for (Iterator j = allLayers.iterator(); j.hasNext(); ) {
                    Layer currLayer = (Layer) j.next();
                    if (saveLayer == currLayer) continue;
                    String currDest = getDestinationFileName(currLayer);
                    if (destToSave.equalsIgnoreCase(currDest)) {
                        context.getWorkbenchFrame().getOutputFrame().addText("Did not save layer " + saveLayer.getName() + " to " + destToSave + " since this would write over the source for layer " + currLayer.getName());
                        layersToSave.remove(i);
                        writeWarning = true;
                        break;
                    }
                }
            }
            String replacedFilesNewLayer = "";
            int numReplacedNewLayer = 0;
            String replacedFilesReadOnlyLayer = "";
            int numReplacedReadOnlyLayer = 0;
            for (int i = 0; i < layersToSave.size(); i++) {
                String destinationFile = "";
                Layer layer = (Layer) layersToSave.get(i);
                destinationFile = getDestinationFileName(layer);
                if (new File(destinationFile).exists()) {
                    if (noSource(layer)) {
                        numReplacedNewLayer++;
                        replacedFilesNewLayer = replacedFilesNewLayer + layer.getName() + " --> " + destinationFile + "\n";
                    } else if (!isWritable(layer)) {
                        numReplacedReadOnlyLayer++;
                        replacedFilesReadOnlyLayer = replacedFilesReadOnlyLayer + layer.getName() + " --> " + destinationFile + "\n";
                    }
                }
            }
            if (numReplacedNewLayer >= 1) {
                String prompt = numReplacedNewLayer + " new layer will replace an existing file?\n(Note: Output window will display the results of this command.)";
                if (numReplacedNewLayer > 1) prompt = numReplacedNewLayer + " new layers will replace existing files?\n(Note: Output window will display the results of this command.)";
                int response = JOptionPane.showConfirmDialog(workbenchContext.getLayerViewPanel(), prompt, "JUMP", JOptionPane.OK_CANCEL_OPTION);
                if (response == JOptionPane.CANCEL_OPTION) {
                    if (numReplacedNewLayer == 1) context.getWorkbenchFrame().getOutputFrame().addText("The new layer would have replaced the following file:"); else context.getWorkbenchFrame().getOutputFrame().addText("The new layers would have replaced the following files:");
                    context.getWorkbenchFrame().getOutputFrame().addText(replacedFilesNewLayer);
                    writeWarning = true;
                    return true;
                }
                if (numReplacedNewLayer == 1) context.getWorkbenchFrame().getOutputFrame().addText("The new layer has replaced the following file:"); else context.getWorkbenchFrame().getOutputFrame().addText("The new layers have replaced the following files:");
                context.getWorkbenchFrame().getOutputFrame().addText(replacedFilesNewLayer);
            }
            if (numReplacedReadOnlyLayer >= 1) {
                String prompt = numReplacedReadOnlyLayer + " read-only source will replace an existing file?\n(Note: Output window will display the results of this command.)";
                if (numReplacedReadOnlyLayer > 1) prompt = numReplacedReadOnlyLayer + " read-only sources will replace existing files?\n(Note: Output window will display the results of this command.)";
                int response = JOptionPane.showConfirmDialog(workbenchContext.getLayerViewPanel(), prompt, "JUMP", JOptionPane.OK_CANCEL_OPTION);
                if (response == JOptionPane.CANCEL_OPTION) {
                    if (numReplacedReadOnlyLayer == 1) context.getWorkbenchFrame().getOutputFrame().addText("The read-only layer would have replaced the following file:"); else context.getWorkbenchFrame().getOutputFrame().addText("The read-only layers would have replaced the following files:");
                    context.getWorkbenchFrame().getOutputFrame().addText(replacedFilesReadOnlyLayer);
                    writeWarning = true;
                    return true;
                }
                if (numReplacedReadOnlyLayer == 1) context.getWorkbenchFrame().getOutputFrame().addText("The read-only layer has replaced the following file:"); else context.getWorkbenchFrame().getOutputFrame().addText("The read-only layers have replaced the following files:");
                context.getWorkbenchFrame().getOutputFrame().addText(replacedFilesReadOnlyLayer);
            }
            for (int i = 0; i < layersToSave.size(); i++) {
                Layer layer = (Layer) layersToSave.get(i);
                if (WriteLayer(context, layer)) {
                    layer.setFeatureCollectionModified(false);
                    context.getWorkbenchFrame().getOutputFrame().addText("Saved layer: " + layer.getName());
                } else {
                    context.getWorkbenchFrame().getOutputFrame().addText("Could not save layer: " + layer.getName());
                }
            }
            for (Iterator i = allLayers.iterator(); i.hasNext(); ) {
                Layer layer = (Layer) i.next();
                writeProjectionFile(context, layer);
            }
            if (saveAll) {
                if (context.getTask().getProjectFile() != null) {
                    new SaveProjectPlugIn(new SaveProjectAsPlugIn()).execute(context);
                    context.getWorkbenchFrame().getOutputFrame().addText("\nSaved task: " + context.getTask().getProjectFile().getName());
                }
            }
            if (writeWarning) context.getWorkbenchFrame().warnUser("Warning: unsaved data - see output window");
            return true;
        } catch (Exception e) {
            context.getWorkbenchFrame().warnUser("Error: see output window");
            context.getWorkbenchFrame().getOutputFrame().addText("SaveDatasetsPlugIn Exception:" + e.toString());
            return false;
        }
    }

    public static MultiEnableCheck createEnableCheck(WorkbenchContext workbenchContext) {
        EnableCheckFactory checkFactory = new EnableCheckFactory(workbenchContext);
        return new MultiEnableCheck().add(checkFactory.createWindowWithSelectionManagerMustBeActiveCheck()).add(checkFactory.createAtLeastNLayersMustBeSelectedCheck(1));
    }

    public void setSaveAll() {
        saveAll = true;
    }

    private void setDataSourceQuery(Layer layer, String filename) throws Exception {
        DataSource dataSource = null;
        if ((filename.toLowerCase()).endsWith(".shp")) dataSource = (DataSource) StandardReaderWriterFileDataSource.Shapefile.class.newInstance();
        if ((filename.toLowerCase()).endsWith(".jml")) dataSource = (DataSource) StandardReaderWriterFileDataSource.JML.class.newInstance();
        if ((filename.toLowerCase()).endsWith(".gml")) dataSource = (DataSource) StandardReaderWriterFileDataSource.GML.class.newInstance();
        if ((filename.toLowerCase()).endsWith(".fme")) dataSource = (DataSource) StandardReaderWriterFileDataSource.FMEGML.class.newInstance();
        if ((filename.toLowerCase()).endsWith(".wkt")) dataSource = (DataSource) StandardReaderWriterFileDataSource.WKT.class.newInstance();
        if (dataSource != null) {
            HashMap properties = new HashMap();
            properties.put(DataSource.COORDINATE_SYSTEM_KEY, "Unspecified");
            properties.put(DataSource.FILE_KEY, filename);
            dataSource.setProperties(properties);
            DataSourceQuery dataSourceQuery = new DataSourceQuery(dataSource, layer.getName(), null);
            layer.setDataSourceQuery(dataSourceQuery).setFeatureCollectionModified(false);
        }
    }

    private boolean WriteLayer(PlugInContext context, Layer layer) {
        String filename = "";
        try {
            if (noSource(layer)) {
                filename = pathToSaveNewLayerSources + normalizeName(layer.getName()) + extToSaveNewLayerSources;
                setDataSourceQuery(layer, filename);
            } else {
                DataSource ds = layer.getDataSourceQuery().getDataSource();
                if (!ds.isWritable()) {
                    filename = pathToSaveReadOnlySources + normalizeName(layer.getName()) + extToSaveReadOnlySources;
                } else {
                    filename = ds.getProperties().get("File").toString();
                }
            }
            DriverProperties dp = new DriverProperties();
            dp.set("File", filename);
            if ((filename.toLowerCase()).endsWith(".shp")) {
                String path = new File(filename).getParent() + File.separator;
                List newLayers = new ArrayList();
                if (!CompatibleFeatures(layer)) newLayers = splitLayer(context, layer);
                (new ShapefileWriter()).write(layer.getFeatureCollectionWrapper(), dp);
                SaveToArchive(layer, filename);
                for (int i = 0; i < newLayers.size(); i++) {
                    Layer newLayer = (Layer) newLayers.get(i);
                    String newFileName = path + normalizeName(newLayer.getName()) + ".shp";
                    setDataSourceQuery(newLayer, newFileName);
                    dp.set("File", newFileName);
                    (new ShapefileWriter()).write(newLayer.getFeatureCollectionWrapper(), dp);
                    context.getWorkbenchFrame().getOutputFrame().addText("Saved layer: " + newLayer.getName());
                }
                return true;
            }
            if ((filename.toLowerCase()).endsWith(".jml")) {
                (new JMLWriter()).write(layer.getFeatureCollectionWrapper(), dp);
                SaveToArchive(layer, filename);
                return true;
            }
            if ((filename.toLowerCase()).endsWith(".gml")) {
                (new GMLWriter()).write(layer.getFeatureCollectionWrapper(), dp);
                SaveToArchive(layer, filename);
                return true;
            }
            if ((filename.toLowerCase()).endsWith(".fme")) {
                (new FMEGMLWriter()).write(layer.getFeatureCollectionWrapper(), dp);
                SaveToArchive(layer, filename);
                return true;
            }
            if ((filename.toLowerCase()).endsWith(".wkt")) {
                (new WKTWriter()).write(layer.getFeatureCollectionWrapper(), dp);
                SaveToArchive(layer, filename);
                return true;
            }
            context.getWorkbenchFrame().getOutputFrame().addText("Unrecognized file type - could not save layer: " + layer.getName());
            context.getWorkbenchFrame().warnUser("Warning: see output window");
            return false;
        } catch (Exception e) {
            context.getWorkbenchFrame().warnUser("Error: see output window");
            context.getWorkbenchFrame().getOutputFrame().createNewDocument();
            context.getWorkbenchFrame().getOutputFrame().addText("SaveDatasetsPlugIn:WriteLayer Exception:" + e.toString());
            return false;
        }
    }

    private void copy(InputStream in, OutputStream out) throws IOException {
        byte buffer[] = new byte[1024];
        int noBytes;
        while ((noBytes = in.read(buffer)) != -1) {
            out.write(buffer, 0, noBytes);
        }
    }

    private void SaveToArchive(Layer layer, String layerFileName) throws Exception {
        Object archiveObj = layer.getBlackboard().get("ArchiveFileName");
        Object entryObj = layer.getBlackboard().get("ArchiveEntryPrefix");
        if ((archiveObj == null) || (entryObj == null)) return;
        String archiveName = archiveObj.toString();
        String entryPrefix = entryObj.toString();
        if ((archiveName == "") || (entryPrefix == "")) return;
        File tempZip = File.createTempFile("tmp", ".zip");
        InputStream in = new BufferedInputStream(new FileInputStream(archiveName));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(tempZip));
        copy(in, out);
        in.close();
        out.close();
        ZipFile zipFile = new ZipFile(tempZip);
        ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(archiveName)));
        ZipInputStream zin = new ZipInputStream(new FileInputStream(tempZip));
        ZipEntry entry = zin.getNextEntry();
        while (entry != null) {
            String entryName = entry.getName();
            String en = GUIUtil.nameWithoutExtension(new File(entryName));
            if (en.equalsIgnoreCase(entryPrefix)) {
                if (entryName.endsWith(".jmp")) {
                    String layerTaskPath = CreateArchivePlugIn.createLayerTask(layer, archiveName, entryPrefix);
                    CreateArchivePlugIn.WriteZipEntry(layerTaskPath, entryPrefix, zout);
                } else if ((!entryName.endsWith(".shx")) && (!entryName.endsWith(".dbf")) && (!entryName.endsWith(".shp.xml")) && (!entryName.endsWith(".prj"))) {
                    CreateArchivePlugIn.WriteZipEntry(layerFileName, entryPrefix, zout);
                }
            } else {
                zout.putNextEntry(entry);
                copy(zin, zout);
            }
            entry = zin.getNextEntry();
        }
        zin.close();
        zout.close();
        zipFile.close();
        tempZip.delete();
    }

    private boolean CompatibleFeatures(Layer layer) {
        BitSet bitSet = new BitSet();
        FeatureCollectionWrapper featureCollection = layer.getFeatureCollectionWrapper();
        List featureList = featureCollection.getFeatures();
        for (Iterator i = featureList.iterator(); i.hasNext(); ) bitSet = GeoUtils.setBit(bitSet, ((Feature) i.next()).getGeometry());
        return (bitSet.cardinality() < 2);
    }

    private List splitLayer(PlugInContext context, Layer layer) {
        ArrayList newLayers = new ArrayList();
        if (!CompatibleFeatures(layer)) {
            ArrayList emptyFeatures = new ArrayList();
            ArrayList pointFeatures = new ArrayList();
            ArrayList lineFeatures = new ArrayList();
            ArrayList polyFeatures = new ArrayList();
            ArrayList groupFeatures = new ArrayList();
            FeatureCollectionWrapper featureCollection = layer.getFeatureCollectionWrapper();
            List featureList = featureCollection.getFeatures();
            FeatureSchema featureSchema = layer.getFeatureCollectionWrapper().getFeatureSchema();
            for (Iterator i = featureList.iterator(); i.hasNext(); ) {
                Feature feature = (Feature) i.next();
                Geometry geometry = feature.getGeometry();
                if (geometry.isEmpty()) emptyFeatures.add(feature); else if ((geometry instanceof GeometryCollection) && (!(geometry instanceof MultiPoint)) && (!(geometry instanceof MultiLineString)) && (!(geometry instanceof MultiPolygon))) groupFeatures.add(feature);
            }
            for (int i = 0; i < emptyFeatures.size(); i++) {
                featureCollection.remove((Feature) emptyFeatures.get(i));
            }
            for (int i = 0; i < groupFeatures.size(); i++) {
                Feature feature = (Feature) groupFeatures.get(i);
                GeometryCollection geometry = (GeometryCollection) feature.getGeometry();
                explodeGeometryCollection(featureSchema, pointFeatures, lineFeatures, polyFeatures, geometry, feature);
                featureCollection.remove(feature);
            }
            featureCollection = layer.getFeatureCollectionWrapper();
            featureList = layer.getFeatureCollectionWrapper().getFeatures();
            BitSet layerBit = new BitSet();
            if (featureList.size() > 0) {
                Geometry firstGeo = ((Feature) featureList.iterator().next()).getGeometry();
                layerBit = GeoUtils.setBit(layerBit, firstGeo);
            }
            if (layerBit.get(GeoUtils.polyBit)) {
                if (polyFeatures.size() > 0) {
                    for (int i = 0; i < polyFeatures.size(); i++) {
                        Feature feature = (Feature) polyFeatures.get(i);
                        featureCollection.add(feature);
                    }
                    polyFeatures.clear();
                }
            } else if (layerBit.get(GeoUtils.lineBit)) {
                if (lineFeatures.size() > 0) {
                    for (int i = 0; i < lineFeatures.size(); i++) {
                        Feature feature = (Feature) lineFeatures.get(i);
                        featureCollection.add(feature);
                    }
                    lineFeatures.clear();
                }
            } else if (layerBit.get(GeoUtils.pointBit)) {
                if (pointFeatures.size() > 0) {
                    for (int i = 0; i < pointFeatures.size(); i++) {
                        Feature feature = (Feature) pointFeatures.get(i);
                        featureCollection.add(feature);
                    }
                    pointFeatures.clear();
                }
            } else {
                if (polyFeatures.size() > 0) {
                    for (int i = 0; i < polyFeatures.size(); i++) {
                        Feature feature = (Feature) polyFeatures.get(i);
                        featureCollection.add(feature);
                    }
                    polyFeatures.clear();
                } else if (lineFeatures.size() > 0) {
                    for (int i = 0; i < lineFeatures.size(); i++) {
                        Feature feature = (Feature) lineFeatures.get(i);
                        featureCollection.add(feature);
                    }
                    lineFeatures.clear();
                } else if (pointFeatures.size() > 0) {
                    for (int i = 0; i < pointFeatures.size(); i++) {
                        Feature feature = (Feature) pointFeatures.get(i);
                        featureCollection.add(feature);
                    }
                    pointFeatures.clear();
                }
            }
            featureCollection = layer.getFeatureCollectionWrapper();
            featureList = layer.getFeatureCollectionWrapper().getFeatures();
            layerBit = new BitSet();
            if (featureList.size() > 0) {
                Geometry firstGeo = ((Feature) featureList.iterator().next()).getGeometry();
                layerBit = GeoUtils.setBit(layerBit, firstGeo);
            }
            Collection selectedCategories = context.getLayerNamePanel().getSelectedCategories();
            for (Iterator i = featureList.iterator(); i.hasNext(); ) {
                Feature feature = (Feature) i.next();
                Geometry geo = feature.getGeometry();
                BitSet currFeatureBit = new BitSet();
                currFeatureBit = GeoUtils.setBit(currFeatureBit, geo);
                if (!layerBit.get(GeoUtils.pointBit) && currFeatureBit.get(GeoUtils.pointBit)) pointFeatures.add(feature);
                if (!layerBit.get(GeoUtils.lineBit) && currFeatureBit.get(GeoUtils.lineBit)) lineFeatures.add(feature);
                if (!layerBit.get(GeoUtils.polyBit) && currFeatureBit.get(GeoUtils.polyBit)) polyFeatures.add(feature);
            }
            if (pointFeatures.size() > 0) {
                Layer pointLayer = context.addLayer(selectedCategories.isEmpty() ? StandardCategoryNames.WORKING : selectedCategories.iterator().next().toString(), layer.getName() + "_point", new FeatureDataset(featureSchema));
                FeatureCollectionWrapper pointFeatureCollection = pointLayer.getFeatureCollectionWrapper();
                newLayers.add(pointLayer);
                context.getWorkbenchFrame().getOutputFrame().addText("Created new layer " + pointLayer.getName());
                context.getWorkbenchFrame().warnUser("Warning: new layers created - see output window");
                for (int i = 0; i < pointFeatures.size(); i++) {
                    Feature feature = (Feature) pointFeatures.get(i);
                    featureCollection.remove(feature);
                    pointFeatureCollection.add(feature);
                }
            }
            if (lineFeatures.size() > 0) {
                Layer lineLayer = context.addLayer(selectedCategories.isEmpty() ? StandardCategoryNames.WORKING : selectedCategories.iterator().next().toString(), layer.getName() + "_line", new FeatureDataset(featureSchema));
                FeatureCollectionWrapper lineFeatureCollection = lineLayer.getFeatureCollectionWrapper();
                newLayers.add(lineLayer);
                context.getWorkbenchFrame().getOutputFrame().addText("Created new layer " + lineLayer.getName());
                context.getWorkbenchFrame().warnUser("Warning: new layers created - see output window");
                for (int i = 0; i < lineFeatures.size(); i++) {
                    Feature feature = (Feature) lineFeatures.get(i);
                    featureCollection.remove(feature);
                    lineFeatureCollection.add(feature);
                }
            }
            if (polyFeatures.size() > 0) {
                Layer polyLayer = context.addLayer(selectedCategories.isEmpty() ? StandardCategoryNames.WORKING : selectedCategories.iterator().next().toString(), layer.getName() + "_area", new FeatureDataset(featureSchema));
                FeatureCollectionWrapper polyFeatureCollection = polyLayer.getFeatureCollectionWrapper();
                newLayers.add(polyLayer);
                context.getWorkbenchFrame().getOutputFrame().addText("Created new layer " + polyLayer.getName());
                context.getWorkbenchFrame().warnUser("Warning: new layers created - see output window");
                for (int i = 0; i < polyFeatures.size(); i++) {
                    Feature feature = (Feature) polyFeatures.get(i);
                    featureCollection.remove(feature);
                    polyFeatureCollection.add(feature);
                }
            }
        }
        return newLayers;
    }

    private void explodeGeometryCollection(FeatureSchema fs, ArrayList pointFeatures, ArrayList lineFeatures, ArrayList polyFeatures, GeometryCollection geometryCollection, Feature feature) {
        for (int i = 0; i < geometryCollection.getNumGeometries(); i++) {
            Geometry geometry = geometryCollection.getGeometryN(i);
            if (geometry instanceof GeometryCollection) {
                explodeGeometryCollection(fs, pointFeatures, lineFeatures, polyFeatures, (GeometryCollection) geometry, feature);
            } else {
                Feature newFeature = feature.clone(true);
                newFeature.setGeometry(geometry);
                BitSet featureBit = new BitSet();
                featureBit = GeoUtils.setBit(featureBit, geometry);
                if (featureBit.get(GeoUtils.pointBit)) pointFeatures.add(newFeature);
                if (featureBit.get(GeoUtils.lineBit)) lineFeatures.add(newFeature);
                if (featureBit.get(GeoUtils.polyBit)) polyFeatures.add(newFeature);
            }
        }
    }

    private void writeProjectionFile(PlugInContext context, Layer outputLayer) throws IOException, FileNotFoundException {
        DataSourceQuery dsqOut = outputLayer.getDataSourceQuery();
        if (dsqOut != null) {
            String outputFileName = dsqOut.getDataSource().getProperties().get("File").toString();
            if ((outputFileName.toLowerCase()).endsWith(".shp")) {
                String outputPrjFileName = "";
                int pos = outputFileName.lastIndexOf('.');
                outputPrjFileName = outputFileName.substring(0, pos) + ".prj";
                if (!(new File(outputPrjFileName).exists())) {
                    List layerList = context.getLayerManager().getLayers();
                    for (Iterator i = layerList.iterator(); i.hasNext(); ) {
                        Layer layer = (Layer) i.next();
                        DataSourceQuery dsq = layer.getDataSourceQuery();
                        if (dsq != null) {
                            String inputFileName = dsq.getDataSource().getProperties().get("File").toString();
                            if ((inputFileName.toLowerCase()).endsWith(".shp")) {
                                String inputPrjFileName = "";
                                pos = inputFileName.lastIndexOf('.');
                                inputPrjFileName = inputFileName.substring(0, pos) + ".prj";
                                if (new File(inputPrjFileName).exists()) {
                                    List prjStr = FileUtil.getContents(inputPrjFileName);
                                    try {
                                        FileUtil.setContents(outputPrjFileName, prjStr);
                                    } catch (IOException ex) {
                                        context.getWorkbenchFrame().getOutputFrame().addText("Could not write: " + outputPrjFileName + " due to: " + ex.getMessage());
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private String normalizeName(String name) {
        String newName = new String(name);
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if ((ch == '.') || (ch == '*') || (ch == '\\') || (ch == '/') || (ch == '#')) newName = newName.replace(ch, '_');
        }
        return newName;
    }

    private String getDestinationFileName(Layer layer) {
        String destination = "";
        if (noSource(layer)) destination = pathToSaveNewLayerSources + normalizeName(layer.getName()) + extToSaveNewLayerSources; else {
            DataSource ds = layer.getDataSourceQuery().getDataSource();
            if (!ds.isWritable()) destination = pathToSaveReadOnlySources + normalizeName(layer.getName()) + extToSaveReadOnlySources; else destination = ds.getProperties().get("File").toString();
        }
        return destination;
    }
}
