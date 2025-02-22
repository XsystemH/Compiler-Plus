package AST.Expr;

import AST.ASTVisitor;
import util.position;

import java.util.ArrayList;

public class funcCallExprNode extends ExprNode{
    public String funcName;
    public ArrayList<ExprNode> parameters;

    public funcCallExprNode(position pos) {
        super(pos);
        parameters = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
