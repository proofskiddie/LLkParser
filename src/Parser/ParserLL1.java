package Parser;

import Lexer.Lexer;
import Lexer.Token;
import Log.Log;

public abstract class ParserLL1 {
    Lexer lex;
    Token t; // lookahead

    ParserLL1(Lexer input) { lex = input; consume(); }

    void match(int type) {
        if (t.type == type) {
            Log.d("terminal", t.toString());
            consume();
        } else throw new Error("expected " + lex.getTokenName(type) + " but found " + t);
    }

    void consume() {
        t = lex.nextToken();
    }

    // defines start non-terminal of grammar
    public abstract void parse();
}
