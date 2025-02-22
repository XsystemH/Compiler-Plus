package AST.Expr;

import AST.ASTVisitor;
import util.Type.Type;
import util.position;

public class boolExprNode extends ExprNode{
    public ExprNode lhs, rhs;

    public enum boolOpType {
        equal, notEqual, less, greater, lessOrEqual, greaterOrEqual;
    }
    public boolOpType opCode;

    public boolExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
