package AST.Expr;

import AST.ASTVisitor;
import util.position;

public class thisExprNode extends ExprNode{
    public thisExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
