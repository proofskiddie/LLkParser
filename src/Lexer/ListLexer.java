package Lexer;

/* a lexer for arbitrarily nested lists of numbers and names */
public class ListLexer extends Lexer {
    // Types
    public static final int NAME = 2; // fist char a LETTER followed by ALPHANUMERIC
    public static final int COMMA = 3;
    public static final int LBRACK = 4;
    public static final int RBRACK = 5;
    public static final int NUMBER = 6; // whole numbers
    protected static String[] tokenNames = {"n/a", "<EOF>", "NAME", "COMMA", "LBRACK", "RBRACK", "NUMBER"};

    public String getTokenName(int x) {
        return tokenNames[x];
    }

    public ListLexer(String input) {
        super(input);
    }

    protected boolean isLETTER() {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    protected boolean isNUMBER() {
        return c >= '0' && c <= '9';
    }

    protected boolean isALPHANUMERIC() {
        return isLETTER() || isNUMBER();
    }

    // pattern for LL(1) Recursive decent lexer
    // each token can be determined by first char in that token
    // walking across the string then calls handler methods
    // as those tokens are found
    public Token nextToken() {
        while (c != EOF) {
            switch (c) {
                case ' ':
                case '\t':
                case '\r':
                case '\n':
                    WS();
                    continue;
                case ',':
                    consume();
                    return new Token(COMMA, ",");
                case '[':
                    consume();
                    return new Token(LBRACK, "[");
                case ']':
                    consume();
                    return new Token(RBRACK, "]");
                default:
                    if (isLETTER()) {
                        return NAME();
                    } else if (isNUMBER()) {
                        return NUMBER();
                    } else {
                        throw new Error("invalid character: " + c);
                    }
            }
        }
        return new Token(EOF_TYPE, "<EOF>");
    }

    protected Token NAME() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(c);
            consume();
        } while (isALPHANUMERIC());
        return new Token(NAME, buf.toString());
    }

    protected Token NUMBER() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(c);
            consume();
        } while (isNUMBER());
        return new Token(NUMBER, buf.toString());
    }

    protected void WS() {
        do {
            consume();
        } while (c == '\t' || c == ' ' || c == '\n' || c == '\r');
    }
}

