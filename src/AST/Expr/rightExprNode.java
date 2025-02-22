package AST.Expr;

import AST.ASTVisitor;
import util.Type.Type;
import util.position;

public class rightExprNode extends ExprNode{
    public ExprNode expr;

    public enum rightOpType {
        add, sub;
    }
    public rightOpType opCode;

    public rightExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
