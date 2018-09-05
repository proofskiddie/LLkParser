package Parser;

/*
  implements an LL(1) parser for a simple list grammar
      list := '[' elements ']'
  elements := element (',' elements)*
   element := NAME | NUMBER | list
 */

import Lexer.ListLexer;
import Log.Log;

public class ListParserLL1 extends ParserLL1 {

    public ListParserLL1(ListLexer input) {
        super(input);
    }

    public void parse() { list(); }

    private void list() {
        Log.d("non-terminal", "list");
        match(ListLexer.LBRACK);
        elements();
        match(ListLexer.RBRACK);
    }

    private void elements() {
        Log.d("non-terminal", "elements");
        element();
        if (t.type == ListLexer.COMMA) {
            match(ListLexer.COMMA);
            elements();
        }
    }

    private void element() {
        Log.d("non-terminal", "element");
        switch (t.type) {
            case ListLexer.NAME:
                match(ListLexer.NAME);
                break;
            case ListLexer.NUMBER:
                match(ListLexer.NUMBER);
                break;
            case ListLexer.LBRACK:
                list();
                break;
            default:
                throw new Error("expecting NAME, NUMBER or list; found " + t);
        }
    }
}
