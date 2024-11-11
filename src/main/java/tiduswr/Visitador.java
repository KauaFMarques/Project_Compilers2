package tiduswr;

public class Visitador extends LinguagemBaseVisitor<Void> {
    @Override
    public Void visitPrograma(LinguagemParser.ProgramaContext ctx) {
        System.out.println("Iniciando análise do programa");
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitComando(LinguagemParser.ComandoContext ctx) {
        return super.visitComando(ctx);
    }

    @Override
    public Void visitDeclaracao(LinguagemParser.DeclaracaoContext ctx) {
        System.out.println("Processando declaração de variável: " + ctx.ID().getText());
        if (ctx.expr() != null) {
            System.out.println("Com inicialização");
        }
        return super.visitDeclaracao(ctx);
    }

    @Override
    public Void visitAtribuicao(LinguagemParser.AtribuicaoContext ctx) {
        System.out.println("Processando atribuição para variável: " + ctx.ID().getText());
        return super.visitAtribuicao(ctx);
    }

    @Override
    public Void visitImpressao(LinguagemParser.ImpressaoContext ctx) {
        if (ctx.expr() != null) {
            System.out.println("Imprimindo expressão");
        } else if (ctx.STRING() != null) {
            System.out.println("Imprimindo string: " + ctx.STRING().getText());
        }
        return super.visitImpressao(ctx);
    }

    @Override
    public Void visitEntrada(LinguagemParser.EntradaContext ctx) {
        System.out.println("Processando entrada para variável: " + ctx.ID().getText());
        return super.visitEntrada(ctx);
    }

    @Override
    public Void visitIf_stmt(LinguagemParser.If_stmtContext ctx) {
        System.out.println("Processando comando if");
        return super.visitIf_stmt(ctx);
    }

    @Override
    public Void visitWhile_stmt(LinguagemParser.While_stmtContext ctx) {
        System.out.println("Processando comando while");
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
        return super.visitExpr_rel(ctx);
    }

    @Override
    public Void visitExpr(LinguagemParser.ExprContext ctx) {
        System.out.println("Processando expressão");
        return super.visitExpr(ctx);
    }
}