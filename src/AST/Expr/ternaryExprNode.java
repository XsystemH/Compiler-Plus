package AST.Expr;

import AST.ASTVisitor;
import util.position;

public class ternaryExprNode extends ExprNode{
    public ExprNode condition;
    public ExprNode case1, case0;

    public ternaryExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
