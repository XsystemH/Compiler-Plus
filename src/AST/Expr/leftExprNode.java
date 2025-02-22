package AST.Expr;

import AST.ASTVisitor;
import util.Type.Type;
import util.position;

public class leftExprNode extends ExprNode{
    public ExprNode expr;

    public enum leftOpType {
        add, sub, negation, negative;
    }
    public leftOpType opCode;

    public leftExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
