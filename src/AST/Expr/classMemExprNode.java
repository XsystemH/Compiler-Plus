package AST.Expr;

import AST.ASTVisitor;
import util.position;

public class classMemExprNode extends ExprNode{
    public ExprNode className;
    public String memName;

    public classMemExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
