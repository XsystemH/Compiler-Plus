package AST.Expr;

import AST.ASTVisitor;
import util.position;

public class notExprNode extends ExprNode{
    public ExprNode expr;

    public notExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
