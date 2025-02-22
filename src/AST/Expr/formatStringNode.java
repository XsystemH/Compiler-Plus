package AST.Expr;

import AST.ASTVisitor;
import util.position;

import java.util.ArrayList;

public class formatStringNode extends ExprNode{
    public String begin, end;
    public ArrayList<String> midList;
    public ArrayList<ExprNode> exprList;

    public formatStringNode(position pos) {
        super(pos);
        midList = new ArrayList<>();
        exprList = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
