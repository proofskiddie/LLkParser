package Parser;
/*
   extension of ListParserLL1 grammar
   adding the possibility of assignment in lists
   means this parser is LL(2) (leftmost non-terminals are equal in two branches)
   or two lookahead elements are needed to create a parser for the grammar

      list := '[' elements ']'
  elements := element (',' elements)*
   element := NAME | NUMBER | list | NAME "=" NAME
 */

import Lexer.ListLexerEq;

public class ListParserEq extends ParserLLk {

    public ListParserEq(ListLexerEq input) {
        super(input, 2);
    }

    @Override
    public void parse() {
        list();
    }

    protected void list() {
        match(ListLexerEq.LBRACK);
        elements();
        match(ListLexerEq.RBRACK);
    }

    protected void elements() {
        element();
        if (LA(1) == ListLexerEq.COMMA) {
            match(ListLexerEq.COMMA);
            elements();
        }
    }

    protected void element() {
        if (LA(1) == ListLexerEq.NAME && LA(2) == ListLexerEq.EQUAL) {
            match(ListLexerEq.NAME);
            match(ListLexerEq.EQUAL);
            match(ListLexerEq.NAME);
        } else if (LA(1) == ListLexerEq.NUMBER) {
            match(ListLexerEq.NUMBER);
        } else if (LA(1) == ListLexerEq.NAME) {
            match(ListLexerEq.NAME);
        } else if (LA(1) == ListLexerEq.LBRACK) {
            list();
        } else throw new Error("expected NAME, NUMBER, list or Assignment; found " + LT(1));
    }
}
