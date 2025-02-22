package AST.Expr;

import AST.ASTVisitor;
import util.position;

public class priorityExprNode extends ExprNode{
    public ExprNode expr;

    public priorityExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
