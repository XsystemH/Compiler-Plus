package AST.Expr;

import AST.ASTVisitor;
import AST.Cons.ConsNode;
import util.position;

public class constantExprNode extends ExprNode{
    public ConsNode constant;

    public constantExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
