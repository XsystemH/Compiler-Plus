package AST.Cons;

import AST.ASTVisitor;
import util.Type.Type;
import util.position;

public class strConsNode extends ConsNode{
    public String value;

    public strConsNode(position pos) {
        super(pos);
        type = new Type("string");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
