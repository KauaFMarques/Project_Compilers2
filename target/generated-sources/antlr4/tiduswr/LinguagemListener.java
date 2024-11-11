// Generated from tiduswr/Linguagem.g4 by ANTLR 4.13.2
package tiduswr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LinguagemParser}.
 */
public interface LinguagemListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(LinguagemParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(LinguagemParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinguagemParser#inicial}.
	 * @param ctx the parse tree
	 */
	void enterInicial(LinguagemParser.InicialContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinguagemParser#inicial}.
	 * @param ctx the parse tree
	 */
	void exitInicial(LinguagemParser.InicialContext ctx);
}