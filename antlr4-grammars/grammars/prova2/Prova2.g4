/**
 * https://prova.ws/index.html?page=gram.php
 */
grammar Prova2;

options {
	output=AST;
	ASTLabelType=ProvaAST;
}

tokens {
	DOT = '.';
	PLUS 	= '+' ;
	MINUS	= '-' ;
	MULT	= '*' ;
	DIV	= '/' ;
	COMMA = ',';
	IF = ':-';
	CUT = '!';
	OPEN = '(';
	CLOSE = ')';
	BAR = '|';
	BRA = '[';
	KET = ']';
	EQUAL = '=';
	NOT_EQUAL1 = '<>';
	NOT_EQUAL2 = '!=';
	LT = '<';
	GT = '>';
	LE = '<=';
	GE = '>=';
	CLAUSE;
	RULEBASE;
	STATEMENT;
	QUERY;
	LITERAL;
	METADATA;
	RELATION;
	SEMANTIC_ATTACHMENT;
	ARITHMETIC_RELATION;
	PREDICATE;
	TERM;
	INSTANCE_JAVA_CALL;
	STATIC_JAVA_CALL;
	LIST_BODY;
	PROVA_LIST;
	ARGS;
	TYPED_VARIABLE;
	USWORD;
	QUALIFIED_JAVA_CLASS;
	ANNOTATION;
	REM = 'mod';
	DOLLARWORD;
	GUARD;
}

@header {
       package ws.prova.parser;

	import ws.prova.parser2.ProvaAST;
}

@lexer::header {
       package ws.prova.parser;
}

