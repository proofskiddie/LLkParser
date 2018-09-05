package Parser;

import Lexer.Lexer;
import Lexer.Token;
import Log.Log;

/* LL(k) parser template */

public abstract class ParserLLk {
    Lexer lex;
    Token[] tokenBuffer;
    int k; // LL(k), number of lookahead tokens
    int p = 0; // position of next token

    ParserLLk(Lexer input, int k) {
        this.lex = input;
        this.k = k;
        this.tokenBuffer = new Token[k];
        for (int i = 0; i < k; ++i) consume();
        for (int i = 0; i < k; ++i) Log.d("token", tokenBuffer[i].toString());
    }

    public Token LT(int i) { return tokenBuffer[(p + i - 1)%k]; }
    public int LA(int i) { return LT(i).type; }

    void match(int x) {
        if (LA(1) == x) consume();
        else throw new Error("expected token of type " + lex.getTokenName(x) + " found " + LT(1));
    }

    void consume() {
        tokenBuffer[p] = lex.nextToken();
        p = (p + 1) % k;
    }

    public abstract void parse();
}
