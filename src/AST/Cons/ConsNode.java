package AST.Cons;

import AST.ASTNode;
import util.Type.Type;
import util.position;

public abstract class ConsNode extends ASTNode {
    public Type type;

    public ConsNode(position pos) {
        super(pos);
    }
}
