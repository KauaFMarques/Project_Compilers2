package tiduswr;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GeradorPCode extends LinguagemBaseVisitor<Void> {
    private Map<String, Integer> variaveis;  // Mapeia variáveis para endereços
    private int proxEndereco;                // Próximo endereço de memória disponível
    private int labelCount;                  // Contador para gerar labels únicos
    private Stack<String> breakLabels;       // Stack para labels de break em loops aninhados

    public GeradorPCode() {
        this.variaveis = new HashMap<>();
        this.proxEndereco = 0;
        this.labelCount = 0;
        this.breakLabels = new Stack<>();
    }

    private String novoLabel() {
        return "L" + (labelCount++);
    }

    @Override
    public Void visitPrograma(LinguagemParser.ProgramaContext ctx) {
        // Visita todos os comandos do programa
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitDeclaracao(LinguagemParser.DeclaracaoContext ctx) {
        String id = ctx.ID().getText();
        
        // Verifica se variável já foi declarada
        if (variaveis.containsKey(id)) {
            throw new RuntimeException("Erro: Variável '" + id + "' já declarada");
        }

        // Reserva endereço para a variável
        variaveis.put(id, proxEndereco++);

        // Se tem inicialização, gera código para ela
        if (ctx.expr() != null) {
            visit(ctx.expr());           // Avalia a expressão
            System.out.println("lda #" + variaveis.get(id));  // Carrega endereço
            System.out.println("sto");   // Armazena valor
        }

        return null;
    }

    @Override
    public Void visitAtribuicao(LinguagemParser.AtribuicaoContext ctx) {
        String id = ctx.ID().getText();
        
        // Verifica se variável existe
        if (!variaveis.containsKey(id)) {
            throw new RuntimeException("Erro: Variável '" + id + "' não declarada");
        }

        visit(ctx.expr());               // Avalia a expressão
        System.out.println("lda #" + variaveis.get(id));  // Carrega endereço
        System.out.println("sto");       // Armazena valor

        return null;
    }

    @Override
    public Void visitImpressao(LinguagemParser.ImpressaoContext ctx) {
        if (ctx.expr() != null) {
            visit(ctx.expr());           // Avalia a expressão
        } else if (ctx.STRING() != null) {
            // Carrega string literal
            System.out.println("ldc " + ctx.STRING().getText());
        }
        System.out.println("wri");       // Imprime o valor

        return null;
    }

    @Override
    public Void visitEntrada(LinguagemParser.EntradaContext ctx) {
        String id = ctx.ID().getText();
        
        // Verifica se variável existe
        if (!variaveis.containsKey(id)) {
            throw new RuntimeException("Erro: Variável '" + id + "' não declarada");
        }

        System.out.println("rd");        // Lê valor da entrada
        System.out.println("lda #" + variaveis.get(id));  // Carrega endereço
        System.out.println("sto");       // Armazena valor

        return null;
    }

    @Override
    public Void visitIf_stmt(LinguagemParser.If_stmtContext ctx) {
        String endLabel = novoLabel();   // Label para fim do if

        visit(ctx.expr_bool());          // Avalia condição
        System.out.println("fjp " + endLabel);  // Pula se falso

        visit(ctx.bloco());             // Visita bloco de código

        System.out.println(endLabel + ":");  // Marca fim do if

        return null;
    }

    @Override
    public Void visitWhile_stmt(LinguagemParser.While_stmtContext ctx) {
        String startLabel = novoLabel();  // Label início do while
        String endLabel = novoLabel();    // Label fim do while
        
        breakLabels.push(endLabel);      // Guarda label para possíveis breaks

        System.out.println(startLabel + ":");  // Marca início do loop
        
        visit(ctx.expr_bool());          // Avalia condição
        System.out.println("fjp " + endLabel);  // Pula se falso

        visit(ctx.bloco());             // Visita bloco de código

        System.out.println("ujp " + startLabel);  // Volta ao início
        System.out.println(endLabel + ":");      // Marca fim do while

        breakLabels.pop();               // Remove label do stack

        return null;
    }

    @Override
    public Void visitExpr_bool(LinguagemParser.Expr_boolContext ctx) {
        if (ctx.getText().contains("and")) {
            visit(ctx.expr_bool(0));     // Avalia primeira expressão
            visit(ctx.expr_bool(1));     // Avalia segunda expressão
            System.out.println("and");   // Operação AND
        } else if (ctx.getText().contains("or")) {
            visit(ctx.expr_bool(0));     // Avalia primeira expressão
            visit(ctx.expr_bool(1));     // Avalia segunda expressão
            System.out.println("or");    // Operação OR
        } else if (ctx.expr_rel() != null) {
            visit(ctx.expr_rel());       // Avalia expressão relacional
        } else if (ctx.getText().equals("true")) {
            System.out.println("ldc true");
        } else if (ctx.getText().equals("false")) {
            System.out.println("ldc false");
        }

        return null;
    }

    @Override
    public Void visitExpr_rel(LinguagemParser.Expr_relContext ctx) {
        visit(ctx.expr(0));             // Avalia primeira expressão
        visit(ctx.expr(1));             // Avalia segunda expressão

        String op = ctx.op_rel().getText();
        switch (op) {
            case "<":
                System.out.println("let");
                break;
            case ">":
                System.out.println("grt");
                break;
            case "<=":
                System.out.println("lte");
                break;
            case ">=":
                System.out.println("gte");
                break;
            case "==":
                System.out.println("equ");
                break;
            case "!=":
                System.out.println("neq");
                break;
        }

        return null;
    }

    @Override
    public Void visitExpr(LinguagemParser.ExprContext ctx) {
        if (ctx.termo().size() == 2) {
            visit(ctx.termo(0));        // Avalia primeiro termo
            visit(ctx.termo(1));        // Avalia segundo termo
            
            if (ctx.op.getText().equals("+")) {
                System.out.println("add");
            } else {
                System.out.println("sub");
            }
        } else {
            visit(ctx.termo(0));        // Avalia único termo
        }

        return null;
    }

    @Override
    public Void visitTermo(LinguagemParser.TermoContext ctx) {
        if (ctx.fator().size() == 2) {
            visit(ctx.fator(0));        // Avalia primeiro fator
            visit(ctx.fator(1));        // Avalia segundo fator
            
            if (ctx.op.getText().equals("*")) {
                System.out.println("mul");
            } else {
                System.out.println("div");
            }
        } else {
            visit(ctx.fator(0));        // Avalia único fator
        }

        return null;
    }

    @Override
    public Void visitFator(LinguagemParser.FatorContext ctx) {
        if (ctx.potencia().size() == 2) {
            visit(ctx.potencia(0));     // Base
            visit(ctx.potencia(1));     // Expoente
            System.out.println("pow");  // Operação de potência
        } else {
            visit(ctx.potencia(0));     // Único valor
        }
        return null;
    }

    @Override
    public Void visitPotencia(LinguagemParser.PotenciaContext ctx) {
        if (ctx.NUM() != null) {
            System.out.println("ldc " + ctx.NUM().getText());
        } else if (ctx.ID() != null) {
            String id = ctx.ID().getText();
            if (!variaveis.containsKey(id)) {
                throw new RuntimeException("Erro: Variável '" + id + "' não declarada");
            }
            System.out.println("lod #" + variaveis.get(id));
        } else if (ctx.expr() != null) {
            visit(ctx.expr());
        }

        return null;
    }
}