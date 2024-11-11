// Generated from tiduswr/Linguagem.g4 by ANTLR 4.13.2
package tiduswr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LinguagemParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LinguagemVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(LinguagemParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#comando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComando(LinguagemParser.ComandoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#declaracao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao(LinguagemParser.DeclaracaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#atribuicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtribuicao(LinguagemParser.AtribuicaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#impressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImpressao(LinguagemParser.ImpressaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#entrada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntrada(LinguagemParser.EntradaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(LinguagemParser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#while_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stmt(LinguagemParser.While_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#bloco}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloco(LinguagemParser.BlocoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#expr_bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_bool(LinguagemParser.Expr_boolContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#expr_rel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_rel(LinguagemParser.Expr_relContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#op_rel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_rel(LinguagemParser.Op_relContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(LinguagemParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#termo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo(LinguagemParser.TermoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#fator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator(LinguagemParser.FatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#potencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPotencia(LinguagemParser.PotenciaContext ctx);
}