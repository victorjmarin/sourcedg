// Generated from JavaParser.g4 by ANTLR 4.7
package edu.rit.goal.sdg.java8.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JavaParser}.
 */
public interface JavaParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JavaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(JavaParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(JavaParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPackageDeclaration(JavaParser.PackageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPackageDeclaration(JavaParser.PackageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterImportDeclaration(JavaParser.ImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitImportDeclaration(JavaParser.ImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassTypeDeclaration}
	 * labeled alternative in {@link JavaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassTypeDeclaration(JavaParser.ClassTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassTypeDeclaration}
	 * labeled alternative in {@link JavaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassTypeDeclaration(JavaParser.ClassTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EnumTypeDeclaration}
	 * labeled alternative in {@link JavaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterEnumTypeDeclaration(JavaParser.EnumTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EnumTypeDeclaration}
	 * labeled alternative in {@link JavaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitEnumTypeDeclaration(JavaParser.EnumTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InterfaceTypeDeclaration}
	 * labeled alternative in {@link JavaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceTypeDeclaration(JavaParser.InterfaceTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InterfaceTypeDeclaration}
	 * labeled alternative in {@link JavaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceTypeDeclaration(JavaParser.InterfaceTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AnnotationTypeTypeDeclaration}
	 * labeled alternative in {@link JavaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeTypeDeclaration(JavaParser.AnnotationTypeTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AnnotationTypeTypeDeclaration}
	 * labeled alternative in {@link JavaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeTypeDeclaration(JavaParser.AnnotationTypeTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SemicolonTypeDeclaration}
	 * labeled alternative in {@link JavaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSemicolonTypeDeclaration(JavaParser.SemicolonTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SemicolonTypeDeclaration}
	 * labeled alternative in {@link JavaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSemicolonTypeDeclaration(JavaParser.SemicolonTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(JavaParser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(JavaParser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#classOrInterfaceModifier}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#classOrInterfaceModifier}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceModifier(JavaParser.ClassOrInterfaceModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void enterVariableModifier(JavaParser.VariableModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#variableModifier}.
	 * @param ctx the parse tree
	 */
	void exitVariableModifier(JavaParser.VariableModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(JavaParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameters(JavaParser.TypeParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#typeParameters}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameters(JavaParser.TypeParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameter(JavaParser.TypeParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameter(JavaParser.TypeParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#typeBound}.
	 * @param ctx the parse tree
	 */
	void enterTypeBound(JavaParser.TypeBoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#typeBound}.
	 * @param ctx the parse tree
	 */
	void exitTypeBound(JavaParser.TypeBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterEnumDeclaration(JavaParser.EnumDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#enumDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitEnumDeclaration(JavaParser.EnumDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#enumConstants}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstants(JavaParser.EnumConstantsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#enumConstants}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstants(JavaParser.EnumConstantsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#enumConstant}.
	 * @param ctx the parse tree
	 */
	void enterEnumConstant(JavaParser.EnumConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#enumConstant}.
	 * @param ctx the parse tree
	 */
	void exitEnumConstant(JavaParser.EnumConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 */
	void enterEnumBodyDeclarations(JavaParser.EnumBodyDeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#enumBodyDeclarations}.
	 * @param ctx the parse tree
	 */
	void exitEnumBodyDeclarations(JavaParser.EnumBodyDeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(JavaParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(JavaParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBody(JavaParser.InterfaceBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBody(JavaParser.InterfaceBodyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassBodyDeclSemicolon}
	 * labeled alternative in {@link JavaParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyDeclSemicolon(JavaParser.ClassBodyDeclSemicolonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassBodyDeclSemicolon}
	 * labeled alternative in {@link JavaParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyDeclSemicolon(JavaParser.ClassBodyDeclSemicolonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassBodyDeclBlock}
	 * labeled alternative in {@link JavaParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyDeclBlock(JavaParser.ClassBodyDeclBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassBodyDeclBlock}
	 * labeled alternative in {@link JavaParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyDeclBlock(JavaParser.ClassBodyDeclBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassBodyDeclMemberDecl}
	 * labeled alternative in {@link JavaParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyDeclMemberDecl(JavaParser.ClassBodyDeclMemberDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassBodyDeclMemberDecl}
	 * labeled alternative in {@link JavaParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyDeclMemberDecl(JavaParser.ClassBodyDeclMemberDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberDeclMethodDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclMethodDecl(JavaParser.MemberDeclMethodDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberDeclMethodDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclMethodDecl(JavaParser.MemberDeclMethodDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberDeclGenericMethodDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclGenericMethodDecl(JavaParser.MemberDeclGenericMethodDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberDeclGenericMethodDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclGenericMethodDecl(JavaParser.MemberDeclGenericMethodDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberDeclFieldDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclFieldDecl(JavaParser.MemberDeclFieldDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberDeclFieldDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclFieldDecl(JavaParser.MemberDeclFieldDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberDeclConstuctorDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclConstuctorDecl(JavaParser.MemberDeclConstuctorDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberDeclConstuctorDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclConstuctorDecl(JavaParser.MemberDeclConstuctorDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberDeclGenericConstructorDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclGenericConstructorDecl(JavaParser.MemberDeclGenericConstructorDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberDeclGenericConstructorDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclGenericConstructorDecl(JavaParser.MemberDeclGenericConstructorDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberDeclInterfaceDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclInterfaceDecl(JavaParser.MemberDeclInterfaceDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberDeclInterfaceDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclInterfaceDecl(JavaParser.MemberDeclInterfaceDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberDeclAnnotationTypeDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclAnnotationTypeDecl(JavaParser.MemberDeclAnnotationTypeDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberDeclAnnotationTypeDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclAnnotationTypeDecl(JavaParser.MemberDeclAnnotationTypeDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberDeclClassDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclClassDecl(JavaParser.MemberDeclClassDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberDeclClassDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclClassDecl(JavaParser.MemberDeclClassDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberDeclEnumDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclEnumDecl(JavaParser.MemberDeclEnumDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberDeclEnumDecl}
	 * labeled alternative in {@link JavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclEnumDecl(JavaParser.MemberDeclEnumDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(JavaParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodBodyBlock}
	 * labeled alternative in {@link JavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBodyBlock(JavaParser.MethodBodyBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodBodyBlock}
	 * labeled alternative in {@link JavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBodyBlock(JavaParser.MethodBodyBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodBodySemicolon}
	 * labeled alternative in {@link JavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBodySemicolon(JavaParser.MethodBodySemicolonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodBodySemicolon}
	 * labeled alternative in {@link JavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBodySemicolon(JavaParser.MethodBodySemicolonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeTypeOrVoidTypeType}
	 * labeled alternative in {@link JavaParser#typeTypeOrVoid}.
	 * @param ctx the parse tree
	 */
	void enterTypeTypeOrVoidTypeType(JavaParser.TypeTypeOrVoidTypeTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeTypeOrVoidTypeType}
	 * labeled alternative in {@link JavaParser#typeTypeOrVoid}.
	 * @param ctx the parse tree
	 */
	void exitTypeTypeOrVoidTypeType(JavaParser.TypeTypeOrVoidTypeTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeTypeOrVoidVoid}
	 * labeled alternative in {@link JavaParser#typeTypeOrVoid}.
	 * @param ctx the parse tree
	 */
	void enterTypeTypeOrVoidVoid(JavaParser.TypeTypeOrVoidVoidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeTypeOrVoidVoid}
	 * labeled alternative in {@link JavaParser#typeTypeOrVoid}.
	 * @param ctx the parse tree
	 */
	void exitTypeTypeOrVoidVoid(JavaParser.TypeTypeOrVoidVoidContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#genericMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericMethodDeclaration(JavaParser.GenericMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#genericMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericMethodDeclaration(JavaParser.GenericMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#genericConstructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericConstructorDeclaration(JavaParser.GenericConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#genericConstructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericConstructorDeclaration(JavaParser.GenericConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#constructorDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(JavaParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBodyDeclaration(JavaParser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBodyDeclaration(JavaParser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMemberDeclaration(JavaParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMemberDeclaration(JavaParser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#constDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstDeclaration(JavaParser.ConstDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#constDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstDeclaration(JavaParser.ConstDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclarator(JavaParser.ConstantDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclarator(JavaParser.ConstantDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#interfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#interfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#interfaceMethodModifier}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodModifier(JavaParser.InterfaceMethodModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#interfaceMethodModifier}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodModifier(JavaParser.InterfaceMethodModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#genericInterfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericInterfaceMethodDeclaration(JavaParser.GenericInterfaceMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#genericInterfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericInterfaceMethodDeclaration(JavaParser.GenericInterfaceMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarators(JavaParser.VariableDeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarators(JavaParser.VariableDeclaratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(JavaParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(JavaParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializer(JavaParser.VariableInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializer(JavaParser.VariableInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(JavaParser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(JavaParser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#classOrInterfaceType}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceType(JavaParser.ClassOrInterfaceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgument(JavaParser.TypeArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#typeArgument}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgument(JavaParser.TypeArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#qualifiedNameList}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedNameList(JavaParser.QualifiedNameListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#qualifiedNameList}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedNameList(JavaParser.QualifiedNameListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(JavaParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(JavaParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FormalParameterListParams}
	 * labeled alternative in {@link JavaParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterListParams(JavaParser.FormalParameterListParamsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FormalParameterListParams}
	 * labeled alternative in {@link JavaParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterListParams(JavaParser.FormalParameterListParamsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FormalParameterListVarArgs}
	 * labeled alternative in {@link JavaParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterListVarArgs(JavaParser.FormalParameterListVarArgsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FormalParameterListVarArgs}
	 * labeled alternative in {@link JavaParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterListVarArgs(JavaParser.FormalParameterListVarArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(JavaParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(JavaParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#lastFormalParameter}.
	 * @param ctx the parse tree
	 */
	void enterLastFormalParameter(JavaParser.LastFormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#lastFormalParameter}.
	 * @param ctx the parse tree
	 */
	void exitLastFormalParameter(JavaParser.LastFormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#x}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(JavaParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#x}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(JavaParser.QualifiedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(JavaParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(JavaParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(JavaParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(JavaParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#floatLiteral}.
	 * @param ctx the parse tree
	 */
	void enterFloatLiteral(JavaParser.FloatLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#floatLiteral}.
	 * @param ctx the parse tree
	 */
	void exitFloatLiteral(JavaParser.FloatLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(JavaParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(JavaParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#elementValuePairs}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePairs(JavaParser.ElementValuePairsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#elementValuePairs}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePairs(JavaParser.ElementValuePairsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void enterElementValuePair(JavaParser.ElementValuePairContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#elementValuePair}.
	 * @param ctx the parse tree
	 */
	void exitElementValuePair(JavaParser.ElementValuePairContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void enterElementValue(JavaParser.ElementValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#elementValue}.
	 * @param ctx the parse tree
	 */
	void exitElementValue(JavaParser.ElementValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterElementValueArrayInitializer(JavaParser.ElementValueArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#elementValueArrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitElementValueArrayInitializer(JavaParser.ElementValueArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeDeclaration(JavaParser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#annotationTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeDeclaration(JavaParser.AnnotationTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#annotationTypeBody}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeBody(JavaParser.AnnotationTypeBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#annotationTypeBody}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeBody(JavaParser.AnnotationTypeBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#annotationTypeElementDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeElementDeclaration(JavaParser.AnnotationTypeElementDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#annotationTypeElementDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeElementDeclaration(JavaParser.AnnotationTypeElementDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#annotationTypeElementRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationTypeElementRest(JavaParser.AnnotationTypeElementRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#annotationTypeElementRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationTypeElementRest(JavaParser.AnnotationTypeElementRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#annotationMethodOrConstantRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationMethodOrConstantRest(JavaParser.AnnotationMethodOrConstantRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#annotationMethodOrConstantRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationMethodOrConstantRest(JavaParser.AnnotationMethodOrConstantRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#annotationMethodRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationMethodRest(JavaParser.AnnotationMethodRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#annotationMethodRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationMethodRest(JavaParser.AnnotationMethodRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#annotationConstantRest}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationConstantRest(JavaParser.AnnotationConstantRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#annotationConstantRest}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationConstantRest(JavaParser.AnnotationConstantRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(JavaParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(JavaParser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(JavaParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(JavaParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockStmtLocalVarDecl}
	 * labeled alternative in {@link JavaParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStmtLocalVarDecl(JavaParser.BlockStmtLocalVarDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockStmtLocalVarDecl}
	 * labeled alternative in {@link JavaParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStmtLocalVarDecl(JavaParser.BlockStmtLocalVarDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockStmtStmt}
	 * labeled alternative in {@link JavaParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStmtStmt(JavaParser.BlockStmtStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockStmtStmt}
	 * labeled alternative in {@link JavaParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStmtStmt(JavaParser.BlockStmtStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockStmtLocalTypeDecl}
	 * labeled alternative in {@link JavaParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStmtLocalTypeDecl(JavaParser.BlockStmtLocalTypeDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockStmtLocalTypeDecl}
	 * labeled alternative in {@link JavaParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStmtLocalTypeDecl(JavaParser.BlockStmtLocalTypeDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#localTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalTypeDeclaration(JavaParser.LocalTypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#localTypeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalTypeDeclaration(JavaParser.LocalTypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStmt(JavaParser.BlockStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStmt(JavaParser.BlockStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssertStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssertStmt(JavaParser.AssertStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssertStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssertStmt(JavaParser.AssertStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(JavaParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(JavaParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(JavaParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(JavaParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(JavaParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(JavaParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DoStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDoStmt(JavaParser.DoStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DoStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDoStmt(JavaParser.DoStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TryStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterTryStmt(JavaParser.TryStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TryStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitTryStmt(JavaParser.TryStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TryWithResourcesStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterTryWithResourcesStmt(JavaParser.TryWithResourcesStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TryWithResourcesStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitTryWithResourcesStmt(JavaParser.TryWithResourcesStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SwitchStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStmt(JavaParser.SwitchStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SwitchStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStmt(JavaParser.SwitchStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SynchronizedStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSynchronizedStmt(JavaParser.SynchronizedStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SynchronizedStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSynchronizedStmt(JavaParser.SynchronizedStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(JavaParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(JavaParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ThrowStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterThrowStmt(JavaParser.ThrowStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ThrowStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitThrowStmt(JavaParser.ThrowStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(JavaParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BreakStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(JavaParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(JavaParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ContinueStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(JavaParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SemicolonStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSemicolonStmt(JavaParser.SemicolonStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SemicolonStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSemicolonStmt(JavaParser.SemicolonStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStmt(JavaParser.ExpressionStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStmt(JavaParser.ExpressionStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LabeledStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterLabeledStmt(JavaParser.LabeledStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LabeledStmt}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitLabeledStmt(JavaParser.LabeledStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void enterCatchClause(JavaParser.CatchClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void exitCatchClause(JavaParser.CatchClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#catchType}.
	 * @param ctx the parse tree
	 */
	void enterCatchType(JavaParser.CatchTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#catchType}.
	 * @param ctx the parse tree
	 */
	void exitCatchType(JavaParser.CatchTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#finallyBlock}.
	 * @param ctx the parse tree
	 */
	void enterFinallyBlock(JavaParser.FinallyBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#finallyBlock}.
	 * @param ctx the parse tree
	 */
	void exitFinallyBlock(JavaParser.FinallyBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#resourceSpecification}.
	 * @param ctx the parse tree
	 */
	void enterResourceSpecification(JavaParser.ResourceSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#resourceSpecification}.
	 * @param ctx the parse tree
	 */
	void exitResourceSpecification(JavaParser.ResourceSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#resources}.
	 * @param ctx the parse tree
	 */
	void enterResources(JavaParser.ResourcesContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#resources}.
	 * @param ctx the parse tree
	 */
	void exitResources(JavaParser.ResourcesContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#resource}.
	 * @param ctx the parse tree
	 */
	void enterResource(JavaParser.ResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#resource}.
	 * @param ctx the parse tree
	 */
	void exitResource(JavaParser.ResourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabel(JavaParser.SwitchLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabel(JavaParser.SwitchLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#forControl}.
	 * @param ctx the parse tree
	 */
	void enterForControl(JavaParser.ForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#forControl}.
	 * @param ctx the parse tree
	 */
	void exitForControl(JavaParser.ForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(JavaParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(JavaParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void enterEnhancedForControl(JavaParser.EnhancedForControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#enhancedForControl}.
	 * @param ctx the parse tree
	 */
	void exitEnhancedForControl(JavaParser.EnhancedForControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void enterParExpression(JavaParser.ParExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void exitParExpression(JavaParser.ParExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(JavaParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(JavaParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(JavaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(JavaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpression(JavaParser.LambdaExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpression(JavaParser.LambdaExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#lambdaParameters}.
	 * @param ctx the parse tree
	 */
	void enterLambdaParameters(JavaParser.LambdaParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#lambdaParameters}.
	 * @param ctx the parse tree
	 */
	void exitLambdaParameters(JavaParser.LambdaParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#lambdaBody}.
	 * @param ctx the parse tree
	 */
	void enterLambdaBody(JavaParser.LambdaBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#lambdaBody}.
	 * @param ctx the parse tree
	 */
	void exitLambdaBody(JavaParser.LambdaBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(JavaParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(JavaParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#classType}.
	 * @param ctx the parse tree
	 */
	void enterClassType(JavaParser.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#classType}.
	 * @param ctx the parse tree
	 */
	void exitClassType(JavaParser.ClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterCreator(JavaParser.CreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitCreator(JavaParser.CreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#createdName}.
	 * @param ctx the parse tree
	 */
	void enterCreatedName(JavaParser.CreatedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#createdName}.
	 * @param ctx the parse tree
	 */
	void exitCreatedName(JavaParser.CreatedNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#innerCreator}.
	 * @param ctx the parse tree
	 */
	void enterInnerCreator(JavaParser.InnerCreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#innerCreator}.
	 * @param ctx the parse tree
	 */
	void exitInnerCreator(JavaParser.InnerCreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreatorRest(JavaParser.ArrayCreatorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreatorRest(JavaParser.ArrayCreatorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void enterClassCreatorRest(JavaParser.ClassCreatorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void exitClassCreatorRest(JavaParser.ClassCreatorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#explicitGenericInvocation}.
	 * @param ctx the parse tree
	 */
	void enterExplicitGenericInvocation(JavaParser.ExplicitGenericInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#explicitGenericInvocation}.
	 * @param ctx the parse tree
	 */
	void exitExplicitGenericInvocation(JavaParser.ExplicitGenericInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void enterTypeArgumentsOrDiamond(JavaParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#typeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void exitTypeArgumentsOrDiamond(JavaParser.TypeArgumentsOrDiamondContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#nonWildcardTypeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void enterNonWildcardTypeArgumentsOrDiamond(JavaParser.NonWildcardTypeArgumentsOrDiamondContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#nonWildcardTypeArgumentsOrDiamond}.
	 * @param ctx the parse tree
	 */
	void exitNonWildcardTypeArgumentsOrDiamond(JavaParser.NonWildcardTypeArgumentsOrDiamondContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#nonWildcardTypeArguments}.
	 * @param ctx the parse tree
	 */
	void enterNonWildcardTypeArguments(JavaParser.NonWildcardTypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#nonWildcardTypeArguments}.
	 * @param ctx the parse tree
	 */
	void exitNonWildcardTypeArguments(JavaParser.NonWildcardTypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#typeList}.
	 * @param ctx the parse tree
	 */
	void enterTypeList(JavaParser.TypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#typeList}.
	 * @param ctx the parse tree
	 */
	void exitTypeList(JavaParser.TypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#typeType}.
	 * @param ctx the parse tree
	 */
	void enterTypeType(JavaParser.TypeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#typeType}.
	 * @param ctx the parse tree
	 */
	void exitTypeType(JavaParser.TypeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(JavaParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(JavaParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void enterTypeArguments(JavaParser.TypeArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#typeArguments}.
	 * @param ctx the parse tree
	 */
	void exitTypeArguments(JavaParser.TypeArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#superSuffix}.
	 * @param ctx the parse tree
	 */
	void enterSuperSuffix(JavaParser.SuperSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#superSuffix}.
	 * @param ctx the parse tree
	 */
	void exitSuperSuffix(JavaParser.SuperSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#explicitGenericInvocationSuffix}.
	 * @param ctx the parse tree
	 */
	void enterExplicitGenericInvocationSuffix(JavaParser.ExplicitGenericInvocationSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#explicitGenericInvocationSuffix}.
	 * @param ctx the parse tree
	 */
	void exitExplicitGenericInvocationSuffix(JavaParser.ExplicitGenericInvocationSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(JavaParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(JavaParser.ArgumentsContext ctx);
}