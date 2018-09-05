package Lexer;

/* extends the ListLexer class to allow equality inside of lists */

public class ListLexerEq extends ListLexer {
    public static final int EQUAL = ListLexer.tokenNames.length + 1;
    protected static final String[] tokenNames = new String[ListLexer.tokenNames.length + 1];

    static {
        System.arraycopy(ListLexer.tokenNames, 0, tokenNames, 0, ListLexer.tokenNames.length);
        tokenNames[EQUAL - 1] = "EQUAL";
    }

    public ListLexerEq(String input) {
        super(input);
    }

    @Override
    public String getTokenName(int x) {
        return tokenNames[x];
    }

    @Override
    public Token nextToken() {
        while (c != Lexer.EOF) {
            switch (c) {
                case ' ': case '\t': case '\n': case '\r': WS(); continue;
                case ',': consume(); return new Token(COMMA, ",");
                case '[': consume(); return new Token(LBRACK, "[");
                case ']': consume(); return new Token(RBRACK, "]");
                case '=': consume(); return new Token(EQUAL, "=");
                default:
                    if (isLETTER()) return NAME();
                    else if(isNUMBER()) return NUMBER();
                    else throw new Error("invalid character: " + c);
            }
        }
        return new Token(EOF_TYPE, "<EOF>");
    }
}
