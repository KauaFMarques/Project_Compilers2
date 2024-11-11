package tiduswr;

import tiduswr.LinguagemParser.InicialContext;
import tiduswr.LinguagemParser.PrintContext;

public class Visitador extends LinguagemBaseVisitor<Void> {
    @Override
    public Void visitInicial(InicialContext ctx) {
        System.out.println("VISITEI O NÓ INICIAL");
        return super.visitInicial(ctx);
    }

    @Override
    public Void visitPrint(PrintContext ctx) {
        System.out.println("VISITEI O NÓ '" + ctx.PC_PRINT().getText() + "'");
        System.out.println("O PARÂMETRO FOI: '" + ctx.STRING().getText() + "'");
        return super.visitPrint(ctx);
    }
}