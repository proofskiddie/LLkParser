package Lexer;
/* Token contains a type and string taken from raw input
 *  represents words in the language */

import Lexer.ListLexer;

public class Token {
    public int type;
    public String text;

    public Token(int type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        String tname = ListLexer.tokenNames[type];
        return "<" + text + ", " + tname + ">";
    }
}
