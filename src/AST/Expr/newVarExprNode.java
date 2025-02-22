package AST.Expr;

import AST.ASTVisitor;
import util.position;

public class newVarExprNode extends ExprNode{
    public boolean calling = false;

    public newVarExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
