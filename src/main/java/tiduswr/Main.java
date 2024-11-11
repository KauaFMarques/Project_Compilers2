package tiduswr;

import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;

public class Main {
    public static void main(String[] args) throws IOException {
        CharStream input = CharStreams.fromFileName("input.txt");
        LinguagemLexer lexer = new LinguagemLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LinguagemParser parser = new LinguagemParser(tokens);
        
        GeradorPCode gerador = new GeradorPCode();
        gerador.visit(parser.programa());
    }
}