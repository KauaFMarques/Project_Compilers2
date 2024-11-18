grammar Linguagem;

programa: comando+;

comando: declaracao
       | atribuicao
       | impressao
       | entrada
       | if_stmt
       | while_stmt
       ;

declaracao: 'var' ID ('=' expr)? ';';
atribuicao: ID '=' expr ';';
impressao: 'print' '(' (expr | STRING) ')' ';';
entrada: 'input' '(' ID ')' ';';

if_stmt: 'if' '(' expr_bool ')' bloco ('else' bloco)?;
while_stmt: 'while' '(' expr_bool ')' bloco;

bloco: '{' comando* '}';

expr_bool: expr_bool ('and' | 'or') expr_bool
         | expr_rel
         | 'true'
         | 'false'
         ;

expr_rel: expr op_rel expr;
op_rel: '<' | '>' | '<=' | '>=' | '==' | '!=';

expr: termo (('+'|'-') termo)*;

termo: fator (('*'|'/') fator)*;

fator: potencia ('^' potencia)*;

potencia: NUM
        | ID
        | '(' expr ')'
        ;

// Tokens
ID: [a-zA-Z_][a-zA-Z0-9_]*;
NUM: [0-9]+('.'[0-9]+)?;
STRING: '"' (~["\r\n] | '\\"')* '"';
WS: [ \t\r\n]+ -> skip;
COMMENT: '//' ~[\r\n]* -> skip;