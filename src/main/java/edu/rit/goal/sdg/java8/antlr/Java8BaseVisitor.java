package edu.rit.goal.sdg.java8.antlr;

// Generated from Java8.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link Java8Visitor}, which can be
 * extended to create a visitor which only needs to handle a subset of the available
 * methods.
 *
 * @param <T>
 *            The return type of the visit operation. Use {@link Void} for operations with
 *            no return type.
 */
public class Java8BaseVisitor<T> extends AbstractParseTreeVisitor<T> implements Java8Visitor<T> {
    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitLiteral(final Java8Parser.LiteralContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitType(final Java8Parser.TypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPrimitiveType(final Java8Parser.PrimitiveTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitNumericType(final Java8Parser.NumericTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitIntegralType(final Java8Parser.IntegralTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitFloatingPointType(final Java8Parser.FloatingPointTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitReferenceType(final Java8Parser.ReferenceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitClassOrInterfaceType(final Java8Parser.ClassOrInterfaceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitClassType(final Java8Parser.ClassTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitClassType_lf_classOrInterfaceType(final Java8Parser.ClassType_lf_classOrInterfaceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitClassType_lfno_classOrInterfaceType(
	    final Java8Parser.ClassType_lfno_classOrInterfaceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitInterfaceType(final Java8Parser.InterfaceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitInterfaceType_lf_classOrInterfaceType(
	    final Java8Parser.InterfaceType_lf_classOrInterfaceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitInterfaceType_lfno_classOrInterfaceType(
	    final Java8Parser.InterfaceType_lfno_classOrInterfaceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTypeVariable(final Java8Parser.TypeVariableContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitArrayType(final Java8Parser.ArrayTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitDims(final Java8Parser.DimsContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTypeParameter(final Java8Parser.TypeParameterContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTypeParameterModifier(final Java8Parser.TypeParameterModifierContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTypeBound(final Java8Parser.TypeBoundContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAdditionalBound(final Java8Parser.AdditionalBoundContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTypeArguments(final Java8Parser.TypeArgumentsContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTypeArgumentList(final Java8Parser.TypeArgumentListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTypeArgument(final Java8Parser.TypeArgumentContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitWildcard(final Java8Parser.WildcardContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitWildcardBounds(final Java8Parser.WildcardBoundsContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPackageName(final Java8Parser.PackageNameContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTypeName(final Java8Parser.TypeNameContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPackageOrTypeName(final Java8Parser.PackageOrTypeNameContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitExpressionName(final Java8Parser.ExpressionNameContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMethodName(final Java8Parser.MethodNameContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAmbiguousName(final Java8Parser.AmbiguousNameContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitCompilationUnit(final Java8Parser.CompilationUnitContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPackageDeclaration(final Java8Parser.PackageDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPackageModifier(final Java8Parser.PackageModifierContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitImportDeclaration(final Java8Parser.ImportDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitSingleTypeImportDeclaration(final Java8Parser.SingleTypeImportDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTypeImportOnDemandDeclaration(final Java8Parser.TypeImportOnDemandDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitSingleStaticImportDeclaration(final Java8Parser.SingleStaticImportDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitStaticImportOnDemandDeclaration(final Java8Parser.StaticImportOnDemandDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTypeDeclaration(final Java8Parser.TypeDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitClassDeclaration(final Java8Parser.ClassDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitNormalClassDeclaration(final Java8Parser.NormalClassDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitClassModifier(final Java8Parser.ClassModifierContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTypeParameters(final Java8Parser.TypeParametersContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTypeParameterList(final Java8Parser.TypeParameterListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitSuperclass(final Java8Parser.SuperclassContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitSuperinterfaces(final Java8Parser.SuperinterfacesContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitInterfaceTypeList(final Java8Parser.InterfaceTypeListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitClassBody(final Java8Parser.ClassBodyContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitClassBodyDeclaration(final Java8Parser.ClassBodyDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitClassMemberDeclaration(final Java8Parser.ClassMemberDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitFieldDeclaration(final Java8Parser.FieldDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitFieldModifier(final Java8Parser.FieldModifierContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitVariableDeclaratorList(final Java8Parser.VariableDeclaratorListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitVariableDeclarator(final Java8Parser.VariableDeclaratorContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitVariableDeclaratorId(final Java8Parser.VariableDeclaratorIdContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitVariableInitializer(final Java8Parser.VariableInitializerContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnannType(final Java8Parser.UnannTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnannPrimitiveType(final Java8Parser.UnannPrimitiveTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnannReferenceType(final Java8Parser.UnannReferenceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnannClassOrInterfaceType(final Java8Parser.UnannClassOrInterfaceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnannClassType(final Java8Parser.UnannClassTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnannClassType_lf_unannClassOrInterfaceType(
	    final Java8Parser.UnannClassType_lf_unannClassOrInterfaceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnannClassType_lfno_unannClassOrInterfaceType(
	    final Java8Parser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnannInterfaceType(final Java8Parser.UnannInterfaceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnannInterfaceType_lf_unannClassOrInterfaceType(
	    final Java8Parser.UnannInterfaceType_lf_unannClassOrInterfaceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnannInterfaceType_lfno_unannClassOrInterfaceType(
	    final Java8Parser.UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnannTypeVariable(final Java8Parser.UnannTypeVariableContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnannArrayType(final Java8Parser.UnannArrayTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMethodDeclaration(final Java8Parser.MethodDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMethodModifier(final Java8Parser.MethodModifierContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMethodHeader(final Java8Parser.MethodHeaderContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitResult(final Java8Parser.ResultContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMethodDeclarator(final Java8Parser.MethodDeclaratorContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitFormalParameterList(final Java8Parser.FormalParameterListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitFormalParameters(final Java8Parser.FormalParametersContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitFormalParameter(final Java8Parser.FormalParameterContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitVariableModifier(final Java8Parser.VariableModifierContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitLastFormalParameter(final Java8Parser.LastFormalParameterContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitReceiverParameter(final Java8Parser.ReceiverParameterContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitThrows_(final Java8Parser.Throws_Context ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitExceptionTypeList(final Java8Parser.ExceptionTypeListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitExceptionType(final Java8Parser.ExceptionTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMethodBody(final Java8Parser.MethodBodyContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitInstanceInitializer(final Java8Parser.InstanceInitializerContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitStaticInitializer(final Java8Parser.StaticInitializerContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitConstructorDeclaration(final Java8Parser.ConstructorDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitConstructorModifier(final Java8Parser.ConstructorModifierContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitConstructorDeclarator(final Java8Parser.ConstructorDeclaratorContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitSimpleTypeName(final Java8Parser.SimpleTypeNameContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitConstructorBody(final Java8Parser.ConstructorBodyContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitExplicitConstructorInvocation(final Java8Parser.ExplicitConstructorInvocationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitEnumDeclaration(final Java8Parser.EnumDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitEnumBody(final Java8Parser.EnumBodyContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitEnumConstantList(final Java8Parser.EnumConstantListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitEnumConstant(final Java8Parser.EnumConstantContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitEnumConstantModifier(final Java8Parser.EnumConstantModifierContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitEnumBodyDeclarations(final Java8Parser.EnumBodyDeclarationsContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitInterfaceDeclaration(final Java8Parser.InterfaceDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitNormalInterfaceDeclaration(final Java8Parser.NormalInterfaceDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitInterfaceModifier(final Java8Parser.InterfaceModifierContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitExtendsInterfaces(final Java8Parser.ExtendsInterfacesContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitInterfaceBody(final Java8Parser.InterfaceBodyContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitInterfaceMemberDeclaration(final Java8Parser.InterfaceMemberDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitConstantDeclaration(final Java8Parser.ConstantDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitConstantModifier(final Java8Parser.ConstantModifierContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitInterfaceMethodDeclaration(final Java8Parser.InterfaceMethodDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitInterfaceMethodModifier(final Java8Parser.InterfaceMethodModifierContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAnnotationTypeDeclaration(final Java8Parser.AnnotationTypeDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAnnotationTypeBody(final Java8Parser.AnnotationTypeBodyContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAnnotationTypeMemberDeclaration(final Java8Parser.AnnotationTypeMemberDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAnnotationTypeElementDeclaration(final Java8Parser.AnnotationTypeElementDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAnnotationTypeElementModifier(final Java8Parser.AnnotationTypeElementModifierContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitDefaultValue(final Java8Parser.DefaultValueContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAnnotation(final Java8Parser.AnnotationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitNormalAnnotation(final Java8Parser.NormalAnnotationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitElementValuePairList(final Java8Parser.ElementValuePairListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitElementValuePair(final Java8Parser.ElementValuePairContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitElementValue(final Java8Parser.ElementValueContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitElementValueArrayInitializer(final Java8Parser.ElementValueArrayInitializerContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitElementValueList(final Java8Parser.ElementValueListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMarkerAnnotation(final Java8Parser.MarkerAnnotationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitSingleElementAnnotation(final Java8Parser.SingleElementAnnotationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitArrayInitializer(final Java8Parser.ArrayInitializerContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitVariableInitializerList(final Java8Parser.VariableInitializerListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitBlock(final Java8Parser.BlockContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitBlockStatements(final Java8Parser.BlockStatementsContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitBlockStatement(final Java8Parser.BlockStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitLocalVariableDeclarationStatement(final Java8Parser.LocalVariableDeclarationStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitLocalVariableDeclaration(final Java8Parser.LocalVariableDeclarationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitStatement(final Java8Parser.StatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitStatementNoShortIf(final Java8Parser.StatementNoShortIfContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitStatementWithoutTrailingSubstatement(
	    final Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitEmptyStatement(final Java8Parser.EmptyStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitLabeledStatement(final Java8Parser.LabeledStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitLabeledStatementNoShortIf(final Java8Parser.LabeledStatementNoShortIfContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitExpressionStatement(final Java8Parser.ExpressionStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitStatementExpression(final Java8Parser.StatementExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitIfThenStatement(final Java8Parser.IfThenStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitIfThenElseStatement(final Java8Parser.IfThenElseStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitIfThenElseStatementNoShortIf(final Java8Parser.IfThenElseStatementNoShortIfContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAssertStatement(final Java8Parser.AssertStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitSwitchStatement(final Java8Parser.SwitchStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitSwitchBlock(final Java8Parser.SwitchBlockContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitSwitchBlockStatementGroup(final Java8Parser.SwitchBlockStatementGroupContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitSwitchLabels(final Java8Parser.SwitchLabelsContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitSwitchLabel(final Java8Parser.SwitchLabelContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitEnumConstantName(final Java8Parser.EnumConstantNameContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitWhileStatement(final Java8Parser.WhileStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitWhileStatementNoShortIf(final Java8Parser.WhileStatementNoShortIfContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitDoStatement(final Java8Parser.DoStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitForStatement(final Java8Parser.ForStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitForStatementNoShortIf(final Java8Parser.ForStatementNoShortIfContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitBasicForStatement(final Java8Parser.BasicForStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitBasicForStatementNoShortIf(final Java8Parser.BasicForStatementNoShortIfContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitForInit(final Java8Parser.ForInitContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitForUpdate(final Java8Parser.ForUpdateContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitStatementExpressionList(final Java8Parser.StatementExpressionListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitEnhancedForStatement(final Java8Parser.EnhancedForStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitEnhancedForStatementNoShortIf(final Java8Parser.EnhancedForStatementNoShortIfContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitBreakStatement(final Java8Parser.BreakStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitContinueStatement(final Java8Parser.ContinueStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitReturnStatement(final Java8Parser.ReturnStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitThrowStatement(final Java8Parser.ThrowStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitSynchronizedStatement(final Java8Parser.SynchronizedStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTryStatement(final Java8Parser.TryStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitCatches(final Java8Parser.CatchesContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitCatchClause(final Java8Parser.CatchClauseContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitCatchFormalParameter(final Java8Parser.CatchFormalParameterContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitCatchType(final Java8Parser.CatchTypeContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitFinally_(final Java8Parser.Finally_Context ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTryWithResourcesStatement(final Java8Parser.TryWithResourcesStatementContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitResourceSpecification(final Java8Parser.ResourceSpecificationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitResourceList(final Java8Parser.ResourceListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitResource(final Java8Parser.ResourceContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPrimary(final Java8Parser.PrimaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPrimaryNoNewArray(final Java8Parser.PrimaryNoNewArrayContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPrimaryNoNewArray_lf_arrayAccess(final Java8Parser.PrimaryNoNewArray_lf_arrayAccessContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPrimaryNoNewArray_lfno_arrayAccess(final Java8Parser.PrimaryNoNewArray_lfno_arrayAccessContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPrimaryNoNewArray_lf_primary(final Java8Parser.PrimaryNoNewArray_lf_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(
	    final Java8Parser.PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(
	    final Java8Parser.PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPrimaryNoNewArray_lfno_primary(final Java8Parser.PrimaryNoNewArray_lfno_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(
	    final Java8Parser.PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(
	    final Java8Parser.PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitClassInstanceCreationExpression(final Java8Parser.ClassInstanceCreationExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitClassInstanceCreationExpression_lf_primary(
	    final Java8Parser.ClassInstanceCreationExpression_lf_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitClassInstanceCreationExpression_lfno_primary(
	    final Java8Parser.ClassInstanceCreationExpression_lfno_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitTypeArgumentsOrDiamond(final Java8Parser.TypeArgumentsOrDiamondContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitFieldAccess(final Java8Parser.FieldAccessContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitFieldAccess_lf_primary(final Java8Parser.FieldAccess_lf_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitFieldAccess_lfno_primary(final Java8Parser.FieldAccess_lfno_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitArrayAccess(final Java8Parser.ArrayAccessContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitArrayAccess_lf_primary(final Java8Parser.ArrayAccess_lf_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitArrayAccess_lfno_primary(final Java8Parser.ArrayAccess_lfno_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMethodInvocation(final Java8Parser.MethodInvocationContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMethodInvocation_lf_primary(final Java8Parser.MethodInvocation_lf_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMethodInvocation_lfno_primary(final Java8Parser.MethodInvocation_lfno_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitArgumentList(final Java8Parser.ArgumentListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMethodReference(final Java8Parser.MethodReferenceContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMethodReference_lf_primary(final Java8Parser.MethodReference_lf_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMethodReference_lfno_primary(final Java8Parser.MethodReference_lfno_primaryContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitArrayCreationExpression(final Java8Parser.ArrayCreationExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitDimExprs(final Java8Parser.DimExprsContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitDimExpr(final Java8Parser.DimExprContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitConstantExpression(final Java8Parser.ConstantExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitExpression(final Java8Parser.ExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitLambdaExpression(final Java8Parser.LambdaExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitLambdaParameters(final Java8Parser.LambdaParametersContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitInferredFormalParameterList(final Java8Parser.InferredFormalParameterListContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitLambdaBody(final Java8Parser.LambdaBodyContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAssignmentExpression(final Java8Parser.AssignmentExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAssignment(final Java8Parser.AssignmentContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitLeftHandSide(final Java8Parser.LeftHandSideContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAssignmentOperator(final Java8Parser.AssignmentOperatorContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitConditionalExpression(final Java8Parser.ConditionalExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitConditionalOrExpression(final Java8Parser.ConditionalOrExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitConditionalAndExpression(final Java8Parser.ConditionalAndExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitInclusiveOrExpression(final Java8Parser.InclusiveOrExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitExclusiveOrExpression(final Java8Parser.ExclusiveOrExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAndExpression(final Java8Parser.AndExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitEqualityExpression(final Java8Parser.EqualityExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitRelationalExpression(final Java8Parser.RelationalExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitShiftExpression(final Java8Parser.ShiftExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitAdditiveExpression(final Java8Parser.AdditiveExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitMultiplicativeExpression(final Java8Parser.MultiplicativeExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnaryExpression(final Java8Parser.UnaryExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPreIncrementExpression(final Java8Parser.PreIncrementExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPreDecrementExpression(final Java8Parser.PreDecrementExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitUnaryExpressionNotPlusMinus(final Java8Parser.UnaryExpressionNotPlusMinusContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPostfixExpression(final Java8Parser.PostfixExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPostIncrementExpression(final Java8Parser.PostIncrementExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPostIncrementExpression_lf_postfixExpression(
	    final Java8Parser.PostIncrementExpression_lf_postfixExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPostDecrementExpression(final Java8Parser.PostDecrementExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitPostDecrementExpression_lf_postfixExpression(
	    final Java8Parser.PostDecrementExpression_lf_postfixExpressionContext ctx) {
	return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation returns the result of calling {@link #visitChildren} on
     * {@code ctx}.
     * </p>
     */
    @Override
    public T visitCastExpression(final Java8Parser.CastExpressionContext ctx) {
	return visitChildren(ctx);
    }
}