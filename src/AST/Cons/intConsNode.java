package AST.Cons;

import AST.ASTVisitor;
import util.Type.Type;
import util.position;

public class intConsNode extends ConsNode{
    public int value;

    public intConsNode(position pos) {
        super(pos);
        type = new Type("int");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
