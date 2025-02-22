package AST.Expr;

import AST.ASTVisitor;
import util.position;

import java.util.ArrayList;

public class arrayVisitExprNode extends ExprNode{
    public ExprNode arrayName;
    public ExprNode index;

    public arrayVisitExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
