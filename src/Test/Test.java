package Test;

import Lexer.ListLexerEq;
import Log.Log;
import Parser.ListParserEq;

public class Test {
    public static void main(String[] args) {
        if (args[0] != null) {
            ListLexerEq lexer = new ListLexerEq(args[0]);
            ListParserEq parser = new ListParserEq(lexer);
            Log.setLOG(true);
            Log.addTag("token");
            parser.parse();
        }
    }
}
