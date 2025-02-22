package AST.Expr;

import AST.ASTVisitor;
import util.position;

public class nullExprNode extends ExprNode{
    public nullExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
