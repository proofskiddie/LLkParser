package Lexer;

public abstract class Lexer {
    public static final char EOF = (char) -1;
    public static final int EOF_TYPE = 1;

    private final String input;
    private final int len;
    private int p = 0; // index into input
    char c; // current char

    Lexer(String input) {
        this.input = input;
        len = input.length();
        c = input.charAt(p); // first char
    }

    void consume() {
        ++p;
        if (p >= len) {
            c = EOF;
        } else c = input.charAt(p);
    }

    void match(char x) {
        if (c == x) consume();
        else throw new Error("expecting " + x + "; found " + c);
    }

    public abstract Token nextToken();

    public abstract String getTokenName(int x);
}
