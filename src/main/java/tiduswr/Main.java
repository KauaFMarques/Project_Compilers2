package tiduswr;

import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;

public class Main {
    public static void main(String[] args) throws IOException {
        CharStream charStream = CharStreams.fromFileName("teste.grammar");
        LinguagemLexer lexer = new LinguagemLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LinguagemParser parser = new LinguagemParser(tokens);
        
        // Gera código P-code
        GeradorPCode gerador = new GeradorPCode();
        gerador.visit(parser.programa());
    }
}