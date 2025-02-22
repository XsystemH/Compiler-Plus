package AST.Expr;

import AST.ASTVisitor;
import AST.Cons.arrConsNode;
import util.position;

import java.util.ArrayList;

public class newArrExprNode extends ExprNode{
    public ArrayList<ExprNode> expr;
    public arrConsNode arr;
    public newArrExprNode(position pos) {
        super(pos);
        expr = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
