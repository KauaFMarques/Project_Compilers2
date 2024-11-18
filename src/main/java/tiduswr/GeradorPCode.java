package tiduswr;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GeradorPCode extends LinguagemBaseVisitor<Void> {
    private Map<String, Integer> variaveis;
    private int proxEndereco;
    private int labelCount;
    private Stack<String> breakLabels;
    private Stack<String> continueLabels;
    
    public GeradorPCode() {
        this.variaveis = new HashMap<>();
        this.proxEndereco = 0;
        this.labelCount = 0;
        this.breakLabels = new Stack<>();
        this.continueLabels = new Stack<>();
    }

    private String normaliarString(String str) {
         // Normaliza a string para a forma NFD (caracteres decompostos)
         String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        // Remove os caracteres de diacríticos (acentos, etc.)
        return normalized.replaceAll("\\p{M}", "")
                         .replaceAll("[^\\p{ASCII}]", "");
    }

    private String novoLabel() {
        return "L" + (labelCount++);
    }

    private void gerarCodigo(String instrucao) {
        System.out.println(instrucao);
    }

    @Override
    public Void visitPrograma(LinguagemParser.ProgramaContext ctx) {
        // Visita todos os comandos do programa
        ctx.comando().forEach(this::visit);
        // Adiciona instrução de parada ao final
        gerarCodigo("stp");
        return null;
    }

    @Override
    public Void visitDeclaracao(LinguagemParser.DeclaracaoContext ctx) {
        String id = ctx.ID().getText();
        if (!variaveis.containsKey(id)) {
            variaveis.put(id, proxEndereco++);
            
            if (ctx.expr() != null) {
                // Se houver expressão de inicialização
                String endereco = "#" + variaveis.get(id);
                gerarCodigo("lda " + endereco);
                 visit(ctx.expr());
                gerarCodigo("sto");
            }
        } else {
            throw new RuntimeException("Erro: Variável '" + id + "' já declarada.");
        }
        return null;
    }

    @Override
    public Void visitAtribuicao(LinguagemParser.AtribuicaoContext ctx) {
        String id = ctx.ID().getText();
        if (variaveis.containsKey(id)) {
            String endereco = "#" + variaveis.get(id);
            gerarCodigo("lda " + endereco);  // Empilha o endereço da variável
            visit(ctx.expr());  // Empilha o valor
            gerarCodigo("sto");  // Armazena o valor no endereço
        } else {
            throw new RuntimeException("Erro: Variável '" + id + "' não declarada.");
        }
        return null;
    }

    @Override
    public Void visitImpressao(LinguagemParser.ImpressaoContext ctx) {
        if (ctx.expr() != null) {
            visit(ctx.expr());
        } else if (ctx.STRING() != null) {
            String str = ctx.STRING().getText();
            // Remove as aspas do início e fim
            str = str.substring(1, str.length() - 1);
            // Substitui caracteres especiais
            str = normaliarString(str);
            gerarCodigo("ldc \"" + str + "\"");
        }
        gerarCodigo("wri");
        return null;
    }

    @Override
    public Void visitEntrada(LinguagemParser.EntradaContext ctx) {
        String id = ctx.ID().getText();
        if (variaveis.containsKey(id)) {
            gerarCodigo("lda #" + variaveis.get(id));
            gerarCodigo("rd");
            gerarCodigo("sto");
        } else {
            throw new RuntimeException("Erro: Variável '" + id + "' não declarada.");
        }
        return null;
    }

    @Override
    public Void visitIf_stmt(LinguagemParser.If_stmtContext ctx) {
        String endLabel = novoLabel();
        String elseLabel = ctx.bloco().size() > 1 ? novoLabel() : endLabel;

        visit(ctx.expr_bool());
        gerarCodigo("fjp " + elseLabel);

        visit(ctx.bloco(0));  // First block (if block)

        if (ctx.bloco().size() > 1) {
            gerarCodigo("ujp " + endLabel);
            gerarCodigo(elseLabel + ":");
            visit(ctx.bloco(1));  // Second block (else block)
        }

        gerarCodigo(endLabel + ":");
        return null;
    }

    @Override
    public Void visitWhile_stmt(LinguagemParser.While_stmtContext ctx) {
        String startLabel = novoLabel();
        String endLabel = novoLabel();

        breakLabels.push(endLabel);
        continueLabels.push(startLabel);

        gerarCodigo(startLabel + ":");
        visit(ctx.expr_bool());
        gerarCodigo("fjp " + endLabel);

        visit(ctx.bloco());
        gerarCodigo("ujp " + startLabel);

        gerarCodigo(endLabel + ":");

        breakLabels.pop();
        continueLabels.pop();
        return null;
    }

    @Override
    public Void visitExpr_bool(LinguagemParser.Expr_boolContext ctx) {
        if (ctx.expr_bool().size() == 2) {
            visit(ctx.expr_bool(0));
            visit(ctx.expr_bool(1));
            String op = ctx.getChild(1).getText();
            gerarCodigo(op.equals("and") ? "and" : "or");
        } else if (ctx.expr_rel() != null) {
            visit(ctx.expr_rel());
        } else {
            gerarCodigo("ldc " + ctx.getText()); // true ou false
        }
        return null;
    }

    @Override
    public Void visitExpr_rel(LinguagemParser.Expr_relContext ctx) {
        visit(ctx.expr(0));
        visit(ctx.expr(1));
        
        switch (ctx.op_rel().getText()) {
            case "<":
                gerarCodigo("let");
                break;
            case ">":
                gerarCodigo("grt");
                break;
            case "<=":
                gerarCodigo("lte");
                break;
            case ">=":
                gerarCodigo("gte");
                break;
            case "==":
                gerarCodigo("equ");
                break;
            case "!=":
                gerarCodigo("neq");
                break;
        }
        return null;
    }

    @Override
    public Void visitExpr(LinguagemParser.ExprContext ctx) {
        visit(ctx.termo(0));  // Empilha o primeiro termo
        for (int i = 1; i < ctx.termo().size(); i++) {
            visit(ctx.termo(i));  // Empilha os outros termos
            String op = ctx.getChild(2*i - 1).getText();
            if (op.equals("+")) {
                gerarCodigo("add");
            } else if (op.equals("-")) {
                gerarCodigo("sub");
            }
        }
        return null;
    }

    @Override
    public Void visitTermo(LinguagemParser.TermoContext ctx) {
        visit(ctx.fator(0));  // Empilha o primeiro fator
        for (int i = 1; i < ctx.fator().size(); i++) {
            visit(ctx.fator(i));  // Empilha os outros fatores
            String op = ctx.getChild(2*i - 1).getText();
            if (op.equals("*")) {
                gerarCodigo("mul");
            } else if (op.equals("/")) {
                gerarCodigo("div");
            }
        }
        return null;
    }


    @Override
    public Void visitFator(LinguagemParser.FatorContext ctx) {
        visit(ctx.potencia(0));  // Empilha o primeiro valor
        for (int i = 1; i < ctx.potencia().size(); i++) {
            visit(ctx.potencia(i));  // Empilha os outros valores
            gerarCodigo("exp");  // Aplica a exponenciação
        }
        return null;
    }

    @Override
    public Void visitPotencia(LinguagemParser.PotenciaContext ctx) {
        if (ctx.NUM() != null) {
            gerarCodigo("ldc " + ctx.NUM().getText());
        } else if (ctx.ID() != null) {
            String id = ctx.ID().getText();
            if (variaveis.containsKey(id)) {
                gerarCodigo("lod #" + variaveis.get(id));
            } else {
                throw new RuntimeException("Erro: Variável '" + id + "' não declarada.");
            }
        } else {
            visit(ctx.expr());
        }
        return null;
    }
}