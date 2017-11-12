// Generated from JavaParser.g4 by ANTLR 4.7
package edu.rit.goal.sdg.java8.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JavaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ABSTRACT=1, ASSERT=2, BOOLEAN=3, BREAK=4, BYTE=5, CASE=6, CATCH=7, CHAR=8, 
		CLASS=9, CONST=10, CONTINUE=11, DEFAULT=12, DO=13, DOUBLE=14, ELSE=15, 
		ENUM=16, EXTENDS=17, FINAL=18, FINALLY=19, FLOAT=20, FOR=21, IF=22, GOTO=23, 
		IMPLEMENTS=24, IMPORT=25, INSTANCEOF=26, INT=27, INTERFACE=28, LONG=29, 
		NATIVE=30, NEW=31, PACKAGE=32, PRIVATE=33, PROTECTED=34, PUBLIC=35, RETURN=36, 
		SHORT=37, STATIC=38, STRICTFP=39, SUPER=40, SWITCH=41, SYNCHRONIZED=42, 
		THIS=43, THROW=44, THROWS=45, TRANSIENT=46, TRY=47, VOID=48, VOLATILE=49, 
		WHILE=50, DECIMAL_LITERAL=51, HEX_LITERAL=52, OCT_LITERAL=53, BINARY_LITERAL=54, 
		FLOAT_LITERAL=55, HEX_FLOAT_LITERAL=56, BOOL_LITERAL=57, CHAR_LITERAL=58, 
		STRING_LITERAL=59, NULL_LITERAL=60, LPAREN=61, RPAREN=62, LBRACE=63, RBRACE=64, 
		LBRACK=65, RBRACK=66, SEMI=67, COMMA=68, DOT=69, ASSIGN=70, GT=71, LT=72, 
		BANG=73, TILDE=74, QUESTION=75, COLON=76, EQUAL=77, LE=78, GE=79, NOTEQUAL=80, 
		AND=81, OR=82, INC=83, DEC=84, ADD=85, SUB=86, MUL=87, DIV=88, BITAND=89, 
		BITOR=90, CARET=91, MOD=92, ADD_ASSIGN=93, SUB_ASSIGN=94, MUL_ASSIGN=95, 
		DIV_ASSIGN=96, AND_ASSIGN=97, OR_ASSIGN=98, XOR_ASSIGN=99, MOD_ASSIGN=100, 
		LSHIFT_ASSIGN=101, RSHIFT_ASSIGN=102, URSHIFT_ASSIGN=103, ARROW=104, COLONCOLON=105, 
		AT=106, ELLIPSIS=107, WS=108, COMMENT=109, LINE_COMMENT=110, IDENTIFIER=111;
	public static final int
		RULE_compilationUnit = 0, RULE_packageDeclaration = 1, RULE_importDeclaration = 2, 
		RULE_typeDeclaration = 3, RULE_modifier = 4, RULE_classOrInterfaceModifier = 5, 
		RULE_variableModifier = 6, RULE_classDeclaration = 7, RULE_typeParameters = 8, 
		RULE_typeParameter = 9, RULE_typeBound = 10, RULE_enumDeclaration = 11, 
		RULE_enumConstants = 12, RULE_enumConstant = 13, RULE_enumBodyDeclarations = 14, 
		RULE_interfaceDeclaration = 15, RULE_classBody = 16, RULE_interfaceBody = 17, 
		RULE_classBodyDeclaration = 18, RULE_memberDeclaration = 19, RULE_methodDeclaration = 20, 
		RULE_methodBody = 21, RULE_typeTypeOrVoid = 22, RULE_genericMethodDeclaration = 23, 
		RULE_genericConstructorDeclaration = 24, RULE_constructorDeclaration = 25, 
		RULE_fieldDeclaration = 26, RULE_interfaceBodyDeclaration = 27, RULE_interfaceMemberDeclaration = 28, 
		RULE_constDeclaration = 29, RULE_constantDeclarator = 30, RULE_interfaceMethodDeclaration = 31, 
		RULE_interfaceMethodModifier = 32, RULE_genericInterfaceMethodDeclaration = 33, 
		RULE_variableDeclarators = 34, RULE_variableDeclarator = 35, RULE_variableDeclaratorId = 36, 
		RULE_variableInitializer = 37, RULE_arrayInitializer = 38, RULE_classOrInterfaceType = 39, 
		RULE_typeArgument = 40, RULE_qualifiedNameList = 41, RULE_formalParameters = 42, 
		RULE_formalParameterList = 43, RULE_formalParameter = 44, RULE_lastFormalParameter = 45, 
		RULE_qualifiedName = 46, RULE_literal = 47, RULE_integerLiteral = 48, 
		RULE_floatLiteral = 49, RULE_annotation = 50, RULE_elementValuePairs = 51, 
		RULE_elementValuePair = 52, RULE_elementValue = 53, RULE_elementValueArrayInitializer = 54, 
		RULE_annotationTypeDeclaration = 55, RULE_annotationTypeBody = 56, RULE_annotationTypeElementDeclaration = 57, 
		RULE_annotationTypeElementRest = 58, RULE_annotationMethodOrConstantRest = 59, 
		RULE_annotationMethodRest = 60, RULE_annotationConstantRest = 61, RULE_defaultValue = 62, 
		RULE_block = 63, RULE_blockStatement = 64, RULE_localVariableDeclaration = 65, 
		RULE_localTypeDeclaration = 66, RULE_statement = 67, RULE_catchClause = 68, 
		RULE_catchType = 69, RULE_finallyBlock = 70, RULE_resourceSpecification = 71, 
		RULE_resources = 72, RULE_resource = 73, RULE_switchBlockStatementGroup = 74, 
		RULE_switchLabel = 75, RULE_forControl = 76, RULE_forInit = 77, RULE_enhancedForControl = 78, 
		RULE_parExpression = 79, RULE_expressionList = 80, RULE_expression = 81, 
		RULE_lambdaExpression = 82, RULE_lambdaParameters = 83, RULE_lambdaBody = 84, 
		RULE_primary = 85, RULE_classType = 86, RULE_creator = 87, RULE_createdName = 88, 
		RULE_innerCreator = 89, RULE_arrayCreatorRest = 90, RULE_classCreatorRest = 91, 
		RULE_explicitGenericInvocation = 92, RULE_typeArgumentsOrDiamond = 93, 
		RULE_nonWildcardTypeArgumentsOrDiamond = 94, RULE_nonWildcardTypeArguments = 95, 
		RULE_typeList = 96, RULE_typeType = 97, RULE_primitiveType = 98, RULE_typeArguments = 99, 
		RULE_superSuffix = 100, RULE_explicitGenericInvocationSuffix = 101, RULE_arguments = 102;
	public static final String[] ruleNames = {
		"compilationUnit", "packageDeclaration", "importDeclaration", "typeDeclaration", 
		"modifier", "classOrInterfaceModifier", "variableModifier", "classDeclaration", 
		"typeParameters", "typeParameter", "typeBound", "enumDeclaration", "enumConstants", 
		"enumConstant", "enumBodyDeclarations", "interfaceDeclaration", "classBody", 
		"interfaceBody", "classBodyDeclaration", "memberDeclaration", "methodDeclaration", 
		"methodBody", "typeTypeOrVoid", "genericMethodDeclaration", "genericConstructorDeclaration", 
		"constructorDeclaration", "fieldDeclaration", "interfaceBodyDeclaration", 
		"interfaceMemberDeclaration", "constDeclaration", "constantDeclarator", 
		"interfaceMethodDeclaration", "interfaceMethodModifier", "genericInterfaceMethodDeclaration", 
		"variableDeclarators", "variableDeclarator", "variableDeclaratorId", "variableInitializer", 
		"arrayInitializer", "classOrInterfaceType", "typeArgument", "qualifiedNameList", 
		"formalParameters", "formalParameterList", "formalParameter", "lastFormalParameter", 
		"qualifiedName", "literal", "integerLiteral", "floatLiteral", "annotation", 
		"elementValuePairs", "elementValuePair", "elementValue", "elementValueArrayInitializer", 
		"annotationTypeDeclaration", "annotationTypeBody", "annotationTypeElementDeclaration", 
		"annotationTypeElementRest", "annotationMethodOrConstantRest", "annotationMethodRest", 
		"annotationConstantRest", "defaultValue", "block", "blockStatement", "localVariableDeclaration", 
		"localTypeDeclaration", "statement", "catchClause", "catchType", "finallyBlock", 
		"resourceSpecification", "resources", "resource", "switchBlockStatementGroup", 
		"switchLabel", "forControl", "forInit", "enhancedForControl", "parExpression", 
		"expressionList", "expression", "lambdaExpression", "lambdaParameters", 
		"lambdaBody", "primary", "classType", "creator", "createdName", "innerCreator", 
		"arrayCreatorRest", "classCreatorRest", "explicitGenericInvocation", "typeArgumentsOrDiamond", 
		"nonWildcardTypeArgumentsOrDiamond", "nonWildcardTypeArguments", "typeList", 
		"typeType", "primitiveType", "typeArguments", "superSuffix", "explicitGenericInvocationSuffix", 
		"arguments"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'abstract'", "'assert'", "'boolean'", "'break'", "'byte'", "'case'", 
		"'catch'", "'char'", "'class'", "'const'", "'continue'", "'default'", 
		"'do'", "'double'", "'else'", "'enum'", "'extends'", "'final'", "'finally'", 
		"'float'", "'for'", "'if'", "'goto'", "'implements'", "'import'", "'instanceof'", 
		"'int'", "'interface'", "'long'", "'native'", "'new'", "'package'", "'private'", 
		"'protected'", "'public'", "'return'", "'short'", "'static'", "'strictfp'", 
		"'super'", "'switch'", "'synchronized'", "'this'", "'throw'", "'throws'", 
		"'transient'", "'try'", "'void'", "'volatile'", "'while'", null, null, 
		null, null, null, null, null, null, null, "'null'", "'('", "')'", "'{'", 
		"'}'", "'['", "']'", "';'", "','", "'.'", "'='", "'>'", "'<'", "'!'", 
		"'~'", "'?'", "':'", "'=='", "'<='", "'>='", "'!='", "'&&'", "'||'", "'++'", 
		"'--'", "'+'", "'-'", "'*'", "'/'", "'&'", "'|'", "'^'", "'%'", "'+='", 
		"'-='", "'*='", "'/='", "'&='", "'|='", "'^='", "'%='", "'<<='", "'>>='", 
		"'>>>='", "'->'", "'::'", "'@'", "'...'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ABSTRACT", "ASSERT", "BOOLEAN", "BREAK", "BYTE", "CASE", "CATCH", 
		"CHAR", "CLASS", "CONST", "CONTINUE", "DEFAULT", "DO", "DOUBLE", "ELSE", 
		"ENUM", "EXTENDS", "FINAL", "FINALLY", "FLOAT", "FOR", "IF", "GOTO", "IMPLEMENTS", 
		"IMPORT", "INSTANCEOF", "INT", "INTERFACE", "LONG", "NATIVE", "NEW", "PACKAGE", 
		"PRIVATE", "PROTECTED", "PUBLIC", "RETURN", "SHORT", "STATIC", "STRICTFP", 
		"SUPER", "SWITCH", "SYNCHRONIZED", "THIS", "THROW", "THROWS", "TRANSIENT", 
		"TRY", "VOID", "VOLATILE", "WHILE", "DECIMAL_LITERAL", "HEX_LITERAL", 
		"OCT_LITERAL", "BINARY_LITERAL", "FLOAT_LITERAL", "HEX_FLOAT_LITERAL", 
		"BOOL_LITERAL", "CHAR_LITERAL", "STRING_LITERAL", "NULL_LITERAL", "LPAREN", 
		"RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "DOT", 
		"ASSIGN", "GT", "LT", "BANG", "TILDE", "QUESTION", "COLON", "EQUAL", "LE", 
		"GE", "NOTEQUAL", "AND", "OR", "INC", "DEC", "ADD", "SUB", "MUL", "DIV", 
		"BITAND", "BITOR", "CARET", "MOD", "ADD_ASSIGN", "SUB_ASSIGN", "MUL_ASSIGN", 
		"DIV_ASSIGN", "AND_ASSIGN", "OR_ASSIGN", "XOR_ASSIGN", "MOD_ASSIGN", "LSHIFT_ASSIGN", 
		"RSHIFT_ASSIGN", "URSHIFT_ASSIGN", "ARROW", "COLONCOLON", "AT", "ELLIPSIS", 
		"WS", "COMMENT", "LINE_COMMENT", "IDENTIFIER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "JavaParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JavaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CompilationUnitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(JavaParser.EOF, 0); }
		public PackageDeclarationContext packageDeclaration() {
			return getRuleContext(PackageDeclarationContext.class,0);
		}
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public List<TypeDeclarationContext> typeDeclaration() {
			return getRuleContexts(TypeDeclarationContext.class);
		}
		public TypeDeclarationContext typeDeclaration(int i) {
			return getRuleContext(TypeDeclarationContext.class,i);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitCompilationUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitCompilationUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(206);
				packageDeclaration();
				}
				break;
			}
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(209);
				importDeclaration();
				}
				}
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << CLASS) | (1L << ENUM) | (1L << FINAL) | (1L << INTERFACE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP))) != 0) || _la==SEMI || _la==AT) {
				{
				{
				setState(215);
				typeDeclaration();
				}
				}
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(221);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PackageDeclarationContext extends ParserRuleContext {
		public TerminalNode PACKAGE() { return getToken(JavaParser.PACKAGE, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public PackageDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterPackageDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitPackageDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitPackageDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageDeclarationContext packageDeclaration() throws RecognitionException {
		PackageDeclarationContext _localctx = new PackageDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_packageDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(223);
				annotation();
				}
				}
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(229);
			match(PACKAGE);
			setState(230);
			qualifiedName();
			setState(231);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportDeclarationContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(JavaParser.IMPORT, 0); }
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode STATIC() { return getToken(JavaParser.STATIC, 0); }
		public ImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterImportDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitImportDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitImportDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclarationContext importDeclaration() throws RecognitionException {
		ImportDeclarationContext _localctx = new ImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(IMPORT);
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STATIC) {
				{
				setState(234);
				match(STATIC);
				}
			}

			setState(237);
			qualifiedName();
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(238);
				match(DOT);
				setState(239);
				match(MUL);
				}
			}

			setState(242);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDeclarationContext extends ParserRuleContext {
		public TypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDeclaration; }
	 
		public TypeDeclarationContext() { }
		public void copyFrom(TypeDeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EnumTypeDeclarationContext extends TypeDeclarationContext {
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public EnumTypeDeclarationContext(TypeDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterEnumTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitEnumTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitEnumTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SemicolonTypeDeclarationContext extends TypeDeclarationContext {
		public SemicolonTypeDeclarationContext(TypeDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterSemicolonTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitSemicolonTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitSemicolonTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassTypeDeclarationContext extends TypeDeclarationContext {
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public ClassTypeDeclarationContext(TypeDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterClassTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitClassTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitClassTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AnnotationTypeTypeDeclarationContext extends TypeDeclarationContext {
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public AnnotationTypeTypeDeclarationContext(TypeDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterAnnotationTypeTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitAnnotationTypeTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitAnnotationTypeTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InterfaceTypeDeclarationContext extends TypeDeclarationContext {
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public InterfaceTypeDeclarationContext(TypeDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterInterfaceTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitInterfaceTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitInterfaceTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDeclarationContext typeDeclaration() throws RecognitionException {
		TypeDeclarationContext _localctx = new TypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_typeDeclaration);
		int _la;
		try {
			int _alt;
			setState(273);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new ClassTypeDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP))) != 0) || _la==AT) {
					{
					{
					setState(244);
					classOrInterfaceModifier();
					}
					}
					setState(249);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(250);
				classDeclaration();
				}
				break;
			case 2:
				_localctx = new EnumTypeDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP))) != 0) || _la==AT) {
					{
					{
					setState(251);
					classOrInterfaceModifier();
					}
					}
					setState(256);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(257);
				enumDeclaration();
				}
				break;
			case 3:
				_localctx = new InterfaceTypeDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(261);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP))) != 0) || _la==AT) {
					{
					{
					setState(258);
					classOrInterfaceModifier();
					}
					}
					setState(263);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(264);
				interfaceDeclaration();
				}
				break;
			case 4:
				_localctx = new AnnotationTypeTypeDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(268);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(265);
						classOrInterfaceModifier();
						}
						} 
					}
					setState(270);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				setState(271);
				annotationTypeDeclaration();
				}
				break;
			case 5:
				_localctx = new SemicolonTypeDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(272);
				match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModifierContext extends ParserRuleContext {
		public ClassOrInterfaceModifierContext classOrInterfaceModifier() {
			return getRuleContext(ClassOrInterfaceModifierContext.class,0);
		}
		public TerminalNode NATIVE() { return getToken(JavaParser.NATIVE, 0); }
		public TerminalNode SYNCHRONIZED() { return getToken(JavaParser.SYNCHRONIZED, 0); }
		public TerminalNode TRANSIENT() { return getToken(JavaParser.TRANSIENT, 0); }
		public TerminalNode VOLATILE() { return getToken(JavaParser.VOLATILE, 0); }
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_modifier);
		try {
			setState(280);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case FINAL:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case STATIC:
			case STRICTFP:
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(275);
				classOrInterfaceModifier();
				}
				break;
			case NATIVE:
				enterOuterAlt(_localctx, 2);
				{
				setState(276);
				match(NATIVE);
				}
				break;
			case SYNCHRONIZED:
				enterOuterAlt(_localctx, 3);
				{
				setState(277);
				match(SYNCHRONIZED);
				}
				break;
			case TRANSIENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(278);
				match(TRANSIENT);
				}
				break;
			case VOLATILE:
				enterOuterAlt(_localctx, 5);
				{
				setState(279);
				match(VOLATILE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassOrInterfaceModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public TerminalNode PUBLIC() { return getToken(JavaParser.PUBLIC, 0); }
		public TerminalNode PROTECTED() { return getToken(JavaParser.PROTECTED, 0); }
		public TerminalNode PRIVATE() { return getToken(JavaParser.PRIVATE, 0); }
		public TerminalNode STATIC() { return getToken(JavaParser.STATIC, 0); }
		public TerminalNode ABSTRACT() { return getToken(JavaParser.ABSTRACT, 0); }
		public TerminalNode FINAL() { return getToken(JavaParser.FINAL, 0); }
		public TerminalNode STRICTFP() { return getToken(JavaParser.STRICTFP, 0); }
		public ClassOrInterfaceModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classOrInterfaceModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterClassOrInterfaceModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitClassOrInterfaceModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitClassOrInterfaceModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassOrInterfaceModifierContext classOrInterfaceModifier() throws RecognitionException {
		ClassOrInterfaceModifierContext _localctx = new ClassOrInterfaceModifierContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_classOrInterfaceModifier);
		try {
			setState(290);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				annotation();
				}
				break;
			case PUBLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				match(PUBLIC);
				}
				break;
			case PROTECTED:
				enterOuterAlt(_localctx, 3);
				{
				setState(284);
				match(PROTECTED);
				}
				break;
			case PRIVATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(285);
				match(PRIVATE);
				}
				break;
			case STATIC:
				enterOuterAlt(_localctx, 5);
				{
				setState(286);
				match(STATIC);
				}
				break;
			case ABSTRACT:
				enterOuterAlt(_localctx, 6);
				{
				setState(287);
				match(ABSTRACT);
				}
				break;
			case FINAL:
				enterOuterAlt(_localctx, 7);
				{
				setState(288);
				match(FINAL);
				}
				break;
			case STRICTFP:
				enterOuterAlt(_localctx, 8);
				{
				setState(289);
				match(STRICTFP);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableModifierContext extends ParserRuleContext {
		public TerminalNode FINAL() { return getToken(JavaParser.FINAL, 0); }
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public VariableModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterVariableModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitVariableModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitVariableModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableModifierContext variableModifier() throws RecognitionException {
		VariableModifierContext _localctx = new VariableModifierContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variableModifier);
		try {
			setState(294);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(292);
				match(FINAL);
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 2);
				{
				setState(293);
				annotation();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(JavaParser.CLASS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(JavaParser.EXTENDS, 0); }
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode IMPLEMENTS() { return getToken(JavaParser.IMPLEMENTS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(CLASS);
			setState(297);
			match(IDENTIFIER);
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(298);
				typeParameters();
				}
			}

			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(301);
				match(EXTENDS);
				setState(302);
				typeType();
				}
			}

			setState(307);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(305);
				match(IMPLEMENTS);
				setState(306);
				typeList();
				}
			}

			setState(309);
			classBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeParametersContext extends ParserRuleContext {
		public List<TypeParameterContext> typeParameter() {
			return getRuleContexts(TypeParameterContext.class);
		}
		public TypeParameterContext typeParameter(int i) {
			return getRuleContext(TypeParameterContext.class,i);
		}
		public TypeParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterTypeParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitTypeParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitTypeParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParametersContext typeParameters() throws RecognitionException {
		TypeParametersContext _localctx = new TypeParametersContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typeParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			match(LT);
			setState(312);
			typeParameter();
			setState(317);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(313);
				match(COMMA);
				setState(314);
				typeParameter();
				}
				}
				setState(319);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(320);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeParameterContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode EXTENDS() { return getToken(JavaParser.EXTENDS, 0); }
		public TypeBoundContext typeBound() {
			return getRuleContext(TypeBoundContext.class,0);
		}
		public TypeParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterTypeParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitTypeParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitTypeParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParameterContext typeParameter() throws RecognitionException {
		TypeParameterContext _localctx = new TypeParameterContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_typeParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(322);
				annotation();
				}
				}
				setState(327);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(328);
			match(IDENTIFIER);
			setState(331);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(329);
				match(EXTENDS);
				setState(330);
				typeBound();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeBoundContext extends ParserRuleContext {
		public List<TypeTypeContext> typeType() {
			return getRuleContexts(TypeTypeContext.class);
		}
		public TypeTypeContext typeType(int i) {
			return getRuleContext(TypeTypeContext.class,i);
		}
		public TypeBoundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeBound; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterTypeBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitTypeBound(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitTypeBound(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeBoundContext typeBound() throws RecognitionException {
		TypeBoundContext _localctx = new TypeBoundContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_typeBound);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			typeType();
			setState(338);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITAND) {
				{
				{
				setState(334);
				match(BITAND);
				setState(335);
				typeType();
				}
				}
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumDeclarationContext extends ParserRuleContext {
		public TerminalNode ENUM() { return getToken(JavaParser.ENUM, 0); }
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public TerminalNode IMPLEMENTS() { return getToken(JavaParser.IMPLEMENTS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public EnumConstantsContext enumConstants() {
			return getRuleContext(EnumConstantsContext.class,0);
		}
		public EnumBodyDeclarationsContext enumBodyDeclarations() {
			return getRuleContext(EnumBodyDeclarationsContext.class,0);
		}
		public EnumDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterEnumDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitEnumDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitEnumDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumDeclarationContext enumDeclaration() throws RecognitionException {
		EnumDeclarationContext _localctx = new EnumDeclarationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_enumDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			match(ENUM);
			setState(342);
			match(IDENTIFIER);
			setState(345);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(343);
				match(IMPLEMENTS);
				setState(344);
				typeList();
				}
			}

			setState(347);
			match(LBRACE);
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AT || _la==IDENTIFIER) {
				{
				setState(348);
				enumConstants();
				}
			}

			setState(352);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(351);
				match(COMMA);
				}
			}

			setState(355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(354);
				enumBodyDeclarations();
				}
			}

			setState(357);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumConstantsContext extends ParserRuleContext {
		public List<EnumConstantContext> enumConstant() {
			return getRuleContexts(EnumConstantContext.class);
		}
		public EnumConstantContext enumConstant(int i) {
			return getRuleContext(EnumConstantContext.class,i);
		}
		public EnumConstantsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstants; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterEnumConstants(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitEnumConstants(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitEnumConstants(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumConstantsContext enumConstants() throws RecognitionException {
		EnumConstantsContext _localctx = new EnumConstantsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_enumConstants);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			enumConstant();
			setState(364);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(360);
					match(COMMA);
					setState(361);
					enumConstant();
					}
					} 
				}
				setState(366);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumConstantContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public EnumConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterEnumConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitEnumConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitEnumConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumConstantContext enumConstant() throws RecognitionException {
		EnumConstantContext _localctx = new EnumConstantContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_enumConstant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(367);
				annotation();
				}
				}
				setState(372);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(373);
			match(IDENTIFIER);
			setState(375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(374);
				arguments();
				}
			}

			setState(378);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(377);
				classBody();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumBodyDeclarationsContext extends ParserRuleContext {
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public EnumBodyDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumBodyDeclarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterEnumBodyDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitEnumBodyDeclarations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitEnumBodyDeclarations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumBodyDeclarationsContext enumBodyDeclarations() throws RecognitionException {
		EnumBodyDeclarationsContext _localctx = new EnumBodyDeclarationsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_enumBodyDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			match(SEMI);
			setState(384);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE) | (1L << LBRACE))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (SEMI - 67)) | (1L << (LT - 67)) | (1L << (AT - 67)) | (1L << (IDENTIFIER - 67)))) != 0)) {
				{
				{
				setState(381);
				classBodyDeclaration();
				}
				}
				setState(386);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceDeclarationContext extends ParserRuleContext {
		public TerminalNode INTERFACE() { return getToken(JavaParser.INTERFACE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public InterfaceBodyContext interfaceBody() {
			return getRuleContext(InterfaceBodyContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(JavaParser.EXTENDS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public InterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterInterfaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitInterfaceDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitInterfaceDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceDeclarationContext interfaceDeclaration() throws RecognitionException {
		InterfaceDeclarationContext _localctx = new InterfaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_interfaceDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			match(INTERFACE);
			setState(388);
			match(IDENTIFIER);
			setState(390);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(389);
				typeParameters();
				}
			}

			setState(394);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(392);
				match(EXTENDS);
				setState(393);
				typeList();
				}
			}

			setState(396);
			interfaceBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyContext extends ParserRuleContext {
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterClassBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitClassBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitClassBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_classBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			match(LBRACE);
			setState(402);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE) | (1L << LBRACE))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (SEMI - 67)) | (1L << (LT - 67)) | (1L << (AT - 67)) | (1L << (IDENTIFIER - 67)))) != 0)) {
				{
				{
				setState(399);
				classBodyDeclaration();
				}
				}
				setState(404);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(405);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceBodyContext extends ParserRuleContext {
		public List<InterfaceBodyDeclarationContext> interfaceBodyDeclaration() {
			return getRuleContexts(InterfaceBodyDeclarationContext.class);
		}
		public InterfaceBodyDeclarationContext interfaceBodyDeclaration(int i) {
			return getRuleContext(InterfaceBodyDeclarationContext.class,i);
		}
		public InterfaceBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterInterfaceBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitInterfaceBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitInterfaceBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceBodyContext interfaceBody() throws RecognitionException {
		InterfaceBodyContext _localctx = new InterfaceBodyContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_interfaceBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(407);
			match(LBRACE);
			setState(411);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DEFAULT) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (SEMI - 67)) | (1L << (LT - 67)) | (1L << (AT - 67)) | (1L << (IDENTIFIER - 67)))) != 0)) {
				{
				{
				setState(408);
				interfaceBodyDeclaration();
				}
				}
				setState(413);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(414);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyDeclarationContext extends ParserRuleContext {
		public ClassBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBodyDeclaration; }
	 
		public ClassBodyDeclarationContext() { }
		public void copyFrom(ClassBodyDeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ClassBodyDeclMemberDeclContext extends ClassBodyDeclarationContext {
		public MemberDeclarationContext memberDeclaration() {
			return getRuleContext(MemberDeclarationContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public ClassBodyDeclMemberDeclContext(ClassBodyDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterClassBodyDeclMemberDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitClassBodyDeclMemberDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitClassBodyDeclMemberDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassBodyDeclSemicolonContext extends ClassBodyDeclarationContext {
		public ClassBodyDeclSemicolonContext(ClassBodyDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterClassBodyDeclSemicolon(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitClassBodyDeclSemicolon(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitClassBodyDeclSemicolon(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassBodyDeclBlockContext extends ClassBodyDeclarationContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode STATIC() { return getToken(JavaParser.STATIC, 0); }
		public ClassBodyDeclBlockContext(ClassBodyDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterClassBodyDeclBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitClassBodyDeclBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitClassBodyDeclBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBodyDeclarationContext classBodyDeclaration() throws RecognitionException {
		ClassBodyDeclarationContext _localctx = new ClassBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_classBodyDeclaration);
		int _la;
		try {
			int _alt;
			setState(428);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				_localctx = new ClassBodyDeclSemicolonContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(416);
				match(SEMI);
				}
				break;
			case 2:
				_localctx = new ClassBodyDeclBlockContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(418);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(417);
					match(STATIC);
					}
				}

				setState(420);
				block();
				}
				break;
			case 3:
				_localctx = new ClassBodyDeclMemberDeclContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(424);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(421);
						modifier();
						}
						} 
					}
					setState(426);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
				}
				setState(427);
				memberDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberDeclarationContext extends ParserRuleContext {
		public MemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberDeclaration; }
	 
		public MemberDeclarationContext() { }
		public void copyFrom(MemberDeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MemberDeclEnumDeclContext extends MemberDeclarationContext {
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public MemberDeclEnumDeclContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterMemberDeclEnumDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitMemberDeclEnumDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitMemberDeclEnumDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberDeclFieldDeclContext extends MemberDeclarationContext {
		public FieldDeclarationContext fieldDeclaration() {
			return getRuleContext(FieldDeclarationContext.class,0);
		}
		public MemberDeclFieldDeclContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterMemberDeclFieldDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitMemberDeclFieldDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitMemberDeclFieldDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberDeclAnnotationTypeDeclContext extends MemberDeclarationContext {
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public MemberDeclAnnotationTypeDeclContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterMemberDeclAnnotationTypeDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitMemberDeclAnnotationTypeDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitMemberDeclAnnotationTypeDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberDeclGenericMethodDeclContext extends MemberDeclarationContext {
		public GenericMethodDeclarationContext genericMethodDeclaration() {
			return getRuleContext(GenericMethodDeclarationContext.class,0);
		}
		public MemberDeclGenericMethodDeclContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterMemberDeclGenericMethodDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitMemberDeclGenericMethodDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitMemberDeclGenericMethodDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberDeclConstuctorDeclContext extends MemberDeclarationContext {
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public MemberDeclConstuctorDeclContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterMemberDeclConstuctorDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitMemberDeclConstuctorDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitMemberDeclConstuctorDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberDeclMethodDeclContext extends MemberDeclarationContext {
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public MemberDeclMethodDeclContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterMemberDeclMethodDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitMemberDeclMethodDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitMemberDeclMethodDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberDeclGenericConstructorDeclContext extends MemberDeclarationContext {
		public GenericConstructorDeclarationContext genericConstructorDeclaration() {
			return getRuleContext(GenericConstructorDeclarationContext.class,0);
		}
		public MemberDeclGenericConstructorDeclContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterMemberDeclGenericConstructorDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitMemberDeclGenericConstructorDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitMemberDeclGenericConstructorDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberDeclClassDeclContext extends MemberDeclarationContext {
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public MemberDeclClassDeclContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterMemberDeclClassDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitMemberDeclClassDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitMemberDeclClassDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberDeclInterfaceDeclContext extends MemberDeclarationContext {
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public MemberDeclInterfaceDeclContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterMemberDeclInterfaceDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitMemberDeclInterfaceDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitMemberDeclInterfaceDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberDeclarationContext memberDeclaration() throws RecognitionException {
		MemberDeclarationContext _localctx = new MemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_memberDeclaration);
		try {
			setState(439);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				_localctx = new MemberDeclMethodDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(430);
				methodDeclaration();
				}
				break;
			case 2:
				_localctx = new MemberDeclGenericMethodDeclContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(431);
				genericMethodDeclaration();
				}
				break;
			case 3:
				_localctx = new MemberDeclFieldDeclContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(432);
				fieldDeclaration();
				}
				break;
			case 4:
				_localctx = new MemberDeclConstuctorDeclContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(433);
				constructorDeclaration();
				}
				break;
			case 5:
				_localctx = new MemberDeclGenericConstructorDeclContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(434);
				genericConstructorDeclaration();
				}
				break;
			case 6:
				_localctx = new MemberDeclInterfaceDeclContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(435);
				interfaceDeclaration();
				}
				break;
			case 7:
				_localctx = new MemberDeclAnnotationTypeDeclContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(436);
				annotationTypeDeclaration();
				}
				break;
			case 8:
				_localctx = new MemberDeclClassDeclContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(437);
				classDeclaration();
				}
				break;
			case 9:
				_localctx = new MemberDeclEnumDeclContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(438);
				enumDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclarationContext extends ParserRuleContext {
		public TypeTypeOrVoidContext typeTypeOrVoid() {
			return getRuleContext(TypeTypeOrVoidContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public TerminalNode THROWS() { return getToken(JavaParser.THROWS, 0); }
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitMethodDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_methodDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			typeTypeOrVoid();
			setState(442);
			match(IDENTIFIER);
			setState(443);
			formalParameters();
			setState(448);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(444);
				match(LBRACK);
				setState(445);
				match(RBRACK);
				}
				}
				setState(450);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(453);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(451);
				match(THROWS);
				setState(452);
				qualifiedNameList();
				}
			}

			setState(455);
			methodBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodBodyContext extends ParserRuleContext {
		public MethodBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodBody; }
	 
		public MethodBodyContext() { }
		public void copyFrom(MethodBodyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MethodBodyBlockContext extends MethodBodyContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public MethodBodyBlockContext(MethodBodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterMethodBodyBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitMethodBodyBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitMethodBodyBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MethodBodySemicolonContext extends MethodBodyContext {
		public MethodBodySemicolonContext(MethodBodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterMethodBodySemicolon(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitMethodBodySemicolon(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitMethodBodySemicolon(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodBodyContext methodBody() throws RecognitionException {
		MethodBodyContext _localctx = new MethodBodyContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_methodBody);
		try {
			setState(459);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				_localctx = new MethodBodyBlockContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(457);
				block();
				}
				break;
			case SEMI:
				_localctx = new MethodBodySemicolonContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(458);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeTypeOrVoidContext extends ParserRuleContext {
		public TypeTypeOrVoidContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeTypeOrVoid; }
	 
		public TypeTypeOrVoidContext() { }
		public void copyFrom(TypeTypeOrVoidContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeTypeOrVoidTypeTypeContext extends TypeTypeOrVoidContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TypeTypeOrVoidTypeTypeContext(TypeTypeOrVoidContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterTypeTypeOrVoidTypeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitTypeTypeOrVoidTypeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitTypeTypeOrVoidTypeType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TypeTypeOrVoidVoidContext extends TypeTypeOrVoidContext {
		public TerminalNode VOID() { return getToken(JavaParser.VOID, 0); }
		public TypeTypeOrVoidVoidContext(TypeTypeOrVoidContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterTypeTypeOrVoidVoid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitTypeTypeOrVoidVoid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitTypeTypeOrVoidVoid(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeTypeOrVoidContext typeTypeOrVoid() throws RecognitionException {
		TypeTypeOrVoidContext _localctx = new TypeTypeOrVoidContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_typeTypeOrVoid);
		try {
			setState(463);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case AT:
			case IDENTIFIER:
				_localctx = new TypeTypeOrVoidTypeTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(461);
				typeType();
				}
				break;
			case VOID:
				_localctx = new TypeTypeOrVoidVoidContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(462);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GenericMethodDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public GenericMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterGenericMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitGenericMethodDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitGenericMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GenericMethodDeclarationContext genericMethodDeclaration() throws RecognitionException {
		GenericMethodDeclarationContext _localctx = new GenericMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_genericMethodDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			typeParameters();
			setState(466);
			methodDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GenericConstructorDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public GenericConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericConstructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterGenericConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitGenericConstructorDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitGenericConstructorDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GenericConstructorDeclarationContext genericConstructorDeclaration() throws RecognitionException {
		GenericConstructorDeclarationContext _localctx = new GenericConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_genericConstructorDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(468);
			typeParameters();
			setState(469);
			constructorDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorDeclarationContext extends ParserRuleContext {
		public BlockContext constructorBody;
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode THROWS() { return getToken(JavaParser.THROWS, 0); }
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public ConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitConstructorDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitConstructorDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorDeclarationContext constructorDeclaration() throws RecognitionException {
		ConstructorDeclarationContext _localctx = new ConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_constructorDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(471);
			match(IDENTIFIER);
			setState(472);
			formalParameters();
			setState(475);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(473);
				match(THROWS);
				setState(474);
				qualifiedNameList();
				}
			}

			setState(477);
			((ConstructorDeclarationContext)_localctx).constructorBody = block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldDeclarationContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public FieldDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterFieldDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitFieldDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitFieldDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldDeclarationContext fieldDeclaration() throws RecognitionException {
		FieldDeclarationContext _localctx = new FieldDeclarationContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_fieldDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479);
			typeType();
			setState(480);
			variableDeclarators();
			setState(481);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceBodyDeclarationContext extends ParserRuleContext {
		public InterfaceMemberDeclarationContext interfaceMemberDeclaration() {
			return getRuleContext(InterfaceMemberDeclarationContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public InterfaceBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceBodyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterInterfaceBodyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitInterfaceBodyDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitInterfaceBodyDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceBodyDeclarationContext interfaceBodyDeclaration() throws RecognitionException {
		InterfaceBodyDeclarationContext _localctx = new InterfaceBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_interfaceBodyDeclaration);
		try {
			int _alt;
			setState(491);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case CLASS:
			case DEFAULT:
			case DOUBLE:
			case ENUM:
			case FINAL:
			case FLOAT:
			case INT:
			case INTERFACE:
			case LONG:
			case NATIVE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOID:
			case VOLATILE:
			case LT:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(486);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(483);
						modifier();
						}
						} 
					}
					setState(488);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
				}
				setState(489);
				interfaceMemberDeclaration();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(490);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceMemberDeclarationContext extends ParserRuleContext {
		public ConstDeclarationContext constDeclaration() {
			return getRuleContext(ConstDeclarationContext.class,0);
		}
		public InterfaceMethodDeclarationContext interfaceMethodDeclaration() {
			return getRuleContext(InterfaceMethodDeclarationContext.class,0);
		}
		public GenericInterfaceMethodDeclarationContext genericInterfaceMethodDeclaration() {
			return getRuleContext(GenericInterfaceMethodDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public InterfaceMemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMemberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterInterfaceMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitInterfaceMemberDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitInterfaceMemberDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceMemberDeclarationContext interfaceMemberDeclaration() throws RecognitionException {
		InterfaceMemberDeclarationContext _localctx = new InterfaceMemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_interfaceMemberDeclaration);
		try {
			setState(500);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(493);
				constDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(494);
				interfaceMethodDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(495);
				genericInterfaceMethodDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(496);
				interfaceDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(497);
				annotationTypeDeclaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(498);
				classDeclaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(499);
				enumDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstDeclarationContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public List<ConstantDeclaratorContext> constantDeclarator() {
			return getRuleContexts(ConstantDeclaratorContext.class);
		}
		public ConstantDeclaratorContext constantDeclarator(int i) {
			return getRuleContext(ConstantDeclaratorContext.class,i);
		}
		public ConstDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterConstDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitConstDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitConstDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstDeclarationContext constDeclaration() throws RecognitionException {
		ConstDeclarationContext _localctx = new ConstDeclarationContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_constDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(502);
			typeType();
			setState(503);
			constantDeclarator();
			setState(508);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(504);
				match(COMMA);
				setState(505);
				constantDeclarator();
				}
				}
				setState(510);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(511);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantDeclaratorContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public VariableInitializerContext variableInitializer() {
			return getRuleContext(VariableInitializerContext.class,0);
		}
		public ConstantDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterConstantDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitConstantDeclarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitConstantDeclarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantDeclaratorContext constantDeclarator() throws RecognitionException {
		ConstantDeclaratorContext _localctx = new ConstantDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_constantDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			match(IDENTIFIER);
			setState(518);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(514);
				match(LBRACK);
				setState(515);
				match(RBRACK);
				}
				}
				setState(520);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(521);
			match(ASSIGN);
			setState(522);
			variableInitializer();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceMethodDeclarationContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public TypeTypeOrVoidContext typeTypeOrVoid() {
			return getRuleContext(TypeTypeOrVoidContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public List<InterfaceMethodModifierContext> interfaceMethodModifier() {
			return getRuleContexts(InterfaceMethodModifierContext.class);
		}
		public InterfaceMethodModifierContext interfaceMethodModifier(int i) {
			return getRuleContext(InterfaceMethodModifierContext.class,i);
		}
		public TerminalNode THROWS() { return getToken(JavaParser.THROWS, 0); }
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public InterfaceMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterInterfaceMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitInterfaceMethodDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitInterfaceMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceMethodDeclarationContext interfaceMethodDeclaration() throws RecognitionException {
		InterfaceMethodDeclarationContext _localctx = new InterfaceMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_interfaceMethodDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(527);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(524);
					interfaceMethodModifier();
					}
					} 
				}
				setState(529);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			}
			setState(540);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case VOID:
			case AT:
			case IDENTIFIER:
				{
				setState(530);
				typeTypeOrVoid();
				}
				break;
			case LT:
				{
				setState(531);
				typeParameters();
				setState(535);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(532);
						annotation();
						}
						} 
					}
					setState(537);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,49,_ctx);
				}
				setState(538);
				typeTypeOrVoid();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(542);
			match(IDENTIFIER);
			setState(543);
			formalParameters();
			setState(548);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(544);
				match(LBRACK);
				setState(545);
				match(RBRACK);
				}
				}
				setState(550);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(553);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(551);
				match(THROWS);
				setState(552);
				qualifiedNameList();
				}
			}

			setState(555);
			methodBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceMethodModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public TerminalNode PUBLIC() { return getToken(JavaParser.PUBLIC, 0); }
		public TerminalNode ABSTRACT() { return getToken(JavaParser.ABSTRACT, 0); }
		public TerminalNode DEFAULT() { return getToken(JavaParser.DEFAULT, 0); }
		public TerminalNode STATIC() { return getToken(JavaParser.STATIC, 0); }
		public TerminalNode STRICTFP() { return getToken(JavaParser.STRICTFP, 0); }
		public InterfaceMethodModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMethodModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterInterfaceMethodModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitInterfaceMethodModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitInterfaceMethodModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceMethodModifierContext interfaceMethodModifier() throws RecognitionException {
		InterfaceMethodModifierContext _localctx = new InterfaceMethodModifierContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_interfaceMethodModifier);
		try {
			setState(563);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(557);
				annotation();
				}
				break;
			case PUBLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(558);
				match(PUBLIC);
				}
				break;
			case ABSTRACT:
				enterOuterAlt(_localctx, 3);
				{
				setState(559);
				match(ABSTRACT);
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 4);
				{
				setState(560);
				match(DEFAULT);
				}
				break;
			case STATIC:
				enterOuterAlt(_localctx, 5);
				{
				setState(561);
				match(STATIC);
				}
				break;
			case STRICTFP:
				enterOuterAlt(_localctx, 6);
				{
				setState(562);
				match(STRICTFP);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GenericInterfaceMethodDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public InterfaceMethodDeclarationContext interfaceMethodDeclaration() {
			return getRuleContext(InterfaceMethodDeclarationContext.class,0);
		}
		public GenericInterfaceMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericInterfaceMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterGenericInterfaceMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitGenericInterfaceMethodDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitGenericInterfaceMethodDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GenericInterfaceMethodDeclarationContext genericInterfaceMethodDeclaration() throws RecognitionException {
		GenericInterfaceMethodDeclarationContext _localctx = new GenericInterfaceMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_genericInterfaceMethodDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(565);
			typeParameters();
			setState(566);
			interfaceMethodDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclaratorsContext extends ParserRuleContext {
		public List<VariableDeclaratorContext> variableDeclarator() {
			return getRuleContexts(VariableDeclaratorContext.class);
		}
		public VariableDeclaratorContext variableDeclarator(int i) {
			return getRuleContext(VariableDeclaratorContext.class,i);
		}
		public VariableDeclaratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterVariableDeclarators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitVariableDeclarators(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitVariableDeclarators(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclaratorsContext variableDeclarators() throws RecognitionException {
		VariableDeclaratorsContext _localctx = new VariableDeclaratorsContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_variableDeclarators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(568);
			variableDeclarator();
			setState(573);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(569);
				match(COMMA);
				setState(570);
				variableDeclarator();
				}
				}
				setState(575);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclaratorContext extends ParserRuleContext {
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public VariableInitializerContext variableInitializer() {
			return getRuleContext(VariableInitializerContext.class,0);
		}
		public VariableDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterVariableDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitVariableDeclarator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitVariableDeclarator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclaratorContext variableDeclarator() throws RecognitionException {
		VariableDeclaratorContext _localctx = new VariableDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_variableDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(576);
			variableDeclaratorId();
			setState(579);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(577);
				match(ASSIGN);
				setState(578);
				variableInitializer();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclaratorIdContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public VariableDeclaratorIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaratorId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterVariableDeclaratorId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitVariableDeclaratorId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitVariableDeclaratorId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclaratorIdContext variableDeclaratorId() throws RecognitionException {
		VariableDeclaratorIdContext _localctx = new VariableDeclaratorIdContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_variableDeclaratorId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(581);
			match(IDENTIFIER);
			setState(586);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(582);
				match(LBRACK);
				setState(583);
				match(RBRACK);
				}
				}
				setState(588);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableInitializerContext extends ParserRuleContext {
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterVariableInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitVariableInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitVariableInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableInitializerContext variableInitializer() throws RecognitionException {
		VariableInitializerContext _localctx = new VariableInitializerContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_variableInitializer);
		try {
			setState(591);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(589);
				arrayInitializer();
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case SHORT:
			case SUPER:
			case THIS:
			case VOID:
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
			case BOOL_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case NULL_LITERAL:
			case LPAREN:
			case LT:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(590);
				expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayInitializerContext extends ParserRuleContext {
		public List<VariableInitializerContext> variableInitializer() {
			return getRuleContexts(VariableInitializerContext.class);
		}
		public VariableInitializerContext variableInitializer(int i) {
			return getRuleContext(VariableInitializerContext.class,i);
		}
		public ArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitArrayInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitArrayInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayInitializerContext arrayInitializer() throws RecognitionException {
		ArrayInitializerContext _localctx = new ArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_arrayInitializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(593);
			match(LBRACE);
			setState(605);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << CHAR_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << LPAREN) | (1L << LBRACE))) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (LT - 72)) | (1L << (BANG - 72)) | (1L << (TILDE - 72)) | (1L << (INC - 72)) | (1L << (DEC - 72)) | (1L << (ADD - 72)) | (1L << (SUB - 72)) | (1L << (AT - 72)) | (1L << (IDENTIFIER - 72)))) != 0)) {
				{
				setState(594);
				variableInitializer();
				setState(599);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(595);
						match(COMMA);
						setState(596);
						variableInitializer();
						}
						} 
					}
					setState(601);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
				}
				setState(603);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(602);
					match(COMMA);
					}
				}

				}
			}

			setState(607);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassOrInterfaceTypeContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(JavaParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(JavaParser.IDENTIFIER, i);
		}
		public List<TypeArgumentsContext> typeArguments() {
			return getRuleContexts(TypeArgumentsContext.class);
		}
		public TypeArgumentsContext typeArguments(int i) {
			return getRuleContext(TypeArgumentsContext.class,i);
		}
		public ClassOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterClassOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitClassOrInterfaceType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitClassOrInterfaceType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassOrInterfaceTypeContext classOrInterfaceType() throws RecognitionException {
		ClassOrInterfaceTypeContext _localctx = new ClassOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_classOrInterfaceType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(609);
			match(IDENTIFIER);
			setState(611);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				{
				setState(610);
				typeArguments();
				}
				break;
			}
			setState(620);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(613);
					match(DOT);
					setState(614);
					match(IDENTIFIER);
					setState(616);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
					case 1:
						{
						setState(615);
						typeArguments();
						}
						break;
					}
					}
					} 
				}
				setState(622);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeArgumentContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(JavaParser.EXTENDS, 0); }
		public TerminalNode SUPER() { return getToken(JavaParser.SUPER, 0); }
		public TypeArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterTypeArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitTypeArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitTypeArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeArgumentContext typeArgument() throws RecognitionException {
		TypeArgumentContext _localctx = new TypeArgumentContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_typeArgument);
		int _la;
		try {
			setState(629);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(623);
				typeType();
				}
				break;
			case QUESTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(624);
				match(QUESTION);
				setState(627);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXTENDS || _la==SUPER) {
					{
					setState(625);
					_la = _input.LA(1);
					if ( !(_la==EXTENDS || _la==SUPER) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(626);
					typeType();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QualifiedNameListContext extends ParserRuleContext {
		public List<QualifiedNameContext> qualifiedName() {
			return getRuleContexts(QualifiedNameContext.class);
		}
		public QualifiedNameContext qualifiedName(int i) {
			return getRuleContext(QualifiedNameContext.class,i);
		}
		public QualifiedNameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedNameList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterQualifiedNameList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitQualifiedNameList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitQualifiedNameList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNameListContext qualifiedNameList() throws RecognitionException {
		QualifiedNameListContext _localctx = new QualifiedNameListContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_qualifiedNameList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(631);
			qualifiedName();
			setState(636);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(632);
				match(COMMA);
				setState(633);
				qualifiedName();
				}
				}
				setState(638);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParametersContext extends ParserRuleContext {
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public FormalParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterFormalParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitFormalParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitFormalParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParametersContext formalParameters() throws RecognitionException {
		FormalParametersContext _localctx = new FormalParametersContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_formalParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(639);
			match(LPAREN);
			setState(641);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << SHORT))) != 0) || _la==AT || _la==IDENTIFIER) {
				{
				setState(640);
				formalParameterList();
				}
			}

			setState(643);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParameterListContext extends ParserRuleContext {
		public FormalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterList; }
	 
		public FormalParameterListContext() { }
		public void copyFrom(FormalParameterListContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FormalParameterListVarArgsContext extends FormalParameterListContext {
		public LastFormalParameterContext lastFormalParameter() {
			return getRuleContext(LastFormalParameterContext.class,0);
		}
		public FormalParameterListVarArgsContext(FormalParameterListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterFormalParameterListVarArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitFormalParameterListVarArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitFormalParameterListVarArgs(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FormalParameterListParamsContext extends FormalParameterListContext {
		public List<FormalParameterContext> formalParameter() {
			return getRuleContexts(FormalParameterContext.class);
		}
		public FormalParameterContext formalParameter(int i) {
			return getRuleContext(FormalParameterContext.class,i);
		}
		public LastFormalParameterContext lastFormalParameter() {
			return getRuleContext(LastFormalParameterContext.class,0);
		}
		public FormalParameterListParamsContext(FormalParameterListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterFormalParameterListParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitFormalParameterListParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitFormalParameterListParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterListContext formalParameterList() throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_formalParameterList);
		int _la;
		try {
			int _alt;
			setState(658);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				_localctx = new FormalParameterListParamsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(645);
				formalParameter();
				setState(650);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,68,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(646);
						match(COMMA);
						setState(647);
						formalParameter();
						}
						} 
					}
					setState(652);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,68,_ctx);
				}
				setState(655);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(653);
					match(COMMA);
					setState(654);
					lastFormalParameter();
					}
				}

				}
				break;
			case 2:
				_localctx = new FormalParameterListVarArgsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(657);
				lastFormalParameter();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParameterContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public FormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitFormalParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitFormalParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterContext formalParameter() throws RecognitionException {
		FormalParameterContext _localctx = new FormalParameterContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_formalParameter);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(663);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(660);
					variableModifier();
					}
					} 
				}
				setState(665);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
			}
			setState(666);
			typeType();
			setState(667);
			variableDeclaratorId();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LastFormalParameterContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public LastFormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lastFormalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterLastFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitLastFormalParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitLastFormalParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LastFormalParameterContext lastFormalParameter() throws RecognitionException {
		LastFormalParameterContext _localctx = new LastFormalParameterContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_lastFormalParameter);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(672);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,72,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(669);
					variableModifier();
					}
					} 
				}
				setState(674);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,72,_ctx);
			}
			setState(675);
			typeType();
			setState(676);
			match(ELLIPSIS);
			setState(677);
			variableDeclaratorId();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QualifiedNameContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(JavaParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(JavaParser.IDENTIFIER, i);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitQualifiedName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitQualifiedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_qualifiedName);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(679);
			match(IDENTIFIER);
			setState(684);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(680);
					match(DOT);
					setState(681);
					match(IDENTIFIER);
					}
					} 
				}
				setState(686);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public FloatLiteralContext floatLiteral() {
			return getRuleContext(FloatLiteralContext.class,0);
		}
		public TerminalNode CHAR_LITERAL() { return getToken(JavaParser.CHAR_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(JavaParser.STRING_LITERAL, 0); }
		public TerminalNode BOOL_LITERAL() { return getToken(JavaParser.BOOL_LITERAL, 0); }
		public TerminalNode NULL_LITERAL() { return getToken(JavaParser.NULL_LITERAL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_literal);
		try {
			setState(693);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(687);
				integerLiteral();
				}
				break;
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(688);
				floatLiteral();
				}
				break;
			case CHAR_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(689);
				match(CHAR_LITERAL);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(690);
				match(STRING_LITERAL);
				}
				break;
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(691);
				match(BOOL_LITERAL);
				}
				break;
			case NULL_LITERAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(692);
				match(NULL_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerLiteralContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(JavaParser.DECIMAL_LITERAL, 0); }
		public TerminalNode HEX_LITERAL() { return getToken(JavaParser.HEX_LITERAL, 0); }
		public TerminalNode OCT_LITERAL() { return getToken(JavaParser.OCT_LITERAL, 0); }
		public TerminalNode BINARY_LITERAL() { return getToken(JavaParser.BINARY_LITERAL, 0); }
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitIntegerLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_integerLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(695);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FloatLiteralContext extends ParserRuleContext {
		public TerminalNode FLOAT_LITERAL() { return getToken(JavaParser.FLOAT_LITERAL, 0); }
		public TerminalNode HEX_FLOAT_LITERAL() { return getToken(JavaParser.HEX_FLOAT_LITERAL, 0); }
		public FloatLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floatLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterFloatLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitFloatLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitFloatLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatLiteralContext floatLiteral() throws RecognitionException {
		FloatLiteralContext _localctx = new FloatLiteralContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_floatLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(697);
			_la = _input.LA(1);
			if ( !(_la==FLOAT_LITERAL || _la==HEX_FLOAT_LITERAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public ElementValuePairsContext elementValuePairs() {
			return getRuleContext(ElementValuePairsContext.class,0);
		}
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(699);
			match(AT);
			setState(700);
			qualifiedName();
			setState(707);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(701);
				match(LPAREN);
				setState(704);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					{
					setState(702);
					elementValuePairs();
					}
					break;
				case 2:
					{
					setState(703);
					elementValue();
					}
					break;
				}
				setState(706);
				match(RPAREN);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementValuePairsContext extends ParserRuleContext {
		public List<ElementValuePairContext> elementValuePair() {
			return getRuleContexts(ElementValuePairContext.class);
		}
		public ElementValuePairContext elementValuePair(int i) {
			return getRuleContext(ElementValuePairContext.class,i);
		}
		public ElementValuePairsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValuePairs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterElementValuePairs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitElementValuePairs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitElementValuePairs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementValuePairsContext elementValuePairs() throws RecognitionException {
		ElementValuePairsContext _localctx = new ElementValuePairsContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_elementValuePairs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(709);
			elementValuePair();
			setState(714);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(710);
				match(COMMA);
				setState(711);
				elementValuePair();
				}
				}
				setState(716);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementValuePairContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public ElementValuePairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValuePair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterElementValuePair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitElementValuePair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitElementValuePair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementValuePairContext elementValuePair() throws RecognitionException {
		ElementValuePairContext _localctx = new ElementValuePairContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_elementValuePair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(717);
			match(IDENTIFIER);
			setState(718);
			match(ASSIGN);
			setState(719);
			elementValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public ElementValueArrayInitializerContext elementValueArrayInitializer() {
			return getRuleContext(ElementValueArrayInitializerContext.class,0);
		}
		public ElementValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterElementValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitElementValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitElementValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementValueContext elementValue() throws RecognitionException {
		ElementValueContext _localctx = new ElementValueContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_elementValue);
		try {
			setState(724);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(721);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(722);
				annotation();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(723);
				elementValueArrayInitializer();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementValueArrayInitializerContext extends ParserRuleContext {
		public List<ElementValueContext> elementValue() {
			return getRuleContexts(ElementValueContext.class);
		}
		public ElementValueContext elementValue(int i) {
			return getRuleContext(ElementValueContext.class,i);
		}
		public ElementValueArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValueArrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterElementValueArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitElementValueArrayInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitElementValueArrayInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementValueArrayInitializerContext elementValueArrayInitializer() throws RecognitionException {
		ElementValueArrayInitializerContext _localctx = new ElementValueArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_elementValueArrayInitializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(726);
			match(LBRACE);
			setState(735);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << CHAR_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << LPAREN) | (1L << LBRACE))) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (LT - 72)) | (1L << (BANG - 72)) | (1L << (TILDE - 72)) | (1L << (INC - 72)) | (1L << (DEC - 72)) | (1L << (ADD - 72)) | (1L << (SUB - 72)) | (1L << (AT - 72)) | (1L << (IDENTIFIER - 72)))) != 0)) {
				{
				setState(727);
				elementValue();
				setState(732);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(728);
						match(COMMA);
						setState(729);
						elementValue();
						}
						} 
					}
					setState(734);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
				}
				}
			}

			setState(738);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(737);
				match(COMMA);
				}
			}

			setState(740);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationTypeDeclarationContext extends ParserRuleContext {
		public TerminalNode INTERFACE() { return getToken(JavaParser.INTERFACE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public AnnotationTypeBodyContext annotationTypeBody() {
			return getRuleContext(AnnotationTypeBodyContext.class,0);
		}
		public AnnotationTypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterAnnotationTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitAnnotationTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitAnnotationTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationTypeDeclarationContext annotationTypeDeclaration() throws RecognitionException {
		AnnotationTypeDeclarationContext _localctx = new AnnotationTypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_annotationTypeDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(742);
			match(AT);
			setState(743);
			match(INTERFACE);
			setState(744);
			match(IDENTIFIER);
			setState(745);
			annotationTypeBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationTypeBodyContext extends ParserRuleContext {
		public List<AnnotationTypeElementDeclarationContext> annotationTypeElementDeclaration() {
			return getRuleContexts(AnnotationTypeElementDeclarationContext.class);
		}
		public AnnotationTypeElementDeclarationContext annotationTypeElementDeclaration(int i) {
			return getRuleContext(AnnotationTypeElementDeclarationContext.class,i);
		}
		public AnnotationTypeBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterAnnotationTypeBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitAnnotationTypeBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitAnnotationTypeBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationTypeBodyContext annotationTypeBody() throws RecognitionException {
		AnnotationTypeBodyContext _localctx = new AnnotationTypeBodyContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_annotationTypeBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(747);
			match(LBRACE);
			setState(751);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOLATILE))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (SEMI - 67)) | (1L << (AT - 67)) | (1L << (IDENTIFIER - 67)))) != 0)) {
				{
				{
				setState(748);
				annotationTypeElementDeclaration();
				}
				}
				setState(753);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(754);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationTypeElementDeclarationContext extends ParserRuleContext {
		public AnnotationTypeElementRestContext annotationTypeElementRest() {
			return getRuleContext(AnnotationTypeElementRestContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public AnnotationTypeElementDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeElementDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterAnnotationTypeElementDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitAnnotationTypeElementDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitAnnotationTypeElementDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationTypeElementDeclarationContext annotationTypeElementDeclaration() throws RecognitionException {
		AnnotationTypeElementDeclarationContext _localctx = new AnnotationTypeElementDeclarationContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_annotationTypeElementDeclaration);
		try {
			int _alt;
			setState(764);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case CLASS:
			case DOUBLE:
			case ENUM:
			case FINAL:
			case FLOAT:
			case INT:
			case INTERFACE:
			case LONG:
			case NATIVE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOLATILE:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(759);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(756);
						modifier();
						}
						} 
					}
					setState(761);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
				}
				setState(762);
				annotationTypeElementRest();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(763);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationTypeElementRestContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public AnnotationMethodOrConstantRestContext annotationMethodOrConstantRest() {
			return getRuleContext(AnnotationMethodOrConstantRestContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public AnnotationTypeElementRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeElementRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterAnnotationTypeElementRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitAnnotationTypeElementRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitAnnotationTypeElementRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationTypeElementRestContext annotationTypeElementRest() throws RecognitionException {
		AnnotationTypeElementRestContext _localctx = new AnnotationTypeElementRestContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_annotationTypeElementRest);
		try {
			setState(786);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(766);
				typeType();
				setState(767);
				annotationMethodOrConstantRest();
				setState(768);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(770);
				classDeclaration();
				setState(772);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
				case 1:
					{
					setState(771);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(774);
				interfaceDeclaration();
				setState(776);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
				case 1:
					{
					setState(775);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(778);
				enumDeclaration();
				setState(780);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
				case 1:
					{
					setState(779);
					match(SEMI);
					}
					break;
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(782);
				annotationTypeDeclaration();
				setState(784);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
				case 1:
					{
					setState(783);
					match(SEMI);
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationMethodOrConstantRestContext extends ParserRuleContext {
		public AnnotationMethodRestContext annotationMethodRest() {
			return getRuleContext(AnnotationMethodRestContext.class,0);
		}
		public AnnotationConstantRestContext annotationConstantRest() {
			return getRuleContext(AnnotationConstantRestContext.class,0);
		}
		public AnnotationMethodOrConstantRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationMethodOrConstantRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterAnnotationMethodOrConstantRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitAnnotationMethodOrConstantRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitAnnotationMethodOrConstantRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationMethodOrConstantRestContext annotationMethodOrConstantRest() throws RecognitionException {
		AnnotationMethodOrConstantRestContext _localctx = new AnnotationMethodOrConstantRestContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_annotationMethodOrConstantRest);
		try {
			setState(790);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(788);
				annotationMethodRest();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(789);
				annotationConstantRest();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationMethodRestContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public AnnotationMethodRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationMethodRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterAnnotationMethodRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitAnnotationMethodRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitAnnotationMethodRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationMethodRestContext annotationMethodRest() throws RecognitionException {
		AnnotationMethodRestContext _localctx = new AnnotationMethodRestContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_annotationMethodRest);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(792);
			match(IDENTIFIER);
			setState(793);
			match(LPAREN);
			setState(794);
			match(RPAREN);
			setState(796);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(795);
				defaultValue();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationConstantRestContext extends ParserRuleContext {
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public AnnotationConstantRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationConstantRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterAnnotationConstantRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitAnnotationConstantRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitAnnotationConstantRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationConstantRestContext annotationConstantRest() throws RecognitionException {
		AnnotationConstantRestContext _localctx = new AnnotationConstantRestContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_annotationConstantRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(798);
			variableDeclarators();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultValueContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(JavaParser.DEFAULT, 0); }
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitDefaultValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitDefaultValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(800);
			match(DEFAULT);
			setState(801);
			elementValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(803);
			match(LBRACE);
			setState(807);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONTINUE) | (1L << DO) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << FOR) | (1L << IF) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NEW) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << RETURN) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SUPER) | (1L << SWITCH) | (1L << SYNCHRONIZED) | (1L << THIS) | (1L << THROW) | (1L << TRY) | (1L << VOID) | (1L << WHILE) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << CHAR_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << LPAREN) | (1L << LBRACE))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (SEMI - 67)) | (1L << (LT - 67)) | (1L << (BANG - 67)) | (1L << (TILDE - 67)) | (1L << (INC - 67)) | (1L << (DEC - 67)) | (1L << (ADD - 67)) | (1L << (SUB - 67)) | (1L << (AT - 67)) | (1L << (IDENTIFIER - 67)))) != 0)) {
				{
				{
				setState(804);
				blockStatement();
				}
				}
				setState(809);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(810);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockStatementContext extends ParserRuleContext {
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
	 
		public BlockStatementContext() { }
		public void copyFrom(BlockStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BlockStmtLocalVarDeclContext extends BlockStatementContext {
		public LocalVariableDeclarationContext localVariableDeclaration() {
			return getRuleContext(LocalVariableDeclarationContext.class,0);
		}
		public BlockStmtLocalVarDeclContext(BlockStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterBlockStmtLocalVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitBlockStmtLocalVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitBlockStmtLocalVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockStmtStmtContext extends BlockStatementContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public BlockStmtStmtContext(BlockStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterBlockStmtStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitBlockStmtStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitBlockStmtStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockStmtLocalTypeDeclContext extends BlockStatementContext {
		public LocalTypeDeclarationContext localTypeDeclaration() {
			return getRuleContext(LocalTypeDeclarationContext.class,0);
		}
		public BlockStmtLocalTypeDeclContext(BlockStatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterBlockStmtLocalTypeDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitBlockStmtLocalTypeDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitBlockStmtLocalTypeDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_blockStatement);
		try {
			setState(817);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
			case 1:
				_localctx = new BlockStmtLocalVarDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(812);
				localVariableDeclaration();
				setState(813);
				match(SEMI);
				}
				break;
			case 2:
				_localctx = new BlockStmtStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(815);
				statement();
				}
				break;
			case 3:
				_localctx = new BlockStmtLocalTypeDeclContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(816);
				localTypeDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocalVariableDeclarationContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public LocalVariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVariableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterLocalVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitLocalVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitLocalVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocalVariableDeclarationContext localVariableDeclaration() throws RecognitionException {
		LocalVariableDeclarationContext _localctx = new LocalVariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_localVariableDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(822);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(819);
					variableModifier();
					}
					} 
				}
				setState(824);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			}
			setState(825);
			typeType();
			setState(826);
			variableDeclarators();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocalTypeDeclarationContext extends ParserRuleContext {
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public LocalTypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localTypeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterLocalTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitLocalTypeDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitLocalTypeDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocalTypeDeclarationContext localTypeDeclaration() throws RecognitionException {
		LocalTypeDeclarationContext _localctx = new LocalTypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_localTypeDeclaration);
		int _la;
		try {
			setState(839);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case CLASS:
			case FINAL:
			case INTERFACE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case STATIC:
			case STRICTFP:
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(831);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP))) != 0) || _la==AT) {
					{
					{
					setState(828);
					classOrInterfaceModifier();
					}
					}
					setState(833);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(836);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CLASS:
					{
					setState(834);
					classDeclaration();
					}
					break;
				case INTERFACE:
					{
					setState(835);
					interfaceDeclaration();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(838);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SwitchStmtContext extends StatementContext {
		public TerminalNode SWITCH() { return getToken(JavaParser.SWITCH, 0); }
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public List<SwitchBlockStatementGroupContext> switchBlockStatementGroup() {
			return getRuleContexts(SwitchBlockStatementGroupContext.class);
		}
		public SwitchBlockStatementGroupContext switchBlockStatementGroup(int i) {
			return getRuleContext(SwitchBlockStatementGroupContext.class,i);
		}
		public List<SwitchLabelContext> switchLabel() {
			return getRuleContexts(SwitchLabelContext.class);
		}
		public SwitchLabelContext switchLabel(int i) {
			return getRuleContext(SwitchLabelContext.class,i);
		}
		public SwitchStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterSwitchStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitSwitchStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitSwitchStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockStmtContext extends StatementContext {
		public BlockContext blockLabel;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterBlockStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitBlockStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitBlockStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TryStmtContext extends StatementContext {
		public TerminalNode TRY() { return getToken(JavaParser.TRY, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FinallyBlockContext finallyBlock() {
			return getRuleContext(FinallyBlockContext.class,0);
		}
		public List<CatchClauseContext> catchClause() {
			return getRuleContexts(CatchClauseContext.class);
		}
		public CatchClauseContext catchClause(int i) {
			return getRuleContext(CatchClauseContext.class,i);
		}
		public TryStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterTryStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitTryStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitTryStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SynchronizedStmtContext extends StatementContext {
		public TerminalNode SYNCHRONIZED() { return getToken(JavaParser.SYNCHRONIZED, 0); }
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SynchronizedStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterSynchronizedStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitSynchronizedStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitSynchronizedStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionStmtContext extends StatementContext {
		public ExpressionContext statementExpression;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterExpressionStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitExpressionStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitExpressionStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueStmtContext extends StatementContext {
		public TerminalNode CONTINUE() { return getToken(JavaParser.CONTINUE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public ContinueStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitContinueStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStmtContext extends StatementContext {
		public TerminalNode IF() { return getToken(JavaParser.IF, 0); }
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(JavaParser.ELSE, 0); }
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SemicolonStmtContext extends StatementContext {
		public TerminalNode SEMI() { return getToken(JavaParser.SEMI, 0); }
		public SemicolonStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterSemicolonStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitSemicolonStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitSemicolonStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStmtContext extends StatementContext {
		public TerminalNode WHILE() { return getToken(JavaParser.WHILE, 0); }
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakStmtContext extends StatementContext {
		public TerminalNode BREAK() { return getToken(JavaParser.BREAK, 0); }
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public BreakStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssertStmtContext extends StatementContext {
		public TerminalNode ASSERT() { return getToken(JavaParser.ASSERT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AssertStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterAssertStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitAssertStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitAssertStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TryWithResourcesStmtContext extends StatementContext {
		public TerminalNode TRY() { return getToken(JavaParser.TRY, 0); }
		public ResourceSpecificationContext resourceSpecification() {
			return getRuleContext(ResourceSpecificationContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<CatchClauseContext> catchClause() {
			return getRuleContexts(CatchClauseContext.class);
		}
		public CatchClauseContext catchClause(int i) {
			return getRuleContext(CatchClauseContext.class,i);
		}
		public FinallyBlockContext finallyBlock() {
			return getRuleContext(FinallyBlockContext.class,0);
		}
		public TryWithResourcesStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterTryWithResourcesStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitTryWithResourcesStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitTryWithResourcesStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForStmtContext extends StatementContext {
		public TerminalNode FOR() { return getToken(JavaParser.FOR, 0); }
		public ForControlContext forControl() {
			return getRuleContext(ForControlContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ForStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStmtContext extends StatementContext {
		public TerminalNode RETURN() { return getToken(JavaParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThrowStmtContext extends StatementContext {
		public TerminalNode THROW() { return getToken(JavaParser.THROW, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ThrowStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterThrowStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitThrowStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitThrowStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoStmtContext extends StatementContext {
		public TerminalNode DO() { return getToken(JavaParser.DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(JavaParser.WHILE, 0); }
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public DoStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterDoStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitDoStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitDoStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LabeledStmtContext extends StatementContext {
		public Token identifierLabel;
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public LabeledStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterLabeledStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitLabeledStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitLabeledStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_statement);
		int _la;
		try {
			int _alt;
			setState(945);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				_localctx = new BlockStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(841);
				((BlockStmtContext)_localctx).blockLabel = block();
				}
				break;
			case 2:
				_localctx = new AssertStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(842);
				match(ASSERT);
				setState(843);
				expression(0);
				setState(846);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(844);
					match(COLON);
					setState(845);
					expression(0);
					}
				}

				setState(848);
				match(SEMI);
				}
				break;
			case 3:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(850);
				match(IF);
				setState(851);
				parExpression();
				setState(852);
				statement();
				setState(855);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					{
					setState(853);
					match(ELSE);
					setState(854);
					statement();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(857);
				match(FOR);
				setState(858);
				match(LPAREN);
				setState(859);
				forControl();
				setState(860);
				match(RPAREN);
				setState(861);
				statement();
				}
				break;
			case 5:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(863);
				match(WHILE);
				setState(864);
				parExpression();
				setState(865);
				statement();
				}
				break;
			case 6:
				_localctx = new DoStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(867);
				match(DO);
				setState(868);
				statement();
				setState(869);
				match(WHILE);
				setState(870);
				parExpression();
				setState(871);
				match(SEMI);
				}
				break;
			case 7:
				_localctx = new TryStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(873);
				match(TRY);
				setState(874);
				block();
				setState(884);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CATCH:
					{
					setState(876); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(875);
						catchClause();
						}
						}
						setState(878); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==CATCH );
					setState(881);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==FINALLY) {
						{
						setState(880);
						finallyBlock();
						}
					}

					}
					break;
				case FINALLY:
					{
					setState(883);
					finallyBlock();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 8:
				_localctx = new TryWithResourcesStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(886);
				match(TRY);
				setState(887);
				resourceSpecification();
				setState(888);
				block();
				setState(892);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CATCH) {
					{
					{
					setState(889);
					catchClause();
					}
					}
					setState(894);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(896);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FINALLY) {
					{
					setState(895);
					finallyBlock();
					}
				}

				}
				break;
			case 9:
				_localctx = new SwitchStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(898);
				match(SWITCH);
				setState(899);
				parExpression();
				setState(900);
				match(LBRACE);
				setState(904);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(901);
						switchBlockStatementGroup();
						}
						} 
					}
					setState(906);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
				}
				setState(910);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CASE || _la==DEFAULT) {
					{
					{
					setState(907);
					switchLabel();
					}
					}
					setState(912);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(913);
				match(RBRACE);
				}
				break;
			case 10:
				_localctx = new SynchronizedStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(915);
				match(SYNCHRONIZED);
				setState(916);
				parExpression();
				setState(917);
				block();
				}
				break;
			case 11:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(919);
				match(RETURN);
				setState(921);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << CHAR_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << LPAREN))) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (LT - 72)) | (1L << (BANG - 72)) | (1L << (TILDE - 72)) | (1L << (INC - 72)) | (1L << (DEC - 72)) | (1L << (ADD - 72)) | (1L << (SUB - 72)) | (1L << (AT - 72)) | (1L << (IDENTIFIER - 72)))) != 0)) {
					{
					setState(920);
					expression(0);
					}
				}

				setState(923);
				match(SEMI);
				}
				break;
			case 12:
				_localctx = new ThrowStmtContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(924);
				match(THROW);
				setState(925);
				expression(0);
				setState(926);
				match(SEMI);
				}
				break;
			case 13:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(928);
				match(BREAK);
				setState(930);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(929);
					match(IDENTIFIER);
					}
				}

				setState(932);
				match(SEMI);
				}
				break;
			case 14:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(933);
				match(CONTINUE);
				setState(935);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(934);
					match(IDENTIFIER);
					}
				}

				setState(937);
				match(SEMI);
				}
				break;
			case 15:
				_localctx = new SemicolonStmtContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(938);
				match(SEMI);
				}
				break;
			case 16:
				_localctx = new ExpressionStmtContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(939);
				((ExpressionStmtContext)_localctx).statementExpression = expression(0);
				setState(940);
				match(SEMI);
				}
				break;
			case 17:
				_localctx = new LabeledStmtContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(942);
				((LabeledStmtContext)_localctx).identifierLabel = match(IDENTIFIER);
				setState(943);
				match(COLON);
				setState(944);
				statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CatchClauseContext extends ParserRuleContext {
		public TerminalNode CATCH() { return getToken(JavaParser.CATCH, 0); }
		public CatchTypeContext catchType() {
			return getRuleContext(CatchTypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public CatchClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterCatchClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitCatchClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitCatchClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatchClauseContext catchClause() throws RecognitionException {
		CatchClauseContext _localctx = new CatchClauseContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_catchClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(947);
			match(CATCH);
			setState(948);
			match(LPAREN);
			setState(952);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(949);
				variableModifier();
				}
				}
				setState(954);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(955);
			catchType();
			setState(956);
			match(IDENTIFIER);
			setState(957);
			match(RPAREN);
			setState(958);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CatchTypeContext extends ParserRuleContext {
		public List<QualifiedNameContext> qualifiedName() {
			return getRuleContexts(QualifiedNameContext.class);
		}
		public QualifiedNameContext qualifiedName(int i) {
			return getRuleContext(QualifiedNameContext.class,i);
		}
		public CatchTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterCatchType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitCatchType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitCatchType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatchTypeContext catchType() throws RecognitionException {
		CatchTypeContext _localctx = new CatchTypeContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_catchType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(960);
			qualifiedName();
			setState(965);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITOR) {
				{
				{
				setState(961);
				match(BITOR);
				setState(962);
				qualifiedName();
				}
				}
				setState(967);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FinallyBlockContext extends ParserRuleContext {
		public TerminalNode FINALLY() { return getToken(JavaParser.FINALLY, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FinallyBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finallyBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterFinallyBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitFinallyBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitFinallyBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FinallyBlockContext finallyBlock() throws RecognitionException {
		FinallyBlockContext _localctx = new FinallyBlockContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_finallyBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(968);
			match(FINALLY);
			setState(969);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResourceSpecificationContext extends ParserRuleContext {
		public ResourcesContext resources() {
			return getRuleContext(ResourcesContext.class,0);
		}
		public ResourceSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resourceSpecification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterResourceSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitResourceSpecification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitResourceSpecification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResourceSpecificationContext resourceSpecification() throws RecognitionException {
		ResourceSpecificationContext _localctx = new ResourceSpecificationContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_resourceSpecification);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(971);
			match(LPAREN);
			setState(972);
			resources();
			setState(974);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(973);
				match(SEMI);
				}
			}

			setState(976);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResourcesContext extends ParserRuleContext {
		public List<ResourceContext> resource() {
			return getRuleContexts(ResourceContext.class);
		}
		public ResourceContext resource(int i) {
			return getRuleContext(ResourceContext.class,i);
		}
		public ResourcesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resources; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterResources(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitResources(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitResources(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResourcesContext resources() throws RecognitionException {
		ResourcesContext _localctx = new ResourcesContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_resources);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(978);
			resource();
			setState(983);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,114,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(979);
					match(SEMI);
					setState(980);
					resource();
					}
					} 
				}
				setState(985);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,114,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResourceContext extends ParserRuleContext {
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public ResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitResource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitResource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResourceContext resource() throws RecognitionException {
		ResourceContext _localctx = new ResourceContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_resource);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(989);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(986);
				variableModifier();
				}
				}
				setState(991);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(992);
			classOrInterfaceType();
			setState(993);
			variableDeclaratorId();
			setState(994);
			match(ASSIGN);
			setState(995);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchBlockStatementGroupContext extends ParserRuleContext {
		public List<SwitchLabelContext> switchLabel() {
			return getRuleContexts(SwitchLabelContext.class);
		}
		public SwitchLabelContext switchLabel(int i) {
			return getRuleContext(SwitchLabelContext.class,i);
		}
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public SwitchBlockStatementGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBlockStatementGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterSwitchBlockStatementGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitSwitchBlockStatementGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitSwitchBlockStatementGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchBlockStatementGroupContext switchBlockStatementGroup() throws RecognitionException {
		SwitchBlockStatementGroupContext _localctx = new SwitchBlockStatementGroupContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_switchBlockStatementGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(998); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(997);
				switchLabel();
				}
				}
				setState(1000); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CASE || _la==DEFAULT );
			setState(1003); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1002);
				blockStatement();
				}
				}
				setState(1005); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONTINUE) | (1L << DO) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << FOR) | (1L << IF) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NEW) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << RETURN) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SUPER) | (1L << SWITCH) | (1L << SYNCHRONIZED) | (1L << THIS) | (1L << THROW) | (1L << TRY) | (1L << VOID) | (1L << WHILE) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << CHAR_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << LPAREN) | (1L << LBRACE))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (SEMI - 67)) | (1L << (LT - 67)) | (1L << (BANG - 67)) | (1L << (TILDE - 67)) | (1L << (INC - 67)) | (1L << (DEC - 67)) | (1L << (ADD - 67)) | (1L << (SUB - 67)) | (1L << (AT - 67)) | (1L << (IDENTIFIER - 67)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchLabelContext extends ParserRuleContext {
		public ExpressionContext constantExpression;
		public Token enumConstantName;
		public TerminalNode CASE() { return getToken(JavaParser.CASE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public TerminalNode DEFAULT() { return getToken(JavaParser.DEFAULT, 0); }
		public SwitchLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterSwitchLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitSwitchLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitSwitchLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchLabelContext switchLabel() throws RecognitionException {
		SwitchLabelContext _localctx = new SwitchLabelContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_switchLabel);
		try {
			setState(1015);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1007);
				match(CASE);
				setState(1010);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,118,_ctx) ) {
				case 1:
					{
					setState(1008);
					((SwitchLabelContext)_localctx).constantExpression = expression(0);
					}
					break;
				case 2:
					{
					setState(1009);
					((SwitchLabelContext)_localctx).enumConstantName = match(IDENTIFIER);
					}
					break;
				}
				setState(1012);
				match(COLON);
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1013);
				match(DEFAULT);
				setState(1014);
				match(COLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForControlContext extends ParserRuleContext {
		public ExpressionListContext forUpdate;
		public EnhancedForControlContext enhancedForControl() {
			return getRuleContext(EnhancedForControlContext.class,0);
		}
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForControlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forControl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterForControl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitForControl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitForControl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForControlContext forControl() throws RecognitionException {
		ForControlContext _localctx = new ForControlContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_forControl);
		int _la;
		try {
			setState(1029);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,123,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1017);
				enhancedForControl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1019);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << CHAR_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << LPAREN))) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (LT - 72)) | (1L << (BANG - 72)) | (1L << (TILDE - 72)) | (1L << (INC - 72)) | (1L << (DEC - 72)) | (1L << (ADD - 72)) | (1L << (SUB - 72)) | (1L << (AT - 72)) | (1L << (IDENTIFIER - 72)))) != 0)) {
					{
					setState(1018);
					forInit();
					}
				}

				setState(1021);
				match(SEMI);
				setState(1023);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << CHAR_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << LPAREN))) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (LT - 72)) | (1L << (BANG - 72)) | (1L << (TILDE - 72)) | (1L << (INC - 72)) | (1L << (DEC - 72)) | (1L << (ADD - 72)) | (1L << (SUB - 72)) | (1L << (AT - 72)) | (1L << (IDENTIFIER - 72)))) != 0)) {
					{
					setState(1022);
					expression(0);
					}
				}

				setState(1025);
				match(SEMI);
				setState(1027);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << CHAR_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << LPAREN))) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (LT - 72)) | (1L << (BANG - 72)) | (1L << (TILDE - 72)) | (1L << (INC - 72)) | (1L << (DEC - 72)) | (1L << (ADD - 72)) | (1L << (SUB - 72)) | (1L << (AT - 72)) | (1L << (IDENTIFIER - 72)))) != 0)) {
					{
					setState(1026);
					((ForControlContext)_localctx).forUpdate = expressionList();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForInitContext extends ParserRuleContext {
		public LocalVariableDeclarationContext localVariableDeclaration() {
			return getRuleContext(LocalVariableDeclarationContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitForInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitForInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_forInit);
		try {
			setState(1033);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,124,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1031);
				localVariableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1032);
				expressionList();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnhancedForControlContext extends ParserRuleContext {
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public EnhancedForControlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enhancedForControl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterEnhancedForControl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitEnhancedForControl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitEnhancedForControl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnhancedForControlContext enhancedForControl() throws RecognitionException {
		EnhancedForControlContext _localctx = new EnhancedForControlContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_enhancedForControl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1038);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,125,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1035);
					variableModifier();
					}
					} 
				}
				setState(1040);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,125,_ctx);
			}
			setState(1041);
			typeType();
			setState(1042);
			variableDeclaratorId();
			setState(1043);
			match(COLON);
			setState(1044);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterParExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitParExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitParExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParExpressionContext parExpression() throws RecognitionException {
		ParExpressionContext _localctx = new ParExpressionContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_parExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1046);
			match(LPAREN);
			setState(1047);
			expression(0);
			setState(1048);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1050);
			expression(0);
			setState(1055);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1051);
				match(COMMA);
				setState(1052);
				expression(0);
				}
				}
				setState(1057);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Token prefix;
		public Token bop;
		public Token postfix;
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public TerminalNode NEW() { return getToken(JavaParser.NEW, 0); }
		public CreatorContext creator() {
			return getRuleContext(CreatorContext.class,0);
		}
		public TypeTypeContext typeType() {
			return getRuleContext(TypeTypeContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public LambdaExpressionContext lambdaExpression() {
			return getRuleContext(LambdaExpressionContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public TerminalNode THIS() { return getToken(JavaParser.THIS, 0); }
		public InnerCreatorContext innerCreator() {
			return getRuleContext(InnerCreatorContext.class,0);
		}
		public TerminalNode SUPER() { return getToken(JavaParser.SUPER, 0); }
		public SuperSuffixContext superSuffix() {
			return getRuleContext(SuperSuffixContext.class,0);
		}
		public ExplicitGenericInvocationContext explicitGenericInvocation() {
			return getRuleContext(ExplicitGenericInvocationContext.class,0);
		}
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode INSTANCEOF() { return getToken(JavaParser.INSTANCEOF, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 162;
		enterRecursionRule(_localctx, 162, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1088);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,130,_ctx) ) {
			case 1:
				{
				setState(1059);
				primary();
				}
				break;
			case 2:
				{
				setState(1060);
				match(NEW);
				setState(1061);
				creator();
				}
				break;
			case 3:
				{
				setState(1062);
				match(LPAREN);
				setState(1063);
				typeType();
				setState(1064);
				match(RPAREN);
				setState(1065);
				expression(21);
				}
				break;
			case 4:
				{
				setState(1067);
				((ExpressionContext)_localctx).prefix = _input.LT(1);
				_la = _input.LA(1);
				if ( !(((((_la - 83)) & ~0x3f) == 0 && ((1L << (_la - 83)) & ((1L << (INC - 83)) | (1L << (DEC - 83)) | (1L << (ADD - 83)) | (1L << (SUB - 83)))) != 0)) ) {
					((ExpressionContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1068);
				expression(19);
				}
				break;
			case 5:
				{
				setState(1069);
				((ExpressionContext)_localctx).prefix = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==BANG || _la==TILDE) ) {
					((ExpressionContext)_localctx).prefix = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1070);
				expression(18);
				}
				break;
			case 6:
				{
				setState(1071);
				lambdaExpression();
				}
				break;
			case 7:
				{
				setState(1072);
				typeType();
				setState(1073);
				match(COLONCOLON);
				setState(1079);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LT:
				case IDENTIFIER:
					{
					setState(1075);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(1074);
						typeArguments();
						}
					}

					setState(1077);
					match(IDENTIFIER);
					}
					break;
				case NEW:
					{
					setState(1078);
					match(NEW);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 8:
				{
				setState(1081);
				classType();
				setState(1082);
				match(COLONCOLON);
				setState(1084);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1083);
					typeArguments();
					}
				}

				setState(1086);
				match(NEW);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1175);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,137,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1173);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,136,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1090);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(1091);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 87)) & ~0x3f) == 0 && ((1L << (_la - 87)) & ((1L << (MUL - 87)) | (1L << (DIV - 87)) | (1L << (MOD - 87)))) != 0)) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1092);
						expression(18);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1093);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(1094);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1095);
						expression(17);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1096);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(1104);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,131,_ctx) ) {
						case 1:
							{
							setState(1097);
							match(LT);
							setState(1098);
							match(LT);
							}
							break;
						case 2:
							{
							setState(1099);
							match(GT);
							setState(1100);
							match(GT);
							setState(1101);
							match(GT);
							}
							break;
						case 3:
							{
							setState(1102);
							match(GT);
							setState(1103);
							match(GT);
							}
							break;
						}
						setState(1106);
						expression(16);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1107);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(1108);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (GT - 71)) | (1L << (LT - 71)) | (1L << (LE - 71)) | (1L << (GE - 71)))) != 0)) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1109);
						expression(15);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1110);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(1111);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1112);
						expression(13);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1113);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(1114);
						((ExpressionContext)_localctx).bop = match(BITAND);
						setState(1115);
						expression(12);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1116);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(1117);
						((ExpressionContext)_localctx).bop = match(CARET);
						setState(1118);
						expression(11);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1119);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(1120);
						((ExpressionContext)_localctx).bop = match(BITOR);
						setState(1121);
						expression(10);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1122);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(1123);
						((ExpressionContext)_localctx).bop = match(AND);
						setState(1124);
						expression(9);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1125);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(1126);
						((ExpressionContext)_localctx).bop = match(OR);
						setState(1127);
						expression(8);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1128);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(1129);
						((ExpressionContext)_localctx).bop = match(QUESTION);
						setState(1130);
						expression(0);
						setState(1131);
						match(COLON);
						setState(1132);
						expression(7);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1134);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(1135);
						((ExpressionContext)_localctx).bop = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (ASSIGN - 70)) | (1L << (ADD_ASSIGN - 70)) | (1L << (SUB_ASSIGN - 70)) | (1L << (MUL_ASSIGN - 70)) | (1L << (DIV_ASSIGN - 70)) | (1L << (AND_ASSIGN - 70)) | (1L << (OR_ASSIGN - 70)) | (1L << (XOR_ASSIGN - 70)) | (1L << (MOD_ASSIGN - 70)) | (1L << (LSHIFT_ASSIGN - 70)) | (1L << (RSHIFT_ASSIGN - 70)) | (1L << (URSHIFT_ASSIGN - 70)))) != 0)) ) {
							((ExpressionContext)_localctx).bop = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1136);
						expression(5);
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1137);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(1138);
						((ExpressionContext)_localctx).bop = match(DOT);
						setState(1149);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case IDENTIFIER:
							{
							setState(1139);
							match(IDENTIFIER);
							}
							break;
						case THIS:
							{
							setState(1140);
							match(THIS);
							}
							break;
						case NEW:
							{
							setState(1141);
							match(NEW);
							setState(1143);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==LT) {
								{
								setState(1142);
								nonWildcardTypeArguments();
								}
							}

							setState(1145);
							innerCreator();
							}
							break;
						case SUPER:
							{
							setState(1146);
							match(SUPER);
							setState(1147);
							superSuffix();
							}
							break;
						case LT:
							{
							setState(1148);
							explicitGenericInvocation();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1151);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(1152);
						match(LBRACK);
						setState(1153);
						expression(0);
						setState(1154);
						match(RBRACK);
						}
						break;
					case 15:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1156);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(1157);
						match(LPAREN);
						setState(1159);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << CHAR_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << LPAREN))) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (LT - 72)) | (1L << (BANG - 72)) | (1L << (TILDE - 72)) | (1L << (INC - 72)) | (1L << (DEC - 72)) | (1L << (ADD - 72)) | (1L << (SUB - 72)) | (1L << (AT - 72)) | (1L << (IDENTIFIER - 72)))) != 0)) {
							{
							setState(1158);
							expressionList();
							}
						}

						setState(1161);
						match(RPAREN);
						}
						break;
					case 16:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1162);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(1163);
						((ExpressionContext)_localctx).postfix = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==INC || _la==DEC) ) {
							((ExpressionContext)_localctx).postfix = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 17:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1164);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(1165);
						((ExpressionContext)_localctx).bop = match(INSTANCEOF);
						setState(1166);
						typeType();
						}
						break;
					case 18:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1167);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(1168);
						match(COLONCOLON);
						setState(1170);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==LT) {
							{
							setState(1169);
							typeArguments();
							}
						}

						setState(1172);
						match(IDENTIFIER);
						}
						break;
					}
					} 
				}
				setState(1177);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,137,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LambdaExpressionContext extends ParserRuleContext {
		public LambdaParametersContext lambdaParameters() {
			return getRuleContext(LambdaParametersContext.class,0);
		}
		public LambdaBodyContext lambdaBody() {
			return getRuleContext(LambdaBodyContext.class,0);
		}
		public LambdaExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterLambdaExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitLambdaExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitLambdaExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaExpressionContext lambdaExpression() throws RecognitionException {
		LambdaExpressionContext _localctx = new LambdaExpressionContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_lambdaExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1178);
			lambdaParameters();
			setState(1179);
			match(ARROW);
			setState(1180);
			lambdaBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaParametersContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(JavaParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(JavaParser.IDENTIFIER, i);
		}
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public LambdaParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterLambdaParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitLambdaParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitLambdaParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaParametersContext lambdaParameters() throws RecognitionException {
		LambdaParametersContext _localctx = new LambdaParametersContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_lambdaParameters);
		int _la;
		try {
			setState(1198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,140,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1182);
				match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1183);
				match(LPAREN);
				setState(1185);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << SHORT))) != 0) || _la==AT || _la==IDENTIFIER) {
					{
					setState(1184);
					formalParameterList();
					}
				}

				setState(1187);
				match(RPAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1188);
				match(LPAREN);
				setState(1189);
				match(IDENTIFIER);
				setState(1194);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1190);
					match(COMMA);
					setState(1191);
					match(IDENTIFIER);
					}
					}
					setState(1196);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1197);
				match(RPAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaBodyContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public LambdaBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterLambdaBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitLambdaBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitLambdaBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaBodyContext lambdaBody() throws RecognitionException {
		LambdaBodyContext _localctx = new LambdaBodyContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_lambdaBody);
		try {
			setState(1202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case SHORT:
			case SUPER:
			case THIS:
			case VOID:
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
			case BOOL_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case NULL_LITERAL:
			case LPAREN:
			case LT:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case AT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1200);
				expression(0);
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1201);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode THIS() { return getToken(JavaParser.THIS, 0); }
		public TerminalNode SUPER() { return getToken(JavaParser.SUPER, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public TypeTypeOrVoidContext typeTypeOrVoid() {
			return getRuleContext(TypeTypeOrVoidContext.class,0);
		}
		public TerminalNode CLASS() { return getToken(JavaParser.CLASS, 0); }
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() {
			return getRuleContext(ExplicitGenericInvocationSuffixContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_primary);
		try {
			setState(1222);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,143,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1204);
				match(LPAREN);
				setState(1205);
				expression(0);
				setState(1206);
				match(RPAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1208);
				match(THIS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1209);
				match(SUPER);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1210);
				literal();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1211);
				match(IDENTIFIER);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1212);
				typeTypeOrVoid();
				setState(1213);
				match(DOT);
				setState(1214);
				match(CLASS);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1216);
				nonWildcardTypeArguments();
				setState(1220);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case SUPER:
				case IDENTIFIER:
					{
					setState(1217);
					explicitGenericInvocationSuffix();
					}
					break;
				case THIS:
					{
					setState(1218);
					match(THIS);
					setState(1219);
					arguments();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassTypeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public ClassTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterClassType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitClassType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitClassType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassTypeContext classType() throws RecognitionException {
		ClassTypeContext _localctx = new ClassTypeContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_classType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1227);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,144,_ctx) ) {
			case 1:
				{
				setState(1224);
				classOrInterfaceType();
				setState(1225);
				match(DOT);
				}
				break;
			}
			setState(1232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(1229);
				annotation();
				}
				}
				setState(1234);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1235);
			match(IDENTIFIER);
			setState(1237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1236);
				typeArguments();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreatorContext extends ParserRuleContext {
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public CreatedNameContext createdName() {
			return getRuleContext(CreatedNameContext.class,0);
		}
		public ClassCreatorRestContext classCreatorRest() {
			return getRuleContext(ClassCreatorRestContext.class,0);
		}
		public ArrayCreatorRestContext arrayCreatorRest() {
			return getRuleContext(ArrayCreatorRestContext.class,0);
		}
		public CreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitCreator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitCreator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreatorContext creator() throws RecognitionException {
		CreatorContext _localctx = new CreatorContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_creator);
		try {
			setState(1248);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1239);
				nonWildcardTypeArguments();
				setState(1240);
				createdName();
				setState(1241);
				classCreatorRest();
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1243);
				createdName();
				setState(1246);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LBRACK:
					{
					setState(1244);
					arrayCreatorRest();
					}
					break;
				case LPAREN:
					{
					setState(1245);
					classCreatorRest();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreatedNameContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(JavaParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(JavaParser.IDENTIFIER, i);
		}
		public List<TypeArgumentsOrDiamondContext> typeArgumentsOrDiamond() {
			return getRuleContexts(TypeArgumentsOrDiamondContext.class);
		}
		public TypeArgumentsOrDiamondContext typeArgumentsOrDiamond(int i) {
			return getRuleContext(TypeArgumentsOrDiamondContext.class,i);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public CreatedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createdName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterCreatedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitCreatedName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitCreatedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreatedNameContext createdName() throws RecognitionException {
		CreatedNameContext _localctx = new CreatedNameContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_createdName);
		int _la;
		try {
			setState(1265);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1250);
				match(IDENTIFIER);
				setState(1252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1251);
					typeArgumentsOrDiamond();
					}
				}

				setState(1261);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(1254);
					match(DOT);
					setState(1255);
					match(IDENTIFIER);
					setState(1257);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(1256);
						typeArgumentsOrDiamond();
						}
					}

					}
					}
					setState(1263);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1264);
				primitiveType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InnerCreatorContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public ClassCreatorRestContext classCreatorRest() {
			return getRuleContext(ClassCreatorRestContext.class,0);
		}
		public NonWildcardTypeArgumentsOrDiamondContext nonWildcardTypeArgumentsOrDiamond() {
			return getRuleContext(NonWildcardTypeArgumentsOrDiamondContext.class,0);
		}
		public InnerCreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_innerCreator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterInnerCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitInnerCreator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitInnerCreator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InnerCreatorContext innerCreator() throws RecognitionException {
		InnerCreatorContext _localctx = new InnerCreatorContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_innerCreator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1267);
			match(IDENTIFIER);
			setState(1269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1268);
				nonWildcardTypeArgumentsOrDiamond();
				}
			}

			setState(1271);
			classCreatorRest();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayCreatorRestContext extends ParserRuleContext {
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayCreatorRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayCreatorRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterArrayCreatorRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitArrayCreatorRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitArrayCreatorRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayCreatorRestContext arrayCreatorRest() throws RecognitionException {
		ArrayCreatorRestContext _localctx = new ArrayCreatorRestContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_arrayCreatorRest);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1273);
			match(LBRACK);
			setState(1301);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RBRACK:
				{
				setState(1274);
				match(RBRACK);
				setState(1279);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LBRACK) {
					{
					{
					setState(1275);
					match(LBRACK);
					setState(1276);
					match(RBRACK);
					}
					}
					setState(1281);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1282);
				arrayInitializer();
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case SHORT:
			case SUPER:
			case THIS:
			case VOID:
			case DECIMAL_LITERAL:
			case HEX_LITERAL:
			case OCT_LITERAL:
			case BINARY_LITERAL:
			case FLOAT_LITERAL:
			case HEX_FLOAT_LITERAL:
			case BOOL_LITERAL:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case NULL_LITERAL:
			case LPAREN:
			case LT:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case AT:
			case IDENTIFIER:
				{
				setState(1283);
				expression(0);
				setState(1284);
				match(RBRACK);
				setState(1291);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,155,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1285);
						match(LBRACK);
						setState(1286);
						expression(0);
						setState(1287);
						match(RBRACK);
						}
						} 
					}
					setState(1293);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,155,_ctx);
				}
				setState(1298);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,156,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1294);
						match(LBRACK);
						setState(1295);
						match(RBRACK);
						}
						} 
					}
					setState(1300);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,156,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassCreatorRestContext extends ParserRuleContext {
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public ClassCreatorRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classCreatorRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterClassCreatorRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitClassCreatorRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitClassCreatorRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassCreatorRestContext classCreatorRest() throws RecognitionException {
		ClassCreatorRestContext _localctx = new ClassCreatorRestContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_classCreatorRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1303);
			arguments();
			setState(1305);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,158,_ctx) ) {
			case 1:
				{
				setState(1304);
				classBody();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExplicitGenericInvocationContext extends ParserRuleContext {
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() {
			return getRuleContext(ExplicitGenericInvocationSuffixContext.class,0);
		}
		public ExplicitGenericInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitGenericInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterExplicitGenericInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitExplicitGenericInvocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitExplicitGenericInvocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExplicitGenericInvocationContext explicitGenericInvocation() throws RecognitionException {
		ExplicitGenericInvocationContext _localctx = new ExplicitGenericInvocationContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_explicitGenericInvocation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1307);
			nonWildcardTypeArguments();
			setState(1308);
			explicitGenericInvocationSuffix();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeArgumentsOrDiamondContext extends ParserRuleContext {
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public TypeArgumentsOrDiamondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgumentsOrDiamond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterTypeArgumentsOrDiamond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitTypeArgumentsOrDiamond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitTypeArgumentsOrDiamond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeArgumentsOrDiamondContext typeArgumentsOrDiamond() throws RecognitionException {
		TypeArgumentsOrDiamondContext _localctx = new TypeArgumentsOrDiamondContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_typeArgumentsOrDiamond);
		try {
			setState(1313);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,159,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1310);
				match(LT);
				setState(1311);
				match(GT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1312);
				typeArguments();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonWildcardTypeArgumentsOrDiamondContext extends ParserRuleContext {
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public NonWildcardTypeArgumentsOrDiamondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonWildcardTypeArgumentsOrDiamond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterNonWildcardTypeArgumentsOrDiamond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitNonWildcardTypeArgumentsOrDiamond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitNonWildcardTypeArgumentsOrDiamond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonWildcardTypeArgumentsOrDiamondContext nonWildcardTypeArgumentsOrDiamond() throws RecognitionException {
		NonWildcardTypeArgumentsOrDiamondContext _localctx = new NonWildcardTypeArgumentsOrDiamondContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_nonWildcardTypeArgumentsOrDiamond);
		try {
			setState(1318);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,160,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1315);
				match(LT);
				setState(1316);
				match(GT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1317);
				nonWildcardTypeArguments();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonWildcardTypeArgumentsContext extends ParserRuleContext {
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public NonWildcardTypeArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonWildcardTypeArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterNonWildcardTypeArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitNonWildcardTypeArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitNonWildcardTypeArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonWildcardTypeArgumentsContext nonWildcardTypeArguments() throws RecognitionException {
		NonWildcardTypeArgumentsContext _localctx = new NonWildcardTypeArgumentsContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_nonWildcardTypeArguments);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1320);
			match(LT);
			setState(1321);
			typeList();
			setState(1322);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeListContext extends ParserRuleContext {
		public List<TypeTypeContext> typeType() {
			return getRuleContexts(TypeTypeContext.class);
		}
		public TypeTypeContext typeType(int i) {
			return getRuleContext(TypeTypeContext.class,i);
		}
		public TypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitTypeList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitTypeList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeListContext typeList() throws RecognitionException {
		TypeListContext _localctx = new TypeListContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_typeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1324);
			typeType();
			setState(1329);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1325);
				match(COMMA);
				setState(1326);
				typeType();
				}
				}
				setState(1331);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeTypeContext extends ParserRuleContext {
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public TypeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterTypeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitTypeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitTypeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeTypeContext typeType() throws RecognitionException {
		TypeTypeContext _localctx = new TypeTypeContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_typeType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1333);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AT) {
				{
				setState(1332);
				annotation();
				}
			}

			setState(1337);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(1335);
				classOrInterfaceType();
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				{
				setState(1336);
				primitiveType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1343);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,164,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1339);
					match(LBRACK);
					setState(1340);
					match(RBRACK);
					}
					} 
				}
				setState(1345);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,164,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimitiveTypeContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(JavaParser.BOOLEAN, 0); }
		public TerminalNode CHAR() { return getToken(JavaParser.CHAR, 0); }
		public TerminalNode BYTE() { return getToken(JavaParser.BYTE, 0); }
		public TerminalNode SHORT() { return getToken(JavaParser.SHORT, 0); }
		public TerminalNode INT() { return getToken(JavaParser.INT, 0); }
		public TerminalNode LONG() { return getToken(JavaParser.LONG, 0); }
		public TerminalNode FLOAT() { return getToken(JavaParser.FLOAT, 0); }
		public TerminalNode DOUBLE() { return getToken(JavaParser.DOUBLE, 0); }
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitPrimitiveType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitPrimitiveType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_primitiveType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1346);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << SHORT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeArgumentsContext extends ParserRuleContext {
		public List<TypeArgumentContext> typeArgument() {
			return getRuleContexts(TypeArgumentContext.class);
		}
		public TypeArgumentContext typeArgument(int i) {
			return getRuleContext(TypeArgumentContext.class,i);
		}
		public TypeArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterTypeArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitTypeArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitTypeArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeArgumentsContext typeArguments() throws RecognitionException {
		TypeArgumentsContext _localctx = new TypeArgumentsContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_typeArguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1348);
			match(LT);
			setState(1349);
			typeArgument();
			setState(1354);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1350);
				match(COMMA);
				setState(1351);
				typeArgument();
				}
				}
				setState(1356);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1357);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuperSuffixContext extends ParserRuleContext {
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public SuperSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterSuperSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitSuperSuffix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitSuperSuffix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuperSuffixContext superSuffix() throws RecognitionException {
		SuperSuffixContext _localctx = new SuperSuffixContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_superSuffix);
		try {
			setState(1365);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(1359);
				arguments();
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1360);
				match(DOT);
				setState(1361);
				match(IDENTIFIER);
				setState(1363);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,166,_ctx) ) {
				case 1:
					{
					setState(1362);
					arguments();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExplicitGenericInvocationSuffixContext extends ParserRuleContext {
		public TerminalNode SUPER() { return getToken(JavaParser.SUPER, 0); }
		public SuperSuffixContext superSuffix() {
			return getRuleContext(SuperSuffixContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JavaParser.IDENTIFIER, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitGenericInvocationSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterExplicitGenericInvocationSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitExplicitGenericInvocationSuffix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitExplicitGenericInvocationSuffix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() throws RecognitionException {
		ExplicitGenericInvocationSuffixContext _localctx = new ExplicitGenericInvocationSuffixContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_explicitGenericInvocationSuffix);
		try {
			setState(1371);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUPER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1367);
				match(SUPER);
				setState(1368);
				superSuffix();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1369);
				match(IDENTIFIER);
				setState(1370);
				arguments();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaParserListener ) ((JavaParserListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaParserVisitor ) return ((JavaParserVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1373);
			match(LPAREN);
			setState(1375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << DECIMAL_LITERAL) | (1L << HEX_LITERAL) | (1L << OCT_LITERAL) | (1L << BINARY_LITERAL) | (1L << FLOAT_LITERAL) | (1L << HEX_FLOAT_LITERAL) | (1L << BOOL_LITERAL) | (1L << CHAR_LITERAL) | (1L << STRING_LITERAL) | (1L << NULL_LITERAL) | (1L << LPAREN))) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (LT - 72)) | (1L << (BANG - 72)) | (1L << (TILDE - 72)) | (1L << (INC - 72)) | (1L << (DEC - 72)) | (1L << (ADD - 72)) | (1L << (SUB - 72)) | (1L << (AT - 72)) | (1L << (IDENTIFIER - 72)))) != 0)) {
				{
				setState(1374);
				expressionList();
				}
			}

			setState(1377);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 81:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 17);
		case 1:
			return precpred(_ctx, 16);
		case 2:
			return precpred(_ctx, 15);
		case 3:
			return precpred(_ctx, 14);
		case 4:
			return precpred(_ctx, 12);
		case 5:
			return precpred(_ctx, 11);
		case 6:
			return precpred(_ctx, 10);
		case 7:
			return precpred(_ctx, 9);
		case 8:
			return precpred(_ctx, 8);
		case 9:
			return precpred(_ctx, 7);
		case 10:
			return precpred(_ctx, 6);
		case 11:
			return precpred(_ctx, 5);
		case 12:
			return precpred(_ctx, 25);
		case 13:
			return precpred(_ctx, 24);
		case 14:
			return precpred(_ctx, 23);
		case 15:
			return precpred(_ctx, 20);
		case 16:
			return precpred(_ctx, 13);
		case 17:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3q\u0566\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\3\2\5\2\u00d2\n\2"+
		"\3\2\7\2\u00d5\n\2\f\2\16\2\u00d8\13\2\3\2\7\2\u00db\n\2\f\2\16\2\u00de"+
		"\13\2\3\2\3\2\3\3\7\3\u00e3\n\3\f\3\16\3\u00e6\13\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\5\4\u00ee\n\4\3\4\3\4\3\4\5\4\u00f3\n\4\3\4\3\4\3\5\7\5\u00f8\n"+
		"\5\f\5\16\5\u00fb\13\5\3\5\3\5\7\5\u00ff\n\5\f\5\16\5\u0102\13\5\3\5\3"+
		"\5\7\5\u0106\n\5\f\5\16\5\u0109\13\5\3\5\3\5\7\5\u010d\n\5\f\5\16\5\u0110"+
		"\13\5\3\5\3\5\5\5\u0114\n\5\3\6\3\6\3\6\3\6\3\6\5\6\u011b\n\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0125\n\7\3\b\3\b\5\b\u0129\n\b\3\t\3\t\3"+
		"\t\5\t\u012e\n\t\3\t\3\t\5\t\u0132\n\t\3\t\3\t\5\t\u0136\n\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\7\n\u013e\n\n\f\n\16\n\u0141\13\n\3\n\3\n\3\13\7\13\u0146"+
		"\n\13\f\13\16\13\u0149\13\13\3\13\3\13\3\13\5\13\u014e\n\13\3\f\3\f\3"+
		"\f\7\f\u0153\n\f\f\f\16\f\u0156\13\f\3\r\3\r\3\r\3\r\5\r\u015c\n\r\3\r"+
		"\3\r\5\r\u0160\n\r\3\r\5\r\u0163\n\r\3\r\5\r\u0166\n\r\3\r\3\r\3\16\3"+
		"\16\3\16\7\16\u016d\n\16\f\16\16\16\u0170\13\16\3\17\7\17\u0173\n\17\f"+
		"\17\16\17\u0176\13\17\3\17\3\17\5\17\u017a\n\17\3\17\5\17\u017d\n\17\3"+
		"\20\3\20\7\20\u0181\n\20\f\20\16\20\u0184\13\20\3\21\3\21\3\21\5\21\u0189"+
		"\n\21\3\21\3\21\5\21\u018d\n\21\3\21\3\21\3\22\3\22\7\22\u0193\n\22\f"+
		"\22\16\22\u0196\13\22\3\22\3\22\3\23\3\23\7\23\u019c\n\23\f\23\16\23\u019f"+
		"\13\23\3\23\3\23\3\24\3\24\5\24\u01a5\n\24\3\24\3\24\7\24\u01a9\n\24\f"+
		"\24\16\24\u01ac\13\24\3\24\5\24\u01af\n\24\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\5\25\u01ba\n\25\3\26\3\26\3\26\3\26\3\26\7\26\u01c1"+
		"\n\26\f\26\16\26\u01c4\13\26\3\26\3\26\5\26\u01c8\n\26\3\26\3\26\3\27"+
		"\3\27\5\27\u01ce\n\27\3\30\3\30\5\30\u01d2\n\30\3\31\3\31\3\31\3\32\3"+
		"\32\3\32\3\33\3\33\3\33\3\33\5\33\u01de\n\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\35\7\35\u01e7\n\35\f\35\16\35\u01ea\13\35\3\35\3\35\5\35\u01ee"+
		"\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u01f7\n\36\3\37\3\37\3\37"+
		"\3\37\7\37\u01fd\n\37\f\37\16\37\u0200\13\37\3\37\3\37\3 \3 \3 \7 \u0207"+
		"\n \f \16 \u020a\13 \3 \3 \3 \3!\7!\u0210\n!\f!\16!\u0213\13!\3!\3!\3"+
		"!\7!\u0218\n!\f!\16!\u021b\13!\3!\3!\5!\u021f\n!\3!\3!\3!\3!\7!\u0225"+
		"\n!\f!\16!\u0228\13!\3!\3!\5!\u022c\n!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\5"+
		"\"\u0236\n\"\3#\3#\3#\3$\3$\3$\7$\u023e\n$\f$\16$\u0241\13$\3%\3%\3%\5"+
		"%\u0246\n%\3&\3&\3&\7&\u024b\n&\f&\16&\u024e\13&\3\'\3\'\5\'\u0252\n\'"+
		"\3(\3(\3(\3(\7(\u0258\n(\f(\16(\u025b\13(\3(\5(\u025e\n(\5(\u0260\n(\3"+
		"(\3(\3)\3)\5)\u0266\n)\3)\3)\3)\5)\u026b\n)\7)\u026d\n)\f)\16)\u0270\13"+
		")\3*\3*\3*\3*\5*\u0276\n*\5*\u0278\n*\3+\3+\3+\7+\u027d\n+\f+\16+\u0280"+
		"\13+\3,\3,\5,\u0284\n,\3,\3,\3-\3-\3-\7-\u028b\n-\f-\16-\u028e\13-\3-"+
		"\3-\5-\u0292\n-\3-\5-\u0295\n-\3.\7.\u0298\n.\f.\16.\u029b\13.\3.\3.\3"+
		".\3/\7/\u02a1\n/\f/\16/\u02a4\13/\3/\3/\3/\3/\3\60\3\60\3\60\7\60\u02ad"+
		"\n\60\f\60\16\60\u02b0\13\60\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u02b8"+
		"\n\61\3\62\3\62\3\63\3\63\3\64\3\64\3\64\3\64\3\64\5\64\u02c3\n\64\3\64"+
		"\5\64\u02c6\n\64\3\65\3\65\3\65\7\65\u02cb\n\65\f\65\16\65\u02ce\13\65"+
		"\3\66\3\66\3\66\3\66\3\67\3\67\3\67\5\67\u02d7\n\67\38\38\38\38\78\u02dd"+
		"\n8\f8\168\u02e0\138\58\u02e2\n8\38\58\u02e5\n8\38\38\39\39\39\39\39\3"+
		":\3:\7:\u02f0\n:\f:\16:\u02f3\13:\3:\3:\3;\7;\u02f8\n;\f;\16;\u02fb\13"+
		";\3;\3;\5;\u02ff\n;\3<\3<\3<\3<\3<\3<\5<\u0307\n<\3<\3<\5<\u030b\n<\3"+
		"<\3<\5<\u030f\n<\3<\3<\5<\u0313\n<\5<\u0315\n<\3=\3=\5=\u0319\n=\3>\3"+
		">\3>\3>\5>\u031f\n>\3?\3?\3@\3@\3@\3A\3A\7A\u0328\nA\fA\16A\u032b\13A"+
		"\3A\3A\3B\3B\3B\3B\3B\5B\u0334\nB\3C\7C\u0337\nC\fC\16C\u033a\13C\3C\3"+
		"C\3C\3D\7D\u0340\nD\fD\16D\u0343\13D\3D\3D\5D\u0347\nD\3D\5D\u034a\nD"+
		"\3E\3E\3E\3E\3E\5E\u0351\nE\3E\3E\3E\3E\3E\3E\3E\5E\u035a\nE\3E\3E\3E"+
		"\3E\3E\3E\3E\3E\3E\3E\3E\3E\3E\3E\3E\3E\3E\3E\3E\6E\u036f\nE\rE\16E\u0370"+
		"\3E\5E\u0374\nE\3E\5E\u0377\nE\3E\3E\3E\3E\7E\u037d\nE\fE\16E\u0380\13"+
		"E\3E\5E\u0383\nE\3E\3E\3E\3E\7E\u0389\nE\fE\16E\u038c\13E\3E\7E\u038f"+
		"\nE\fE\16E\u0392\13E\3E\3E\3E\3E\3E\3E\3E\3E\5E\u039c\nE\3E\3E\3E\3E\3"+
		"E\3E\3E\5E\u03a5\nE\3E\3E\3E\5E\u03aa\nE\3E\3E\3E\3E\3E\3E\3E\3E\5E\u03b4"+
		"\nE\3F\3F\3F\7F\u03b9\nF\fF\16F\u03bc\13F\3F\3F\3F\3F\3F\3G\3G\3G\7G\u03c6"+
		"\nG\fG\16G\u03c9\13G\3H\3H\3H\3I\3I\3I\5I\u03d1\nI\3I\3I\3J\3J\3J\7J\u03d8"+
		"\nJ\fJ\16J\u03db\13J\3K\7K\u03de\nK\fK\16K\u03e1\13K\3K\3K\3K\3K\3K\3"+
		"L\6L\u03e9\nL\rL\16L\u03ea\3L\6L\u03ee\nL\rL\16L\u03ef\3M\3M\3M\5M\u03f5"+
		"\nM\3M\3M\3M\5M\u03fa\nM\3N\3N\5N\u03fe\nN\3N\3N\5N\u0402\nN\3N\3N\5N"+
		"\u0406\nN\5N\u0408\nN\3O\3O\5O\u040c\nO\3P\7P\u040f\nP\fP\16P\u0412\13"+
		"P\3P\3P\3P\3P\3P\3Q\3Q\3Q\3Q\3R\3R\3R\7R\u0420\nR\fR\16R\u0423\13R\3S"+
		"\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\5S\u0436\nS\3S\3S\5S"+
		"\u043a\nS\3S\3S\3S\5S\u043f\nS\3S\3S\5S\u0443\nS\3S\3S\3S\3S\3S\3S\3S"+
		"\3S\3S\3S\3S\3S\3S\3S\5S\u0453\nS\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S"+
		"\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S"+
		"\3S\3S\5S\u047a\nS\3S\3S\3S\3S\5S\u0480\nS\3S\3S\3S\3S\3S\3S\3S\3S\5S"+
		"\u048a\nS\3S\3S\3S\3S\3S\3S\3S\3S\3S\5S\u0495\nS\3S\7S\u0498\nS\fS\16"+
		"S\u049b\13S\3T\3T\3T\3T\3U\3U\3U\5U\u04a4\nU\3U\3U\3U\3U\3U\7U\u04ab\n"+
		"U\fU\16U\u04ae\13U\3U\5U\u04b1\nU\3V\3V\5V\u04b5\nV\3W\3W\3W\3W\3W\3W"+
		"\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\5W\u04c7\nW\5W\u04c9\nW\3X\3X\3X\5X\u04ce"+
		"\nX\3X\7X\u04d1\nX\fX\16X\u04d4\13X\3X\3X\5X\u04d8\nX\3Y\3Y\3Y\3Y\3Y\3"+
		"Y\3Y\5Y\u04e1\nY\5Y\u04e3\nY\3Z\3Z\5Z\u04e7\nZ\3Z\3Z\3Z\5Z\u04ec\nZ\7"+
		"Z\u04ee\nZ\fZ\16Z\u04f1\13Z\3Z\5Z\u04f4\nZ\3[\3[\5[\u04f8\n[\3[\3[\3\\"+
		"\3\\\3\\\3\\\7\\\u0500\n\\\f\\\16\\\u0503\13\\\3\\\3\\\3\\\3\\\3\\\3\\"+
		"\3\\\7\\\u050c\n\\\f\\\16\\\u050f\13\\\3\\\3\\\7\\\u0513\n\\\f\\\16\\"+
		"\u0516\13\\\5\\\u0518\n\\\3]\3]\5]\u051c\n]\3^\3^\3^\3_\3_\3_\5_\u0524"+
		"\n_\3`\3`\3`\5`\u0529\n`\3a\3a\3a\3a\3b\3b\3b\7b\u0532\nb\fb\16b\u0535"+
		"\13b\3c\5c\u0538\nc\3c\3c\5c\u053c\nc\3c\3c\7c\u0540\nc\fc\16c\u0543\13"+
		"c\3d\3d\3e\3e\3e\3e\7e\u054b\ne\fe\16e\u054e\13e\3e\3e\3f\3f\3f\3f\5f"+
		"\u0556\nf\5f\u0558\nf\3g\3g\3g\3g\5g\u055e\ng\3h\3h\5h\u0562\nh\3h\3h"+
		"\3h\2\3\u00a4i\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088"+
		"\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0"+
		"\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8"+
		"\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\2\16"+
		"\4\2\23\23**\3\2\658\3\29:\3\2UX\3\2KL\4\2YZ^^\3\2WX\4\2IJPQ\4\2OORR\4"+
		"\2HH_i\3\2UV\n\2\5\5\7\7\n\n\20\20\26\26\35\35\37\37\'\'\2\u05fe\2\u00d1"+
		"\3\2\2\2\4\u00e4\3\2\2\2\6\u00eb\3\2\2\2\b\u0113\3\2\2\2\n\u011a\3\2\2"+
		"\2\f\u0124\3\2\2\2\16\u0128\3\2\2\2\20\u012a\3\2\2\2\22\u0139\3\2\2\2"+
		"\24\u0147\3\2\2\2\26\u014f\3\2\2\2\30\u0157\3\2\2\2\32\u0169\3\2\2\2\34"+
		"\u0174\3\2\2\2\36\u017e\3\2\2\2 \u0185\3\2\2\2\"\u0190\3\2\2\2$\u0199"+
		"\3\2\2\2&\u01ae\3\2\2\2(\u01b9\3\2\2\2*\u01bb\3\2\2\2,\u01cd\3\2\2\2."+
		"\u01d1\3\2\2\2\60\u01d3\3\2\2\2\62\u01d6\3\2\2\2\64\u01d9\3\2\2\2\66\u01e1"+
		"\3\2\2\28\u01ed\3\2\2\2:\u01f6\3\2\2\2<\u01f8\3\2\2\2>\u0203\3\2\2\2@"+
		"\u0211\3\2\2\2B\u0235\3\2\2\2D\u0237\3\2\2\2F\u023a\3\2\2\2H\u0242\3\2"+
		"\2\2J\u0247\3\2\2\2L\u0251\3\2\2\2N\u0253\3\2\2\2P\u0263\3\2\2\2R\u0277"+
		"\3\2\2\2T\u0279\3\2\2\2V\u0281\3\2\2\2X\u0294\3\2\2\2Z\u0299\3\2\2\2\\"+
		"\u02a2\3\2\2\2^\u02a9\3\2\2\2`\u02b7\3\2\2\2b\u02b9\3\2\2\2d\u02bb\3\2"+
		"\2\2f\u02bd\3\2\2\2h\u02c7\3\2\2\2j\u02cf\3\2\2\2l\u02d6\3\2\2\2n\u02d8"+
		"\3\2\2\2p\u02e8\3\2\2\2r\u02ed\3\2\2\2t\u02fe\3\2\2\2v\u0314\3\2\2\2x"+
		"\u0318\3\2\2\2z\u031a\3\2\2\2|\u0320\3\2\2\2~\u0322\3\2\2\2\u0080\u0325"+
		"\3\2\2\2\u0082\u0333\3\2\2\2\u0084\u0338\3\2\2\2\u0086\u0349\3\2\2\2\u0088"+
		"\u03b3\3\2\2\2\u008a\u03b5\3\2\2\2\u008c\u03c2\3\2\2\2\u008e\u03ca\3\2"+
		"\2\2\u0090\u03cd\3\2\2\2\u0092\u03d4\3\2\2\2\u0094\u03df\3\2\2\2\u0096"+
		"\u03e8\3\2\2\2\u0098\u03f9\3\2\2\2\u009a\u0407\3\2\2\2\u009c\u040b\3\2"+
		"\2\2\u009e\u0410\3\2\2\2\u00a0\u0418\3\2\2\2\u00a2\u041c\3\2\2\2\u00a4"+
		"\u0442\3\2\2\2\u00a6\u049c\3\2\2\2\u00a8\u04b0\3\2\2\2\u00aa\u04b4\3\2"+
		"\2\2\u00ac\u04c8\3\2\2\2\u00ae\u04cd\3\2\2\2\u00b0\u04e2\3\2\2\2\u00b2"+
		"\u04f3\3\2\2\2\u00b4\u04f5\3\2\2\2\u00b6\u04fb\3\2\2\2\u00b8\u0519\3\2"+
		"\2\2\u00ba\u051d\3\2\2\2\u00bc\u0523\3\2\2\2\u00be\u0528\3\2\2\2\u00c0"+
		"\u052a\3\2\2\2\u00c2\u052e\3\2\2\2\u00c4\u0537\3\2\2\2\u00c6\u0544\3\2"+
		"\2\2\u00c8\u0546\3\2\2\2\u00ca\u0557\3\2\2\2\u00cc\u055d\3\2\2\2\u00ce"+
		"\u055f\3\2\2\2\u00d0\u00d2\5\4\3\2\u00d1\u00d0\3\2\2\2\u00d1\u00d2\3\2"+
		"\2\2\u00d2\u00d6\3\2\2\2\u00d3\u00d5\5\6\4\2\u00d4\u00d3\3\2\2\2\u00d5"+
		"\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00dc\3\2"+
		"\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00db\5\b\5\2\u00da\u00d9\3\2\2\2\u00db"+
		"\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00df\3\2"+
		"\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\7\2\2\3\u00e0\3\3\2\2\2\u00e1\u00e3"+
		"\5f\64\2\u00e2\u00e1\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4"+
		"\u00e5\3\2\2\2\u00e5\u00e7\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e8\7\""+
		"\2\2\u00e8\u00e9\5^\60\2\u00e9\u00ea\7E\2\2\u00ea\5\3\2\2\2\u00eb\u00ed"+
		"\7\33\2\2\u00ec\u00ee\7(\2\2\u00ed\u00ec\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee"+
		"\u00ef\3\2\2\2\u00ef\u00f2\5^\60\2\u00f0\u00f1\7G\2\2\u00f1\u00f3\7Y\2"+
		"\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5"+
		"\7E\2\2\u00f5\7\3\2\2\2\u00f6\u00f8\5\f\7\2\u00f7\u00f6\3\2\2\2\u00f8"+
		"\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fc\3\2"+
		"\2\2\u00fb\u00f9\3\2\2\2\u00fc\u0114\5\20\t\2\u00fd\u00ff\5\f\7\2\u00fe"+
		"\u00fd\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2"+
		"\2\2\u0101\u0103\3\2\2\2\u0102\u0100\3\2\2\2\u0103\u0114\5\30\r\2\u0104"+
		"\u0106\5\f\7\2\u0105\u0104\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3\2"+
		"\2\2\u0107\u0108\3\2\2\2\u0108\u010a\3\2\2\2\u0109\u0107\3\2\2\2\u010a"+
		"\u0114\5 \21\2\u010b\u010d\5\f\7\2\u010c\u010b\3\2\2\2\u010d\u0110\3\2"+
		"\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0111\3\2\2\2\u0110"+
		"\u010e\3\2\2\2\u0111\u0114\5p9\2\u0112\u0114\7E\2\2\u0113\u00f9\3\2\2"+
		"\2\u0113\u0100\3\2\2\2\u0113\u0107\3\2\2\2\u0113\u010e\3\2\2\2\u0113\u0112"+
		"\3\2\2\2\u0114\t\3\2\2\2\u0115\u011b\5\f\7\2\u0116\u011b\7 \2\2\u0117"+
		"\u011b\7,\2\2\u0118\u011b\7\60\2\2\u0119\u011b\7\63\2\2\u011a\u0115\3"+
		"\2\2\2\u011a\u0116\3\2\2\2\u011a\u0117\3\2\2\2\u011a\u0118\3\2\2\2\u011a"+
		"\u0119\3\2\2\2\u011b\13\3\2\2\2\u011c\u0125\5f\64\2\u011d\u0125\7%\2\2"+
		"\u011e\u0125\7$\2\2\u011f\u0125\7#\2\2\u0120\u0125\7(\2\2\u0121\u0125"+
		"\7\3\2\2\u0122\u0125\7\24\2\2\u0123\u0125\7)\2\2\u0124\u011c\3\2\2\2\u0124"+
		"\u011d\3\2\2\2\u0124\u011e\3\2\2\2\u0124\u011f\3\2\2\2\u0124\u0120\3\2"+
		"\2\2\u0124\u0121\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0123\3\2\2\2\u0125"+
		"\r\3\2\2\2\u0126\u0129\7\24\2\2\u0127\u0129\5f\64\2\u0128\u0126\3\2\2"+
		"\2\u0128\u0127\3\2\2\2\u0129\17\3\2\2\2\u012a\u012b\7\13\2\2\u012b\u012d"+
		"\7q\2\2\u012c\u012e\5\22\n\2\u012d\u012c\3\2\2\2\u012d\u012e\3\2\2\2\u012e"+
		"\u0131\3\2\2\2\u012f\u0130\7\23\2\2\u0130\u0132\5\u00c4c\2\u0131\u012f"+
		"\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0135\3\2\2\2\u0133\u0134\7\32\2\2"+
		"\u0134\u0136\5\u00c2b\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136"+
		"\u0137\3\2\2\2\u0137\u0138\5\"\22\2\u0138\21\3\2\2\2\u0139\u013a\7J\2"+
		"\2\u013a\u013f\5\24\13\2\u013b\u013c\7F\2\2\u013c\u013e\5\24\13\2\u013d"+
		"\u013b\3\2\2\2\u013e\u0141\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u0140\3\2"+
		"\2\2\u0140\u0142\3\2\2\2\u0141\u013f\3\2\2\2\u0142\u0143\7I\2\2\u0143"+
		"\23\3\2\2\2\u0144\u0146\5f\64\2\u0145\u0144\3\2\2\2\u0146\u0149\3\2\2"+
		"\2\u0147\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u014a\3\2\2\2\u0149\u0147"+
		"\3\2\2\2\u014a\u014d\7q\2\2\u014b\u014c\7\23\2\2\u014c\u014e\5\26\f\2"+
		"\u014d\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e\25\3\2\2\2\u014f\u0154"+
		"\5\u00c4c\2\u0150\u0151\7[\2\2\u0151\u0153\5\u00c4c\2\u0152\u0150\3\2"+
		"\2\2\u0153\u0156\3\2\2\2\u0154\u0152\3\2\2\2\u0154\u0155\3\2\2\2\u0155"+
		"\27\3\2\2\2\u0156\u0154\3\2\2\2\u0157\u0158\7\22\2\2\u0158\u015b\7q\2"+
		"\2\u0159\u015a\7\32\2\2\u015a\u015c\5\u00c2b\2\u015b\u0159\3\2\2\2\u015b"+
		"\u015c\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015f\7A\2\2\u015e\u0160\5\32"+
		"\16\2\u015f\u015e\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0162\3\2\2\2\u0161"+
		"\u0163\7F\2\2\u0162\u0161\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0165\3\2"+
		"\2\2\u0164\u0166\5\36\20\2\u0165\u0164\3\2\2\2\u0165\u0166\3\2\2\2\u0166"+
		"\u0167\3\2\2\2\u0167\u0168\7B\2\2\u0168\31\3\2\2\2\u0169\u016e\5\34\17"+
		"\2\u016a\u016b\7F\2\2\u016b\u016d\5\34\17\2\u016c\u016a\3\2\2\2\u016d"+
		"\u0170\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f\33\3\2\2"+
		"\2\u0170\u016e\3\2\2\2\u0171\u0173\5f\64\2\u0172\u0171\3\2\2\2\u0173\u0176"+
		"\3\2\2\2\u0174\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0177\3\2\2\2\u0176"+
		"\u0174\3\2\2\2\u0177\u0179\7q\2\2\u0178\u017a\5\u00ceh\2\u0179\u0178\3"+
		"\2\2\2\u0179\u017a\3\2\2\2\u017a\u017c\3\2\2\2\u017b\u017d\5\"\22\2\u017c"+
		"\u017b\3\2\2\2\u017c\u017d\3\2\2\2\u017d\35\3\2\2\2\u017e\u0182\7E\2\2"+
		"\u017f\u0181\5&\24\2\u0180\u017f\3\2\2\2\u0181\u0184\3\2\2\2\u0182\u0180"+
		"\3\2\2\2\u0182\u0183\3\2\2\2\u0183\37\3\2\2\2\u0184\u0182\3\2\2\2\u0185"+
		"\u0186\7\36\2\2\u0186\u0188\7q\2\2\u0187\u0189\5\22\n\2\u0188\u0187\3"+
		"\2\2\2\u0188\u0189\3\2\2\2\u0189\u018c\3\2\2\2\u018a\u018b\7\23\2\2\u018b"+
		"\u018d\5\u00c2b\2\u018c\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018e"+
		"\3\2\2\2\u018e\u018f\5$\23\2\u018f!\3\2\2\2\u0190\u0194\7A\2\2\u0191\u0193"+
		"\5&\24\2\u0192\u0191\3\2\2\2\u0193\u0196\3\2\2\2\u0194\u0192\3\2\2\2\u0194"+
		"\u0195\3\2\2\2\u0195\u0197\3\2\2\2\u0196\u0194\3\2\2\2\u0197\u0198\7B"+
		"\2\2\u0198#\3\2\2\2\u0199\u019d\7A\2\2\u019a\u019c\58\35\2\u019b\u019a"+
		"\3\2\2\2\u019c\u019f\3\2\2\2\u019d\u019b\3\2\2\2\u019d\u019e\3\2\2\2\u019e"+
		"\u01a0\3\2\2\2\u019f\u019d\3\2\2\2\u01a0\u01a1\7B\2\2\u01a1%\3\2\2\2\u01a2"+
		"\u01af\7E\2\2\u01a3\u01a5\7(\2\2\u01a4\u01a3\3\2\2\2\u01a4\u01a5\3\2\2"+
		"\2\u01a5\u01a6\3\2\2\2\u01a6\u01af\5\u0080A\2\u01a7\u01a9\5\n\6\2\u01a8"+
		"\u01a7\3\2\2\2\u01a9\u01ac\3\2\2\2\u01aa\u01a8\3\2\2\2\u01aa\u01ab\3\2"+
		"\2\2\u01ab\u01ad\3\2\2\2\u01ac\u01aa\3\2\2\2\u01ad\u01af\5(\25\2\u01ae"+
		"\u01a2\3\2\2\2\u01ae\u01a4\3\2\2\2\u01ae\u01aa\3\2\2\2\u01af\'\3\2\2\2"+
		"\u01b0\u01ba\5*\26\2\u01b1\u01ba\5\60\31\2\u01b2\u01ba\5\66\34\2\u01b3"+
		"\u01ba\5\64\33\2\u01b4\u01ba\5\62\32\2\u01b5\u01ba\5 \21\2\u01b6\u01ba"+
		"\5p9\2\u01b7\u01ba\5\20\t\2\u01b8\u01ba\5\30\r\2\u01b9\u01b0\3\2\2\2\u01b9"+
		"\u01b1\3\2\2\2\u01b9\u01b2\3\2\2\2\u01b9\u01b3\3\2\2\2\u01b9\u01b4\3\2"+
		"\2\2\u01b9\u01b5\3\2\2\2\u01b9\u01b6\3\2\2\2\u01b9\u01b7\3\2\2\2\u01b9"+
		"\u01b8\3\2\2\2\u01ba)\3\2\2\2\u01bb\u01bc\5.\30\2\u01bc\u01bd\7q\2\2\u01bd"+
		"\u01c2\5V,\2\u01be\u01bf\7C\2\2\u01bf\u01c1\7D\2\2\u01c0\u01be\3\2\2\2"+
		"\u01c1\u01c4\3\2\2\2\u01c2\u01c0\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c7"+
		"\3\2\2\2\u01c4\u01c2\3\2\2\2\u01c5\u01c6\7/\2\2\u01c6\u01c8\5T+\2\u01c7"+
		"\u01c5\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9\u01ca\5,"+
		"\27\2\u01ca+\3\2\2\2\u01cb\u01ce\5\u0080A\2\u01cc\u01ce\7E\2\2\u01cd\u01cb"+
		"\3\2\2\2\u01cd\u01cc\3\2\2\2\u01ce-\3\2\2\2\u01cf\u01d2\5\u00c4c\2\u01d0"+
		"\u01d2\7\62\2\2\u01d1\u01cf\3\2\2\2\u01d1\u01d0\3\2\2\2\u01d2/\3\2\2\2"+
		"\u01d3\u01d4\5\22\n\2\u01d4\u01d5\5*\26\2\u01d5\61\3\2\2\2\u01d6\u01d7"+
		"\5\22\n\2\u01d7\u01d8\5\64\33\2\u01d8\63\3\2\2\2\u01d9\u01da\7q\2\2\u01da"+
		"\u01dd\5V,\2\u01db\u01dc\7/\2\2\u01dc\u01de\5T+\2\u01dd\u01db\3\2\2\2"+
		"\u01dd\u01de\3\2\2\2\u01de\u01df\3\2\2\2\u01df\u01e0\5\u0080A\2\u01e0"+
		"\65\3\2\2\2\u01e1\u01e2\5\u00c4c\2\u01e2\u01e3\5F$\2\u01e3\u01e4\7E\2"+
		"\2\u01e4\67\3\2\2\2\u01e5\u01e7\5\n\6\2\u01e6\u01e5\3\2\2\2\u01e7\u01ea"+
		"\3\2\2\2\u01e8\u01e6\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01eb\3\2\2\2\u01ea"+
		"\u01e8\3\2\2\2\u01eb\u01ee\5:\36\2\u01ec\u01ee\7E\2\2\u01ed\u01e8\3\2"+
		"\2\2\u01ed\u01ec\3\2\2\2\u01ee9\3\2\2\2\u01ef\u01f7\5<\37\2\u01f0\u01f7"+
		"\5@!\2\u01f1\u01f7\5D#\2\u01f2\u01f7\5 \21\2\u01f3\u01f7\5p9\2\u01f4\u01f7"+
		"\5\20\t\2\u01f5\u01f7\5\30\r\2\u01f6\u01ef\3\2\2\2\u01f6\u01f0\3\2\2\2"+
		"\u01f6\u01f1\3\2\2\2\u01f6\u01f2\3\2\2\2\u01f6\u01f3\3\2\2\2\u01f6\u01f4"+
		"\3\2\2\2\u01f6\u01f5\3\2\2\2\u01f7;\3\2\2\2\u01f8\u01f9\5\u00c4c\2\u01f9"+
		"\u01fe\5> \2\u01fa\u01fb\7F\2\2\u01fb\u01fd\5> \2\u01fc\u01fa\3\2\2\2"+
		"\u01fd\u0200\3\2\2\2\u01fe\u01fc\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff\u0201"+
		"\3\2\2\2\u0200\u01fe\3\2\2\2\u0201\u0202\7E\2\2\u0202=\3\2\2\2\u0203\u0208"+
		"\7q\2\2\u0204\u0205\7C\2\2\u0205\u0207\7D\2\2\u0206\u0204\3\2\2\2\u0207"+
		"\u020a\3\2\2\2\u0208\u0206\3\2\2\2\u0208\u0209\3\2\2\2\u0209\u020b\3\2"+
		"\2\2\u020a\u0208\3\2\2\2\u020b\u020c\7H\2\2\u020c\u020d\5L\'\2\u020d?"+
		"\3\2\2\2\u020e\u0210\5B\"\2\u020f\u020e\3\2\2\2\u0210\u0213\3\2\2\2\u0211"+
		"\u020f\3\2\2\2\u0211\u0212\3\2\2\2\u0212\u021e\3\2\2\2\u0213\u0211\3\2"+
		"\2\2\u0214\u021f\5.\30\2\u0215\u0219\5\22\n\2\u0216\u0218\5f\64\2\u0217"+
		"\u0216\3\2\2\2\u0218\u021b\3\2\2\2\u0219\u0217\3\2\2\2\u0219\u021a\3\2"+
		"\2\2\u021a\u021c\3\2\2\2\u021b\u0219\3\2\2\2\u021c\u021d\5.\30\2\u021d"+
		"\u021f\3\2\2\2\u021e\u0214\3\2\2\2\u021e\u0215\3\2\2\2\u021f\u0220\3\2"+
		"\2\2\u0220\u0221\7q\2\2\u0221\u0226\5V,\2\u0222\u0223\7C\2\2\u0223\u0225"+
		"\7D\2\2\u0224\u0222\3\2\2\2\u0225\u0228\3\2\2\2\u0226\u0224\3\2\2\2\u0226"+
		"\u0227\3\2\2\2\u0227\u022b\3\2\2\2\u0228\u0226\3\2\2\2\u0229\u022a\7/"+
		"\2\2\u022a\u022c\5T+\2\u022b\u0229\3\2\2\2\u022b\u022c\3\2\2\2\u022c\u022d"+
		"\3\2\2\2\u022d\u022e\5,\27\2\u022eA\3\2\2\2\u022f\u0236\5f\64\2\u0230"+
		"\u0236\7%\2\2\u0231\u0236\7\3\2\2\u0232\u0236\7\16\2\2\u0233\u0236\7("+
		"\2\2\u0234\u0236\7)\2\2\u0235\u022f\3\2\2\2\u0235\u0230\3\2\2\2\u0235"+
		"\u0231\3\2\2\2\u0235\u0232\3\2\2\2\u0235\u0233\3\2\2\2\u0235\u0234\3\2"+
		"\2\2\u0236C\3\2\2\2\u0237\u0238\5\22\n\2\u0238\u0239\5@!\2\u0239E\3\2"+
		"\2\2\u023a\u023f\5H%\2\u023b\u023c\7F\2\2\u023c\u023e\5H%\2\u023d\u023b"+
		"\3\2\2\2\u023e\u0241\3\2\2\2\u023f\u023d\3\2\2\2\u023f\u0240\3\2\2\2\u0240"+
		"G\3\2\2\2\u0241\u023f\3\2\2\2\u0242\u0245\5J&\2\u0243\u0244\7H\2\2\u0244"+
		"\u0246\5L\'\2\u0245\u0243\3\2\2\2\u0245\u0246\3\2\2\2\u0246I\3\2\2\2\u0247"+
		"\u024c\7q\2\2\u0248\u0249\7C\2\2\u0249\u024b\7D\2\2\u024a\u0248\3\2\2"+
		"\2\u024b\u024e\3\2\2\2\u024c\u024a\3\2\2\2\u024c\u024d\3\2\2\2\u024dK"+
		"\3\2\2\2\u024e\u024c\3\2\2\2\u024f\u0252\5N(\2\u0250\u0252\5\u00a4S\2"+
		"\u0251\u024f\3\2\2\2\u0251\u0250\3\2\2\2\u0252M\3\2\2\2\u0253\u025f\7"+
		"A\2\2\u0254\u0259\5L\'\2\u0255\u0256\7F\2\2\u0256\u0258\5L\'\2\u0257\u0255"+
		"\3\2\2\2\u0258\u025b\3\2\2\2\u0259\u0257\3\2\2\2\u0259\u025a\3\2\2\2\u025a"+
		"\u025d\3\2\2\2\u025b\u0259\3\2\2\2\u025c\u025e\7F\2\2\u025d\u025c\3\2"+
		"\2\2\u025d\u025e\3\2\2\2\u025e\u0260\3\2\2\2\u025f\u0254\3\2\2\2\u025f"+
		"\u0260\3\2\2\2\u0260\u0261\3\2\2\2\u0261\u0262\7B\2\2\u0262O\3\2\2\2\u0263"+
		"\u0265\7q\2\2\u0264\u0266\5\u00c8e\2\u0265\u0264\3\2\2\2\u0265\u0266\3"+
		"\2\2\2\u0266\u026e\3\2\2\2\u0267\u0268\7G\2\2\u0268\u026a\7q\2\2\u0269"+
		"\u026b\5\u00c8e\2\u026a\u0269\3\2\2\2\u026a\u026b\3\2\2\2\u026b\u026d"+
		"\3\2\2\2\u026c\u0267\3\2\2\2\u026d\u0270\3\2\2\2\u026e\u026c\3\2\2\2\u026e"+
		"\u026f\3\2\2\2\u026fQ\3\2\2\2\u0270\u026e\3\2\2\2\u0271\u0278\5\u00c4"+
		"c\2\u0272\u0275\7M\2\2\u0273\u0274\t\2\2\2\u0274\u0276\5\u00c4c\2\u0275"+
		"\u0273\3\2\2\2\u0275\u0276\3\2\2\2\u0276\u0278\3\2\2\2\u0277\u0271\3\2"+
		"\2\2\u0277\u0272\3\2\2\2\u0278S\3\2\2\2\u0279\u027e\5^\60\2\u027a\u027b"+
		"\7F\2\2\u027b\u027d\5^\60\2\u027c\u027a\3\2\2\2\u027d\u0280\3\2\2\2\u027e"+
		"\u027c\3\2\2\2\u027e\u027f\3\2\2\2\u027fU\3\2\2\2\u0280\u027e\3\2\2\2"+
		"\u0281\u0283\7?\2\2\u0282\u0284\5X-\2\u0283\u0282\3\2\2\2\u0283\u0284"+
		"\3\2\2\2\u0284\u0285\3\2\2\2\u0285\u0286\7@\2\2\u0286W\3\2\2\2\u0287\u028c"+
		"\5Z.\2\u0288\u0289\7F\2\2\u0289\u028b\5Z.\2\u028a\u0288\3\2\2\2\u028b"+
		"\u028e\3\2\2\2\u028c\u028a\3\2\2\2\u028c\u028d\3\2\2\2\u028d\u0291\3\2"+
		"\2\2\u028e\u028c\3\2\2\2\u028f\u0290\7F\2\2\u0290\u0292\5\\/\2\u0291\u028f"+
		"\3\2\2\2\u0291\u0292\3\2\2\2\u0292\u0295\3\2\2\2\u0293\u0295\5\\/\2\u0294"+
		"\u0287\3\2\2\2\u0294\u0293\3\2\2\2\u0295Y\3\2\2\2\u0296\u0298\5\16\b\2"+
		"\u0297\u0296\3\2\2\2\u0298\u029b\3\2\2\2\u0299\u0297\3\2\2\2\u0299\u029a"+
		"\3\2\2\2\u029a\u029c\3\2\2\2\u029b\u0299\3\2\2\2\u029c\u029d\5\u00c4c"+
		"\2\u029d\u029e\5J&\2\u029e[\3\2\2\2\u029f\u02a1\5\16\b\2\u02a0\u029f\3"+
		"\2\2\2\u02a1\u02a4\3\2\2\2\u02a2\u02a0\3\2\2\2\u02a2\u02a3\3\2\2\2\u02a3"+
		"\u02a5\3\2\2\2\u02a4\u02a2\3\2\2\2\u02a5\u02a6\5\u00c4c\2\u02a6\u02a7"+
		"\7m\2\2\u02a7\u02a8\5J&\2\u02a8]\3\2\2\2\u02a9\u02ae\7q\2\2\u02aa\u02ab"+
		"\7G\2\2\u02ab\u02ad\7q\2\2\u02ac\u02aa\3\2\2\2\u02ad\u02b0\3\2\2\2\u02ae"+
		"\u02ac\3\2\2\2\u02ae\u02af\3\2\2\2\u02af_\3\2\2\2\u02b0\u02ae\3\2\2\2"+
		"\u02b1\u02b8\5b\62\2\u02b2\u02b8\5d\63\2\u02b3\u02b8\7<\2\2\u02b4\u02b8"+
		"\7=\2\2\u02b5\u02b8\7;\2\2\u02b6\u02b8\7>\2\2\u02b7\u02b1\3\2\2\2\u02b7"+
		"\u02b2\3\2\2\2\u02b7\u02b3\3\2\2\2\u02b7\u02b4\3\2\2\2\u02b7\u02b5\3\2"+
		"\2\2\u02b7\u02b6\3\2\2\2\u02b8a\3\2\2\2\u02b9\u02ba\t\3\2\2\u02bac\3\2"+
		"\2\2\u02bb\u02bc\t\4\2\2\u02bce\3\2\2\2\u02bd\u02be\7l\2\2\u02be\u02c5"+
		"\5^\60\2\u02bf\u02c2\7?\2\2\u02c0\u02c3\5h\65\2\u02c1\u02c3\5l\67\2\u02c2"+
		"\u02c0\3\2\2\2\u02c2\u02c1\3\2\2\2\u02c2\u02c3\3\2\2\2\u02c3\u02c4\3\2"+
		"\2\2\u02c4\u02c6\7@\2\2\u02c5\u02bf\3\2\2\2\u02c5\u02c6\3\2\2\2\u02c6"+
		"g\3\2\2\2\u02c7\u02cc\5j\66\2\u02c8\u02c9\7F\2\2\u02c9\u02cb\5j\66\2\u02ca"+
		"\u02c8\3\2\2\2\u02cb\u02ce\3\2\2\2\u02cc\u02ca\3\2\2\2\u02cc\u02cd\3\2"+
		"\2\2\u02cdi\3\2\2\2\u02ce\u02cc\3\2\2\2\u02cf\u02d0\7q\2\2\u02d0\u02d1"+
		"\7H\2\2\u02d1\u02d2\5l\67\2\u02d2k\3\2\2\2\u02d3\u02d7\5\u00a4S\2\u02d4"+
		"\u02d7\5f\64\2\u02d5\u02d7\5n8\2\u02d6\u02d3\3\2\2\2\u02d6\u02d4\3\2\2"+
		"\2\u02d6\u02d5\3\2\2\2\u02d7m\3\2\2\2\u02d8\u02e1\7A\2\2\u02d9\u02de\5"+
		"l\67\2\u02da\u02db\7F\2\2\u02db\u02dd\5l\67\2\u02dc\u02da\3\2\2\2\u02dd"+
		"\u02e0\3\2\2\2\u02de\u02dc\3\2\2\2\u02de\u02df\3\2\2\2\u02df\u02e2\3\2"+
		"\2\2\u02e0\u02de\3\2\2\2\u02e1\u02d9\3\2\2\2\u02e1\u02e2\3\2\2\2\u02e2"+
		"\u02e4\3\2\2\2\u02e3\u02e5\7F\2\2\u02e4\u02e3\3\2\2\2\u02e4\u02e5\3\2"+
		"\2\2\u02e5\u02e6\3\2\2\2\u02e6\u02e7\7B\2\2\u02e7o\3\2\2\2\u02e8\u02e9"+
		"\7l\2\2\u02e9\u02ea\7\36\2\2\u02ea\u02eb\7q\2\2\u02eb\u02ec\5r:\2\u02ec"+
		"q\3\2\2\2\u02ed\u02f1\7A\2\2\u02ee\u02f0\5t;\2\u02ef\u02ee\3\2\2\2\u02f0"+
		"\u02f3\3\2\2\2\u02f1\u02ef\3\2\2\2\u02f1\u02f2\3\2\2\2\u02f2\u02f4\3\2"+
		"\2\2\u02f3\u02f1\3\2\2\2\u02f4\u02f5\7B\2\2\u02f5s\3\2\2\2\u02f6\u02f8"+
		"\5\n\6\2\u02f7\u02f6\3\2\2\2\u02f8\u02fb\3\2\2\2\u02f9\u02f7\3\2\2\2\u02f9"+
		"\u02fa\3\2\2\2\u02fa\u02fc\3\2\2\2\u02fb\u02f9\3\2\2\2\u02fc\u02ff\5v"+
		"<\2\u02fd\u02ff\7E\2\2\u02fe\u02f9\3\2\2\2\u02fe\u02fd\3\2\2\2\u02ffu"+
		"\3\2\2\2\u0300\u0301\5\u00c4c\2\u0301\u0302\5x=\2\u0302\u0303\7E\2\2\u0303"+
		"\u0315\3\2\2\2\u0304\u0306\5\20\t\2\u0305\u0307\7E\2\2\u0306\u0305\3\2"+
		"\2\2\u0306\u0307\3\2\2\2\u0307\u0315\3\2\2\2\u0308\u030a\5 \21\2\u0309"+
		"\u030b\7E\2\2\u030a\u0309\3\2\2\2\u030a\u030b\3\2\2\2\u030b\u0315\3\2"+
		"\2\2\u030c\u030e\5\30\r\2\u030d\u030f\7E\2\2\u030e\u030d\3\2\2\2\u030e"+
		"\u030f\3\2\2\2\u030f\u0315\3\2\2\2\u0310\u0312\5p9\2\u0311\u0313\7E\2"+
		"\2\u0312\u0311\3\2\2\2\u0312\u0313\3\2\2\2\u0313\u0315\3\2\2\2\u0314\u0300"+
		"\3\2\2\2\u0314\u0304\3\2\2\2\u0314\u0308\3\2\2\2\u0314\u030c\3\2\2\2\u0314"+
		"\u0310\3\2\2\2\u0315w\3\2\2\2\u0316\u0319\5z>\2\u0317\u0319\5|?\2\u0318"+
		"\u0316\3\2\2\2\u0318\u0317\3\2\2\2\u0319y\3\2\2\2\u031a\u031b\7q\2\2\u031b"+
		"\u031c\7?\2\2\u031c\u031e\7@\2\2\u031d\u031f\5~@\2\u031e\u031d\3\2\2\2"+
		"\u031e\u031f\3\2\2\2\u031f{\3\2\2\2\u0320\u0321\5F$\2\u0321}\3\2\2\2\u0322"+
		"\u0323\7\16\2\2\u0323\u0324\5l\67\2\u0324\177\3\2\2\2\u0325\u0329\7A\2"+
		"\2\u0326\u0328\5\u0082B\2\u0327\u0326\3\2\2\2\u0328\u032b\3\2\2\2\u0329"+
		"\u0327\3\2\2\2\u0329\u032a\3\2\2\2\u032a\u032c\3\2\2\2\u032b\u0329\3\2"+
		"\2\2\u032c\u032d\7B\2\2\u032d\u0081\3\2\2\2\u032e\u032f\5\u0084C\2\u032f"+
		"\u0330\7E\2\2\u0330\u0334\3\2\2\2\u0331\u0334\5\u0088E\2\u0332\u0334\5"+
		"\u0086D\2\u0333\u032e\3\2\2\2\u0333\u0331\3\2\2\2\u0333\u0332\3\2\2\2"+
		"\u0334\u0083\3\2\2\2\u0335\u0337\5\16\b\2\u0336\u0335\3\2\2\2\u0337\u033a"+
		"\3\2\2\2\u0338\u0336\3\2\2\2\u0338\u0339\3\2\2\2\u0339\u033b\3\2\2\2\u033a"+
		"\u0338\3\2\2\2\u033b\u033c\5\u00c4c\2\u033c\u033d\5F$\2\u033d\u0085\3"+
		"\2\2\2\u033e\u0340\5\f\7\2\u033f\u033e\3\2\2\2\u0340\u0343\3\2\2\2\u0341"+
		"\u033f\3\2\2\2\u0341\u0342\3\2\2\2\u0342\u0346\3\2\2\2\u0343\u0341\3\2"+
		"\2\2\u0344\u0347\5\20\t\2\u0345\u0347\5 \21\2\u0346\u0344\3\2\2\2\u0346"+
		"\u0345\3\2\2\2\u0347\u034a\3\2\2\2\u0348\u034a\7E\2\2\u0349\u0341\3\2"+
		"\2\2\u0349\u0348\3\2\2\2\u034a\u0087\3\2\2\2\u034b\u03b4\5\u0080A\2\u034c"+
		"\u034d\7\4\2\2\u034d\u0350\5\u00a4S\2\u034e\u034f\7N\2\2\u034f\u0351\5"+
		"\u00a4S\2\u0350\u034e\3\2\2\2\u0350\u0351\3\2\2\2\u0351\u0352\3\2\2\2"+
		"\u0352\u0353\7E\2\2\u0353\u03b4\3\2\2\2\u0354\u0355\7\30\2\2\u0355\u0356"+
		"\5\u00a0Q\2\u0356\u0359\5\u0088E\2\u0357\u0358\7\21\2\2\u0358\u035a\5"+
		"\u0088E\2\u0359\u0357\3\2\2\2\u0359\u035a\3\2\2\2\u035a\u03b4\3\2\2\2"+
		"\u035b\u035c\7\27\2\2\u035c\u035d\7?\2\2\u035d\u035e\5\u009aN\2\u035e"+
		"\u035f\7@\2\2\u035f\u0360\5\u0088E\2\u0360\u03b4\3\2\2\2\u0361\u0362\7"+
		"\64\2\2\u0362\u0363\5\u00a0Q\2\u0363\u0364\5\u0088E\2\u0364\u03b4\3\2"+
		"\2\2\u0365\u0366\7\17\2\2\u0366\u0367\5\u0088E\2\u0367\u0368\7\64\2\2"+
		"\u0368\u0369\5\u00a0Q\2\u0369\u036a\7E\2\2\u036a\u03b4\3\2\2\2\u036b\u036c"+
		"\7\61\2\2\u036c\u0376\5\u0080A\2\u036d\u036f\5\u008aF\2\u036e\u036d\3"+
		"\2\2\2\u036f\u0370\3\2\2\2\u0370\u036e\3\2\2\2\u0370\u0371\3\2\2\2\u0371"+
		"\u0373\3\2\2\2\u0372\u0374\5\u008eH\2\u0373\u0372\3\2\2\2\u0373\u0374"+
		"\3\2\2\2\u0374\u0377\3\2\2\2\u0375\u0377\5\u008eH\2\u0376\u036e\3\2\2"+
		"\2\u0376\u0375\3\2\2\2\u0377\u03b4\3\2\2\2\u0378\u0379\7\61\2\2\u0379"+
		"\u037a\5\u0090I\2\u037a\u037e\5\u0080A\2\u037b\u037d\5\u008aF\2\u037c"+
		"\u037b\3\2\2\2\u037d\u0380\3\2\2\2\u037e\u037c\3\2\2\2\u037e\u037f\3\2"+
		"\2\2\u037f\u0382\3\2\2\2\u0380\u037e\3\2\2\2\u0381\u0383\5\u008eH\2\u0382"+
		"\u0381\3\2\2\2\u0382\u0383\3\2\2\2\u0383\u03b4\3\2\2\2\u0384\u0385\7+"+
		"\2\2\u0385\u0386\5\u00a0Q\2\u0386\u038a\7A\2\2\u0387\u0389\5\u0096L\2"+
		"\u0388\u0387\3\2\2\2\u0389\u038c\3\2\2\2\u038a\u0388\3\2\2\2\u038a\u038b"+
		"\3\2\2\2\u038b\u0390\3\2\2\2\u038c\u038a\3\2\2\2\u038d\u038f\5\u0098M"+
		"\2\u038e\u038d\3\2\2\2\u038f\u0392\3\2\2\2\u0390\u038e\3\2\2\2\u0390\u0391"+
		"\3\2\2\2\u0391\u0393\3\2\2\2\u0392\u0390\3\2\2\2\u0393\u0394\7B\2\2\u0394"+
		"\u03b4\3\2\2\2\u0395\u0396\7,\2\2\u0396\u0397\5\u00a0Q\2\u0397\u0398\5"+
		"\u0080A\2\u0398\u03b4\3\2\2\2\u0399\u039b\7&\2\2\u039a\u039c\5\u00a4S"+
		"\2\u039b\u039a\3\2\2\2\u039b\u039c\3\2\2\2\u039c\u039d\3\2\2\2\u039d\u03b4"+
		"\7E\2\2\u039e\u039f\7.\2\2\u039f\u03a0\5\u00a4S\2\u03a0\u03a1\7E\2\2\u03a1"+
		"\u03b4\3\2\2\2\u03a2\u03a4\7\6\2\2\u03a3\u03a5\7q\2\2\u03a4\u03a3\3\2"+
		"\2\2\u03a4\u03a5\3\2\2\2\u03a5\u03a6\3\2\2\2\u03a6\u03b4\7E\2\2\u03a7"+
		"\u03a9\7\r\2\2\u03a8\u03aa\7q\2\2\u03a9\u03a8\3\2\2\2\u03a9\u03aa\3\2"+
		"\2\2\u03aa\u03ab\3\2\2\2\u03ab\u03b4\7E\2\2\u03ac\u03b4\7E\2\2\u03ad\u03ae"+
		"\5\u00a4S\2\u03ae\u03af\7E\2\2\u03af\u03b4\3\2\2\2\u03b0\u03b1\7q\2\2"+
		"\u03b1\u03b2\7N\2\2\u03b2\u03b4\5\u0088E\2\u03b3\u034b\3\2\2\2\u03b3\u034c"+
		"\3\2\2\2\u03b3\u0354\3\2\2\2\u03b3\u035b\3\2\2\2\u03b3\u0361\3\2\2\2\u03b3"+
		"\u0365\3\2\2\2\u03b3\u036b\3\2\2\2\u03b3\u0378\3\2\2\2\u03b3\u0384\3\2"+
		"\2\2\u03b3\u0395\3\2\2\2\u03b3\u0399\3\2\2\2\u03b3\u039e\3\2\2\2\u03b3"+
		"\u03a2\3\2\2\2\u03b3\u03a7\3\2\2\2\u03b3\u03ac\3\2\2\2\u03b3\u03ad\3\2"+
		"\2\2\u03b3\u03b0\3\2\2\2\u03b4\u0089\3\2\2\2\u03b5\u03b6\7\t\2\2\u03b6"+
		"\u03ba\7?\2\2\u03b7\u03b9\5\16\b\2\u03b8\u03b7\3\2\2\2\u03b9\u03bc\3\2"+
		"\2\2\u03ba\u03b8\3\2\2\2\u03ba\u03bb\3\2\2\2\u03bb\u03bd\3\2\2\2\u03bc"+
		"\u03ba\3\2\2\2\u03bd\u03be\5\u008cG\2\u03be\u03bf\7q\2\2\u03bf\u03c0\7"+
		"@\2\2\u03c0\u03c1\5\u0080A\2\u03c1\u008b\3\2\2\2\u03c2\u03c7\5^\60\2\u03c3"+
		"\u03c4\7\\\2\2\u03c4\u03c6\5^\60\2\u03c5\u03c3\3\2\2\2\u03c6\u03c9\3\2"+
		"\2\2\u03c7\u03c5\3\2\2\2\u03c7\u03c8\3\2\2\2\u03c8\u008d\3\2\2\2\u03c9"+
		"\u03c7\3\2\2\2\u03ca\u03cb\7\25\2\2\u03cb\u03cc\5\u0080A\2\u03cc\u008f"+
		"\3\2\2\2\u03cd\u03ce\7?\2\2\u03ce\u03d0\5\u0092J\2\u03cf\u03d1\7E\2\2"+
		"\u03d0\u03cf\3\2\2\2\u03d0\u03d1\3\2\2\2\u03d1\u03d2\3\2\2\2\u03d2\u03d3"+
		"\7@\2\2\u03d3\u0091\3\2\2\2\u03d4\u03d9\5\u0094K\2\u03d5\u03d6\7E\2\2"+
		"\u03d6\u03d8\5\u0094K\2\u03d7\u03d5\3\2\2\2\u03d8\u03db\3\2\2\2\u03d9"+
		"\u03d7\3\2\2\2\u03d9\u03da\3\2\2\2\u03da\u0093\3\2\2\2\u03db\u03d9\3\2"+
		"\2\2\u03dc\u03de\5\16\b\2\u03dd\u03dc\3\2\2\2\u03de\u03e1\3\2\2\2\u03df"+
		"\u03dd\3\2\2\2\u03df\u03e0\3\2\2\2\u03e0\u03e2\3\2\2\2\u03e1\u03df\3\2"+
		"\2\2\u03e2\u03e3\5P)\2\u03e3\u03e4\5J&\2\u03e4\u03e5\7H\2\2\u03e5\u03e6"+
		"\5\u00a4S\2\u03e6\u0095\3\2\2\2\u03e7\u03e9\5\u0098M\2\u03e8\u03e7\3\2"+
		"\2\2\u03e9\u03ea\3\2\2\2\u03ea\u03e8\3\2\2\2\u03ea\u03eb\3\2\2\2\u03eb"+
		"\u03ed\3\2\2\2\u03ec\u03ee\5\u0082B\2\u03ed\u03ec\3\2\2\2\u03ee\u03ef"+
		"\3\2\2\2\u03ef\u03ed\3\2\2\2\u03ef\u03f0\3\2\2\2\u03f0\u0097\3\2\2\2\u03f1"+
		"\u03f4\7\b\2\2\u03f2\u03f5\5\u00a4S\2\u03f3\u03f5\7q\2\2\u03f4\u03f2\3"+
		"\2\2\2\u03f4\u03f3\3\2\2\2\u03f5\u03f6\3\2\2\2\u03f6\u03fa\7N\2\2\u03f7"+
		"\u03f8\7\16\2\2\u03f8\u03fa\7N\2\2\u03f9\u03f1\3\2\2\2\u03f9\u03f7\3\2"+
		"\2\2\u03fa\u0099\3\2\2\2\u03fb\u0408\5\u009eP\2\u03fc\u03fe\5\u009cO\2"+
		"\u03fd\u03fc\3\2\2\2\u03fd\u03fe\3\2\2\2\u03fe\u03ff\3\2\2\2\u03ff\u0401"+
		"\7E\2\2\u0400\u0402\5\u00a4S\2\u0401\u0400\3\2\2\2\u0401\u0402\3\2\2\2"+
		"\u0402\u0403\3\2\2\2\u0403\u0405\7E\2\2\u0404\u0406\5\u00a2R\2\u0405\u0404"+
		"\3\2\2\2\u0405\u0406\3\2\2\2\u0406\u0408\3\2\2\2\u0407\u03fb\3\2\2\2\u0407"+
		"\u03fd\3\2\2\2\u0408\u009b\3\2\2\2\u0409\u040c\5\u0084C\2\u040a\u040c"+
		"\5\u00a2R\2\u040b\u0409\3\2\2\2\u040b\u040a\3\2\2\2\u040c\u009d\3\2\2"+
		"\2\u040d\u040f\5\16\b\2\u040e\u040d\3\2\2\2\u040f\u0412\3\2\2\2\u0410"+
		"\u040e\3\2\2\2\u0410\u0411\3\2\2\2\u0411\u0413\3\2\2\2\u0412\u0410\3\2"+
		"\2\2\u0413\u0414\5\u00c4c\2\u0414\u0415\5J&\2\u0415\u0416\7N\2\2\u0416"+
		"\u0417\5\u00a4S\2\u0417\u009f\3\2\2\2\u0418\u0419\7?\2\2\u0419\u041a\5"+
		"\u00a4S\2\u041a\u041b\7@\2\2\u041b\u00a1\3\2\2\2\u041c\u0421\5\u00a4S"+
		"\2\u041d\u041e\7F\2\2\u041e\u0420\5\u00a4S\2\u041f\u041d\3\2\2\2\u0420"+
		"\u0423\3\2\2\2\u0421\u041f\3\2\2\2\u0421\u0422\3\2\2\2\u0422\u00a3\3\2"+
		"\2\2\u0423\u0421\3\2\2\2\u0424\u0425\bS\1\2\u0425\u0443\5\u00acW\2\u0426"+
		"\u0427\7!\2\2\u0427\u0443\5\u00b0Y\2\u0428\u0429\7?\2\2\u0429\u042a\5"+
		"\u00c4c\2\u042a\u042b\7@\2\2\u042b\u042c\5\u00a4S\27\u042c\u0443\3\2\2"+
		"\2\u042d\u042e\t\5\2\2\u042e\u0443\5\u00a4S\25\u042f\u0430\t\6\2\2\u0430"+
		"\u0443\5\u00a4S\24\u0431\u0443\5\u00a6T\2\u0432\u0433\5\u00c4c\2\u0433"+
		"\u0439\7k\2\2\u0434\u0436\5\u00c8e\2\u0435\u0434\3\2\2\2\u0435\u0436\3"+
		"\2\2\2\u0436\u0437\3\2\2\2\u0437\u043a\7q\2\2\u0438\u043a\7!\2\2\u0439"+
		"\u0435\3\2\2\2\u0439\u0438\3\2\2\2\u043a\u0443\3\2\2\2\u043b\u043c\5\u00ae"+
		"X\2\u043c\u043e\7k\2\2\u043d\u043f\5\u00c8e\2\u043e\u043d\3\2\2\2\u043e"+
		"\u043f\3\2\2\2\u043f\u0440\3\2\2\2\u0440\u0441\7!\2\2\u0441\u0443\3\2"+
		"\2\2\u0442\u0424\3\2\2\2\u0442\u0426\3\2\2\2\u0442\u0428\3\2\2\2\u0442"+
		"\u042d\3\2\2\2\u0442\u042f\3\2\2\2\u0442\u0431\3\2\2\2\u0442\u0432\3\2"+
		"\2\2\u0442\u043b\3\2\2\2\u0443\u0499\3\2\2\2\u0444\u0445\f\23\2\2\u0445"+
		"\u0446\t\7\2\2\u0446\u0498\5\u00a4S\24\u0447\u0448\f\22\2\2\u0448\u0449"+
		"\t\b\2\2\u0449\u0498\5\u00a4S\23\u044a\u0452\f\21\2\2\u044b\u044c\7J\2"+
		"\2\u044c\u0453\7J\2\2\u044d\u044e\7I\2\2\u044e\u044f\7I\2\2\u044f\u0453"+
		"\7I\2\2\u0450\u0451\7I\2\2\u0451\u0453\7I\2\2\u0452\u044b\3\2\2\2\u0452"+
		"\u044d\3\2\2\2\u0452\u0450\3\2\2\2\u0453\u0454\3\2\2\2\u0454\u0498\5\u00a4"+
		"S\22\u0455\u0456\f\20\2\2\u0456\u0457\t\t\2\2\u0457\u0498\5\u00a4S\21"+
		"\u0458\u0459\f\16\2\2\u0459\u045a\t\n\2\2\u045a\u0498\5\u00a4S\17\u045b"+
		"\u045c\f\r\2\2\u045c\u045d\7[\2\2\u045d\u0498\5\u00a4S\16\u045e\u045f"+
		"\f\f\2\2\u045f\u0460\7]\2\2\u0460\u0498\5\u00a4S\r\u0461\u0462\f\13\2"+
		"\2\u0462\u0463\7\\\2\2\u0463\u0498\5\u00a4S\f\u0464\u0465\f\n\2\2\u0465"+
		"\u0466\7S\2\2\u0466\u0498\5\u00a4S\13\u0467\u0468\f\t\2\2\u0468\u0469"+
		"\7T\2\2\u0469\u0498\5\u00a4S\n\u046a\u046b\f\b\2\2\u046b\u046c\7M\2\2"+
		"\u046c\u046d\5\u00a4S\2\u046d\u046e\7N\2\2\u046e\u046f\5\u00a4S\t\u046f"+
		"\u0498\3\2\2\2\u0470\u0471\f\7\2\2\u0471\u0472\t\13\2\2\u0472\u0498\5"+
		"\u00a4S\7\u0473\u0474\f\33\2\2\u0474\u047f\7G\2\2\u0475\u0480\7q\2\2\u0476"+
		"\u0480\7-\2\2\u0477\u0479\7!\2\2\u0478\u047a\5\u00c0a\2\u0479\u0478\3"+
		"\2\2\2\u0479\u047a\3\2\2\2\u047a\u047b\3\2\2\2\u047b\u0480\5\u00b4[\2"+
		"\u047c\u047d\7*\2\2\u047d\u0480\5\u00caf\2\u047e\u0480\5\u00ba^\2\u047f"+
		"\u0475\3\2\2\2\u047f\u0476\3\2\2\2\u047f\u0477\3\2\2\2\u047f\u047c\3\2"+
		"\2\2\u047f\u047e\3\2\2\2\u0480\u0498\3\2\2\2\u0481\u0482\f\32\2\2\u0482"+
		"\u0483\7C\2\2\u0483\u0484\5\u00a4S\2\u0484\u0485\7D\2\2\u0485\u0498\3"+
		"\2\2\2\u0486\u0487\f\31\2\2\u0487\u0489\7?\2\2\u0488\u048a\5\u00a2R\2"+
		"\u0489\u0488\3\2\2\2\u0489\u048a\3\2\2\2\u048a\u048b\3\2\2\2\u048b\u0498"+
		"\7@\2\2\u048c\u048d\f\26\2\2\u048d\u0498\t\f\2\2\u048e\u048f\f\17\2\2"+
		"\u048f\u0490\7\34\2\2\u0490\u0498\5\u00c4c\2\u0491\u0492\f\5\2\2\u0492"+
		"\u0494\7k\2\2\u0493\u0495\5\u00c8e\2\u0494\u0493\3\2\2\2\u0494\u0495\3"+
		"\2\2\2\u0495\u0496\3\2\2\2\u0496\u0498\7q\2\2\u0497\u0444\3\2\2\2\u0497"+
		"\u0447\3\2\2\2\u0497\u044a\3\2\2\2\u0497\u0455\3\2\2\2\u0497\u0458\3\2"+
		"\2\2\u0497\u045b\3\2\2\2\u0497\u045e\3\2\2\2\u0497\u0461\3\2\2\2\u0497"+
		"\u0464\3\2\2\2\u0497\u0467\3\2\2\2\u0497\u046a\3\2\2\2\u0497\u0470\3\2"+
		"\2\2\u0497\u0473\3\2\2\2\u0497\u0481\3\2\2\2\u0497\u0486\3\2\2\2\u0497"+
		"\u048c\3\2\2\2\u0497\u048e\3\2\2\2\u0497\u0491\3\2\2\2\u0498\u049b\3\2"+
		"\2\2\u0499\u0497\3\2\2\2\u0499\u049a\3\2\2\2\u049a\u00a5\3\2\2\2\u049b"+
		"\u0499\3\2\2\2\u049c\u049d\5\u00a8U\2\u049d\u049e\7j\2\2\u049e\u049f\5"+
		"\u00aaV\2\u049f\u00a7\3\2\2\2\u04a0\u04b1\7q\2\2\u04a1\u04a3\7?\2\2\u04a2"+
		"\u04a4\5X-\2\u04a3\u04a2\3\2\2\2\u04a3\u04a4\3\2\2\2\u04a4\u04a5\3\2\2"+
		"\2\u04a5\u04b1\7@\2\2\u04a6\u04a7\7?\2\2\u04a7\u04ac\7q\2\2\u04a8\u04a9"+
		"\7F\2\2\u04a9\u04ab\7q\2\2\u04aa\u04a8\3\2\2\2\u04ab\u04ae\3\2\2\2\u04ac"+
		"\u04aa\3\2\2\2\u04ac\u04ad\3\2\2\2\u04ad\u04af\3\2\2\2\u04ae\u04ac\3\2"+
		"\2\2\u04af\u04b1\7@\2\2\u04b0\u04a0\3\2\2\2\u04b0\u04a1\3\2\2\2\u04b0"+
		"\u04a6\3\2\2\2\u04b1\u00a9\3\2\2\2\u04b2\u04b5\5\u00a4S\2\u04b3\u04b5"+
		"\5\u0080A\2\u04b4\u04b2\3\2\2\2\u04b4\u04b3\3\2\2\2\u04b5\u00ab\3\2\2"+
		"\2\u04b6\u04b7\7?\2\2\u04b7\u04b8\5\u00a4S\2\u04b8\u04b9\7@\2\2\u04b9"+
		"\u04c9\3\2\2\2\u04ba\u04c9\7-\2\2\u04bb\u04c9\7*\2\2\u04bc\u04c9\5`\61"+
		"\2\u04bd\u04c9\7q\2\2\u04be\u04bf\5.\30\2\u04bf\u04c0\7G\2\2\u04c0\u04c1"+
		"\7\13\2\2\u04c1\u04c9\3\2\2\2\u04c2\u04c6\5\u00c0a\2\u04c3\u04c7\5\u00cc"+
		"g\2\u04c4\u04c5\7-\2\2\u04c5\u04c7\5\u00ceh\2\u04c6\u04c3\3\2\2\2\u04c6"+
		"\u04c4\3\2\2\2\u04c7\u04c9\3\2\2\2\u04c8\u04b6\3\2\2\2\u04c8\u04ba\3\2"+
		"\2\2\u04c8\u04bb\3\2\2\2\u04c8\u04bc\3\2\2\2\u04c8\u04bd\3\2\2\2\u04c8"+
		"\u04be\3\2\2\2\u04c8\u04c2\3\2\2\2\u04c9\u00ad\3\2\2\2\u04ca\u04cb\5P"+
		")\2\u04cb\u04cc\7G\2\2\u04cc\u04ce\3\2\2\2\u04cd\u04ca\3\2\2\2\u04cd\u04ce"+
		"\3\2\2\2\u04ce\u04d2\3\2\2\2\u04cf\u04d1\5f\64\2\u04d0\u04cf\3\2\2\2\u04d1"+
		"\u04d4\3\2\2\2\u04d2\u04d0\3\2\2\2\u04d2\u04d3\3\2\2\2\u04d3\u04d5\3\2"+
		"\2\2\u04d4\u04d2\3\2\2\2\u04d5\u04d7\7q\2\2\u04d6\u04d8\5\u00c8e\2\u04d7"+
		"\u04d6\3\2\2\2\u04d7\u04d8\3\2\2\2\u04d8\u00af\3\2\2\2\u04d9\u04da\5\u00c0"+
		"a\2\u04da\u04db\5\u00b2Z\2\u04db\u04dc\5\u00b8]\2\u04dc\u04e3\3\2\2\2"+
		"\u04dd\u04e0\5\u00b2Z\2\u04de\u04e1\5\u00b6\\\2\u04df\u04e1\5\u00b8]\2"+
		"\u04e0\u04de\3\2\2\2\u04e0\u04df\3\2\2\2\u04e1\u04e3\3\2\2\2\u04e2\u04d9"+
		"\3\2\2\2\u04e2\u04dd\3\2\2\2\u04e3\u00b1\3\2\2\2\u04e4\u04e6\7q\2\2\u04e5"+
		"\u04e7\5\u00bc_\2\u04e6\u04e5\3\2\2\2\u04e6\u04e7\3\2\2\2\u04e7\u04ef"+
		"\3\2\2\2\u04e8\u04e9\7G\2\2\u04e9\u04eb\7q\2\2\u04ea\u04ec\5\u00bc_\2"+
		"\u04eb\u04ea\3\2\2\2\u04eb\u04ec\3\2\2\2\u04ec\u04ee\3\2\2\2\u04ed\u04e8"+
		"\3\2\2\2\u04ee\u04f1\3\2\2\2\u04ef\u04ed\3\2\2\2\u04ef\u04f0\3\2\2\2\u04f0"+
		"\u04f4\3\2\2\2\u04f1\u04ef\3\2\2\2\u04f2\u04f4\5\u00c6d\2\u04f3\u04e4"+
		"\3\2\2\2\u04f3\u04f2\3\2\2\2\u04f4\u00b3\3\2\2\2\u04f5\u04f7\7q\2\2\u04f6"+
		"\u04f8\5\u00be`\2\u04f7\u04f6\3\2\2\2\u04f7\u04f8\3\2\2\2\u04f8\u04f9"+
		"\3\2\2\2\u04f9\u04fa\5\u00b8]\2\u04fa\u00b5\3\2\2\2\u04fb\u0517\7C\2\2"+
		"\u04fc\u0501\7D\2\2\u04fd\u04fe\7C\2\2\u04fe\u0500\7D\2\2\u04ff\u04fd"+
		"\3\2\2\2\u0500\u0503\3\2\2\2\u0501\u04ff\3\2\2\2\u0501\u0502\3\2\2\2\u0502"+
		"\u0504\3\2\2\2\u0503\u0501\3\2\2\2\u0504\u0518\5N(\2\u0505\u0506\5\u00a4"+
		"S\2\u0506\u050d\7D\2\2\u0507\u0508\7C\2\2\u0508\u0509\5\u00a4S\2\u0509"+
		"\u050a\7D\2\2\u050a\u050c\3\2\2\2\u050b\u0507\3\2\2\2\u050c\u050f\3\2"+
		"\2\2\u050d\u050b\3\2\2\2\u050d\u050e\3\2\2\2\u050e\u0514\3\2\2\2\u050f"+
		"\u050d\3\2\2\2\u0510\u0511\7C\2\2\u0511\u0513\7D\2\2\u0512\u0510\3\2\2"+
		"\2\u0513\u0516\3\2\2\2\u0514\u0512\3\2\2\2\u0514\u0515\3\2\2\2\u0515\u0518"+
		"\3\2\2\2\u0516\u0514\3\2\2\2\u0517\u04fc\3\2\2\2\u0517\u0505\3\2\2\2\u0518"+
		"\u00b7\3\2\2\2\u0519\u051b\5\u00ceh\2\u051a\u051c\5\"\22\2\u051b\u051a"+
		"\3\2\2\2\u051b\u051c\3\2\2\2\u051c\u00b9\3\2\2\2\u051d\u051e\5\u00c0a"+
		"\2\u051e\u051f\5\u00ccg\2\u051f\u00bb\3\2\2\2\u0520\u0521\7J\2\2\u0521"+
		"\u0524\7I\2\2\u0522\u0524\5\u00c8e\2\u0523\u0520\3\2\2\2\u0523\u0522\3"+
		"\2\2\2\u0524\u00bd\3\2\2\2\u0525\u0526\7J\2\2\u0526\u0529\7I\2\2\u0527"+
		"\u0529\5\u00c0a\2\u0528\u0525\3\2\2\2\u0528\u0527\3\2\2\2\u0529\u00bf"+
		"\3\2\2\2\u052a\u052b\7J\2\2\u052b\u052c\5\u00c2b\2\u052c\u052d\7I\2\2"+
		"\u052d\u00c1\3\2\2\2\u052e\u0533\5\u00c4c\2\u052f\u0530\7F\2\2\u0530\u0532"+
		"\5\u00c4c\2\u0531\u052f\3\2\2\2\u0532\u0535\3\2\2\2\u0533\u0531\3\2\2"+
		"\2\u0533\u0534\3\2\2\2\u0534\u00c3\3\2\2\2\u0535\u0533\3\2\2\2\u0536\u0538"+
		"\5f\64\2\u0537\u0536\3\2\2\2\u0537\u0538\3\2\2\2\u0538\u053b\3\2\2\2\u0539"+
		"\u053c\5P)\2\u053a\u053c\5\u00c6d\2\u053b\u0539\3\2\2\2\u053b\u053a\3"+
		"\2\2\2\u053c\u0541\3\2\2\2\u053d\u053e\7C\2\2\u053e\u0540\7D\2\2\u053f"+
		"\u053d\3\2\2\2\u0540\u0543\3\2\2\2\u0541\u053f\3\2\2\2\u0541\u0542\3\2"+
		"\2\2\u0542\u00c5\3\2\2\2\u0543\u0541\3\2\2\2\u0544\u0545\t\r\2\2\u0545"+
		"\u00c7\3\2\2\2\u0546\u0547\7J\2\2\u0547\u054c\5R*\2\u0548\u0549\7F\2\2"+
		"\u0549\u054b\5R*\2\u054a\u0548\3\2\2\2\u054b\u054e\3\2\2\2\u054c\u054a"+
		"\3\2\2\2\u054c\u054d\3\2\2\2\u054d\u054f\3\2\2\2\u054e\u054c\3\2\2\2\u054f"+
		"\u0550\7I\2\2\u0550\u00c9\3\2\2\2\u0551\u0558\5\u00ceh\2\u0552\u0553\7"+
		"G\2\2\u0553\u0555\7q\2\2\u0554\u0556\5\u00ceh\2\u0555\u0554\3\2\2\2\u0555"+
		"\u0556\3\2\2\2\u0556\u0558\3\2\2\2\u0557\u0551\3\2\2\2\u0557\u0552\3\2"+
		"\2\2\u0558\u00cb\3\2\2\2\u0559\u055a\7*\2\2\u055a\u055e\5\u00caf\2\u055b"+
		"\u055c\7q\2\2\u055c\u055e\5\u00ceh\2\u055d\u0559\3\2\2\2\u055d\u055b\3"+
		"\2\2\2\u055e\u00cd\3\2\2\2\u055f\u0561\7?\2\2\u0560\u0562\5\u00a2R\2\u0561"+
		"\u0560\3\2\2\2\u0561\u0562\3\2\2\2\u0562\u0563\3\2\2\2\u0563\u0564\7@"+
		"\2\2\u0564\u00cf\3\2\2\2\u00ac\u00d1\u00d6\u00dc\u00e4\u00ed\u00f2\u00f9"+
		"\u0100\u0107\u010e\u0113\u011a\u0124\u0128\u012d\u0131\u0135\u013f\u0147"+
		"\u014d\u0154\u015b\u015f\u0162\u0165\u016e\u0174\u0179\u017c\u0182\u0188"+
		"\u018c\u0194\u019d\u01a4\u01aa\u01ae\u01b9\u01c2\u01c7\u01cd\u01d1\u01dd"+
		"\u01e8\u01ed\u01f6\u01fe\u0208\u0211\u0219\u021e\u0226\u022b\u0235\u023f"+
		"\u0245\u024c\u0251\u0259\u025d\u025f\u0265\u026a\u026e\u0275\u0277\u027e"+
		"\u0283\u028c\u0291\u0294\u0299\u02a2\u02ae\u02b7\u02c2\u02c5\u02cc\u02d6"+
		"\u02de\u02e1\u02e4\u02f1\u02f9\u02fe\u0306\u030a\u030e\u0312\u0314\u0318"+
		"\u031e\u0329\u0333\u0338\u0341\u0346\u0349\u0350\u0359\u0370\u0373\u0376"+
		"\u037e\u0382\u038a\u0390\u039b\u03a4\u03a9\u03b3\u03ba\u03c7\u03d0\u03d9"+
		"\u03df\u03ea\u03ef\u03f4\u03f9\u03fd\u0401\u0405\u0407\u040b\u0410\u0421"+
		"\u0435\u0439\u043e\u0442\u0452\u0479\u047f\u0489\u0494\u0497\u0499\u04a3"+
		"\u04ac\u04b0\u04b4\u04c6\u04c8\u04cd\u04d2\u04d7\u04e0\u04e2\u04e6\u04eb"+
		"\u04ef\u04f3\u04f7\u0501\u050d\u0514\u0517\u051b\u0523\u0528\u0533\u0537"+
		"\u053b\u0541\u054c\u0555\u0557\u055d\u0561";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}