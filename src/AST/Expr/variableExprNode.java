package AST.Expr;

import AST.ASTVisitor;
import util.position;

public class variableExprNode extends ExprNode{
    public String name;

    public variableExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
