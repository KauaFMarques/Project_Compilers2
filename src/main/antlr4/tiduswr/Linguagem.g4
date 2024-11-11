grammar Linguagem;

print: PC_PRINT '(' STRING ')';
inicial: print;

PC_PRINT: 'print';
ESCAPE: '\\';
STRING: '"' (ESCAPE | ~["\n])* '"';
WS: [ \r\n\t] -> skip;