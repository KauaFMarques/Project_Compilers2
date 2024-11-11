package tiduswr;

import java.util.HashMap;
import java.util.Map;

public class GeradorPCode extends LinguagemBaseVisitor<Void> {
    private Map<String, Integer> variaveis = new HashMap<>();
    private int proxEndereco = 0;
    private int labelCount = 0;
    
    private String novoLabel() {
        return "L" + (labelCount++);
    }

    @Override
    public Void visitDeclaracao(LinguagemParser.DeclaracaoContext ctx) {
        String id = ctx.ID().getText();
        variaveis.put(id, proxEndereco++);
        
        if (ctx.expr() != null) {
            visit(ctx.expr());
            System.out.println("lda #" + variaveis.get(id));
            System.out.println("sto");
        }
        return null;
    }

    @Override
    public Void visitImpressao(LinguagemParser.ImpressaoContext ctx) {
        if (ctx.expr() != null) {
            visit(ctx.expr());
        } else {
            System.out.println("ldc " + ctx.STRING().getText());
        }
        System.out.println("wri");
        return null;
    }

    @Override
    public Void visitEntrada(LinguagemParser.EntradaContext ctx) {
        String id = ctx.ID().getText();
        System.out.println("rd");
        System.out.println("lda #" + variaveis.get(id));
        System.out.println("sto");
        return null;
    }

    @Override
    public Void visitIf_stmt(LinguagemParser.If_stmtContext ctx) {
        String endLabel = novoLabel();
        
        visit(ctx.expr_bool());
        System.out.println("fjp " + endLabel);
        
        visit(ctx.bloco());
        System.out.println(endLabel + ":");
        
        return null;
    }

    @Override
    public Void visitWhile_stmt(LinguagemParser.While_stmtContext ctx) {
        String startLabel = novoLabel();
        String endLabel = novoLabel();
        
        System.out.println(startLabel + ":");
        visit(ctx.expr_bool());
        System.out.println("fjp " + endLabel);
        
        visit(ctx.bloco());
        System.out.println("ujp " + startLabel);
        System.out.println(endLabel + ":");
        
        return null;
    }

    // Implemente os outros métodos visitXXX para expressões e operações...
}