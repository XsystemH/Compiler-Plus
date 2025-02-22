package AST.Cons;

import AST.ASTVisitor;
import util.Type.Type;
import util.position;

import java.util.ArrayList;

public class arrConsNode extends ConsNode{
    public ArrayList<ConsNode> content;

    public arrConsNode(position pos) {
        super(pos);
        content = new ArrayList<>();
        type = new Type();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