@members {
    public static void main(String[] args) throws Exception {
        ProvaLexer lex = new ProvaLexer(new ANTLRFileStream(args[0]));
       	CommonTokenStream tokens = new CommonTokenStream(lex);

        ProvaParser parser = new ProvaParser(tokens);
        try {
            ProvaParser.rulebase_return r = parser.rulebase();
            CommonTree tree = (CommonTree) r.getTree();
            for( int i=0; i<tree.getChildCount(); i++ ) {
            	Tree statement = tree.getChild(i);
                System.out.println( statement.getLine() + ": " + statement.toStringTree() );
            }
        } catch (RecognitionException e)  {
            e.printStackTrace();
        }
    }
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

rulebase 
	:	statement* NEWLINE* EOF-> ^(RULEBASE statement*);

statement 
	:	NEWLINE* stat end_of_statement -> ^(STATEMENT stat);

end_of_statement
	:	NEWLINE* DOT (NEWLINE | EOF);
		
stat 	:	clause
		| query;

query 	:	IF NEWLINE* query_predicate NEWLINE* '(' NEWLINE* relation NEWLINE* ')' -> ^(QUERY query_predicate relation);

query_predicate 
	:	constant;

clause 	:	metadata? relation (IF literals)? -> ^(CLAUSE metadata? relation literals?);

metadata
	:	(annotation NEWLINE*)+ -> ^(METADATA annotation+)
;

annotation
	:	'@' LCWORD ('(' value (COMMA value)* ')')? -> ^(ANNOTATION LCWORD value*)
;
	
value 
	:	LCWORD | UCWORD | string
;

literals 	:	literal (COMMA literal)* -> ^(LITERAL literal+);

literal 	:	NEWLINE* m=metadata? r=relation g=guard? -> ^(RELATION metadata? relation guard?)
			| NEWLINE* semantic_attachment -> ^(SEMANTIC_ATTACHMENT semantic_attachment)
			| NEWLINE* cut -> ^(CUT cut);

cut	:	CUT;

binary_operator
	:	EQUAL | not_equal | LT | GT | LE | GE | PLUS
	;

relation 
	:	predicate '(' list_body ')' -> ^(PREDICATE predicate list_body);

guard
	:	'[' literals ']' -> ^(GUARD literals)
	;

list_body
 	:	(terms ('|' list_tail)?)? NEWLINE* -> ^(LIST_BODY (terms list_tail?)?);

list_tail 
	:	variable | prova_list;

left_term 
	:	constant | variable | number | prova_list;

right_term 
	:	(MINUS? (constant | variable)) | number | prova_list;

terms 	:	NEWLINE* term (NEWLINE* ',' NEWLINE* term)* -> ^(TERM term+);

term	:	left_term | func_term;

func_term
	:	func args;
	
qualified_java_class 
	:	((LCWORD DOT)+ UCWORD) -> ^(QUALIFIED_JAVA_CLASS (LCWORD DOT)+ UCWORD)
;
	
prova_list 
	:	'[' list_body ']' -> ^(PROVA_LIST list_body);

args 	:	'(' list_body ')' -> ^(ARGS list_body);

semantic_attachment 
	:	predicate_java_call | binary_operation;

binary_operation
options {
	backtrack=true;
}
	:	left_term EQUAL!
		(
			constructor_java_call
			| predicate_java_call
			| (left_term (MINUS^|PLUS^|MULT^|DIV^|REM^) left_term)
		)
		| left_term binary_operator right_term
	;

constructor_java_call
	:	(qualified_java_class | UCWORD) args
	;

predicate_java_call
	:	static_java_call | instance_java_call
	;

instance_java_call
	:	instance_call args -> ^(INSTANCE_JAVA_CALL instance_call args)
	;

static_java_call
	:	static_call args -> ^(STATIC_JAVA_CALL static_call args)
	;

method 	:	LCWORD;

func	:	LCWORD | UCWORD | string | typed_variable;

predicate 
	:	LCWORD | UCWORD | string;

variable:	UCWORD | USWORD | typed_variable | DOLLARWORD;

// Not currently allowed
expr	: aterm ( ( PLUS | MINUS )  aterm )* ;
	
// Not currently allowed
aterm	: number ( ( MULT | DIV ) number)* ;

word	:	LCWORD | UCWORD;

// The parser does not know exactly what it is.
// It could be in fact a public field in a class.
// The actial decision is done by the tree parser ProvaWalker
typed_variable 
	:
		qualified_java_class DOT word -> ^(TYPED_VARIABLE qualified_java_class word)
		| UCWORD DOT word -> ^(TYPED_VARIABLE UCWORD word)
;

// This case can be reassessed by the tree walker when 'variable' is in fact
//    the name of a class from default packages java.lang or ws.prova
instance_call
	:	variable DOT LCWORD -> variable LCWORD
;
	
static_call
	:	(qualified_java_class DOT LCWORD) -> qualified_java_class LCWORD
;

pos_number	:	INT_LITERAL | LONG_LITERAL | float_literal/*FLOAT_LITERAL*/;

number
	:	('-')? pos_number;

constant	:
	LCWORD | string | qualified_java_class;

string	:	STRING1 | STRING2;

not_equal 
	:	NOT_EQUAL1 | NOT_EQUAL2;

// Must use a parser here because of the conflict with end_of_statement
float_literal 	:	INT_LITERAL DOT INT_LITERAL EXPONENT?;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

EXPONENT
	:	'e' '-'? DIGIT+
;
LONG_LITERAL	:	DIGIT+ 'L';

INT_LITERAL:
    DIGIT+
;

NEWLINE :	('%' (~('\r'|'\n') )*)? ('\r'? '\n')?;

fragment LC 	:	'a'..'z';

fragment UC 	:	'A'..'Z';

LCWORD 	:	LC WORD;

DOLLARWORD
	:	DOLLAR WORD;

fragment WORD
	: (LC | UC | UNDERSCORE | ':' | DIGIT)*;
	
fragment UNDERSCORE
	:	'_'
	;

fragment DOLLAR
	:	'$'
	;

UCWORD 	:	UC (LC | UC | UNDERSCORE | ':' | DIGIT)*;

USWORD 	:	UNDERSCORE (LC | UC | UNDERSCORE | DIGIT)*;

ML_COMMENT
   :   '/*' (options {greedy=false;} : .)* '*/' {$channel=HIDDEN;}
    ;

WS : ( '\t' | ' ' | '\u000C' )+ { $channel = HIDDEN; } ;

fragment DIGIT	: '0'..'9' ;

STRING1	:	'\'' CLEAN_STRING* (STRING2 CLEAN_STRING*)* '\'';

STRING2	:	'"' CLEAN_STRING* (STRING1 CLEAN_STRING*)* '"';

CLEAN_STRING
	:	~('\''|'\"') | CHAR_ESC;

fragment CHAR_ESC	returns [Character ret]:
    '\\'
    ( 'n'  { $ret='\n'; }
    | 'r'  { $ret='\r'; }
    | 't'  { $ret='\t'; }
    | 'b'  { $ret='\b'; }
    | 'f'  { $ret='\f'; }
    | '\"' { $ret='\"'; }
    | '\'' { $ret='\''; }
    | '\\' { $ret='\\'; }
    )
;