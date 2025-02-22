package AST.Expr;

import AST.ASTVisitor;
import util.Type.Type;
import util.position;

public class binaryExprNode extends ExprNode{
    public ExprNode lhs, rhs;

    public enum binaryOpType {
        add, sub, mul, div, mod, and, or_, xor, leftShift, rightShift;
    }
    public binaryOpType opCode;

    public binaryExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
