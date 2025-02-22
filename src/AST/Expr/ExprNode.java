package AST.Expr;

import AST.ASTNode;
import util.Type.Type;
import util.position;

public abstract class ExprNode extends ASTNode {
    public Type type;
    public boolean isLeft;

    public ExprNode(position pos) {
        super(pos);
    }
}
