package tiduswr;

import java.util.HashMap;
import java.util.Map;

public class Visitador extends LinguagemBaseVisitor<Void> {
    private Map<String, Boolean> variaveisDeclaradas = new HashMap<>();

    @Override
    public Void visitPrograma(LinguagemParser.ProgramaContext ctx) {
        System.out.println("Iniciando análise do programa");
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitDeclaracao(LinguagemParser.DeclaracaoContext ctx) {
        String id = ctx.ID().getText();
        if (variaveisDeclaradas.containsKey(id)) {
            System.out.println("Erro: Variável '" + id + "' já declarada.");
        } else {
            variaveisDeclaradas.put(id, true);
            System.out.println("Variável '" + id + "' declarada.");

            // Se a declaração inclui inicialização, processa a expressão
            if (ctx.expr() != null) {
                visit(ctx.expr());
            }
        }
        return super.visitDeclaracao(ctx);
    }

    @Override
    public Void visitAtribuicao(LinguagemParser.AtribuicaoContext ctx) {
        String id = ctx.ID().getText();
        if (!variaveisDeclaradas.containsKey(id)) {
            System.out.println("Erro: Variável '" + id + "' não declarada antes da atribuição.");
        } else {
            visit(ctx.expr());
            System.out.println("Atribuição à variável '" + id + "'");
        }
        return super.visitAtribuicao(ctx);
    }

    @Override
    public Void visitImpressao(LinguagemParser.ImpressaoContext ctx) {
        if (ctx.expr() != null) {
            visit(ctx.expr());
            System.out.println("Imprimindo expressão");
        } else if (ctx.STRING() != null) {
            System.out.println("Imprimindo string: " + ctx.STRING().getText());
        }
        return super.visitImpressao(ctx);
    }

    @Override
    public Void visitEntrada(LinguagemParser.EntradaContext ctx) {
        String id = ctx.ID().getText();
        if (!variaveisDeclaradas.containsKey(id)) {
            System.out.println("Erro: Variável '" + id + "' não declarada antes da entrada.");
        } else {
            System.out.println("Entrada para variável '" + id + "'");
        }
        return super.visitEntrada(ctx);
    }

    @Override
    public Void visitIf_stmt(LinguagemParser.If_stmtContext ctx) {
        System.out.println("Processando comando if");
        visit(ctx.expr_bool());
        visit(ctx.bloco(0));  // First block (if block)

        // Check if there's an else block
        if (ctx.bloco().size() > 1) {
            System.out.println("Processando bloco else");
            visit(ctx.bloco(1));  // Second block (else block)
        }
        return null;
    }

    @Override
    public Void visitWhile_stmt(LinguagemParser.While_stmtContext ctx) {
        System.out.println("Processando comando while");
        visit(ctx.expr_bool());
        visit(ctx.bloco());
        return super.visitWhile_stmt(ctx);
    }

    @Override
    public Void visitExpr_bool(LinguagemParser.Expr_boolContext ctx) {
        System.out.println("Processando expressão booleana");
        return super.visitExpr_bool(ctx);
    }

    @Override
    public Void visitExpr_rel(LinguagemParser.Expr_relContext ctx) {
        System.out.println("Processando expressão relacional");
        visit(ctx.expr(0));
        visit(ctx.expr(1));
        return super.visitExpr_rel(ctx);
    }

    @Override
    public Void visitExpr(LinguagemParser.ExprContext ctx) {
        return super.visitExpr(ctx);
    }
}