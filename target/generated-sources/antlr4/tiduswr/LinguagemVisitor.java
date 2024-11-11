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
	 * Visit a parse tree produced by {@link LinguagemParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(LinguagemParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link LinguagemParser#inicial}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicial(LinguagemParser.InicialContext ctx);
}