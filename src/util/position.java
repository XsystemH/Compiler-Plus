package util;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public class position {
    private int row, column;

    public position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public position(Token token) {
        this(token.getLine(), token.getCharPositionInLine());
    }

    public position(TerminalNode terminalNode) {
        this(terminalNode.getSymbol());
    }

    public position(ParserRuleContext ctx) {
        this(ctx.getStart());
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return column;
    }

    public String toString() {
        return row + "," + column;
    }
}
