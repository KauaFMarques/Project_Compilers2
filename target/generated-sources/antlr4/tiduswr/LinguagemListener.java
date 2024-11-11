// Generated from tiduswr/Linguagem.g4 by ANTLR 4.13.2
package tiduswr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LinguagemParser}.
 */
public interface LinguagemListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(LinguagemParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(LinguagemParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterComando(LinguagemParser.ComandoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitComando(LinguagemParser.ComandoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#declaracao}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao(LinguagemParser.DeclaracaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#declaracao}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao(LinguagemParser.DeclaracaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#atribuicao}.
	 * @param ctx the parse tree
	 */
	void enterAtribuicao(LinguagemParser.AtribuicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#atribuicao}.
	 * @param ctx the parse tree
	 */
	void exitAtribuicao(LinguagemParser.AtribuicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#impressao}.
	 * @param ctx the parse tree
	 */
	void enterImpressao(LinguagemParser.ImpressaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#impressao}.
	 * @param ctx the parse tree
	 */
	void exitImpressao(LinguagemParser.ImpressaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#entrada}.
	 * @param ctx the parse tree
	 */
	void enterEntrada(LinguagemParser.EntradaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#entrada}.
	 * @param ctx the parse tree
	 */
	void exitEntrada(LinguagemParser.EntradaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIf_stmt(LinguagemParser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIf_stmt(LinguagemParser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stmt(LinguagemParser.While_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#while_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stmt(LinguagemParser.While_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(LinguagemParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(LinguagemParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#expr_bool}.
	 * @param ctx the parse tree
	 */
	void enterExpr_bool(LinguagemParser.Expr_boolContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#expr_bool}.
	 * @param ctx the parse tree
	 */
	void exitExpr_bool(LinguagemParser.Expr_boolContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#expr_rel}.
	 * @param ctx the parse tree
	 */
	void enterExpr_rel(LinguagemParser.Expr_relContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#expr_rel}.
	 * @param ctx the parse tree
	 */
	void exitExpr_rel(LinguagemParser.Expr_relContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#op_rel}.
	 * @param ctx the parse tree
	 */
	void enterOp_rel(LinguagemParser.Op_relContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#op_rel}.
	 * @param ctx the parse tree
	 */
	void exitOp_rel(LinguagemParser.Op_relContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(LinguagemParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(LinguagemParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(LinguagemParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(LinguagemParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(LinguagemParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(LinguagemParser.FatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#potencia}.
	 * @param ctx the parse tree
	 */
	void enterPotencia(LinguagemParser.PotenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#potencia}.
	 * @param ctx the parse tree
	 */
	void exitPotencia(LinguagemParser.PotenciaContext ctx);
}