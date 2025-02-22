package AST;

import AST.Stmt.StmtNode;
import util.position;

import java.util.ArrayList;

public class MainNode extends FuncNode {
    public ArrayList<StmtNode> statements;

    public MainNode(position pos) {
        super(pos);
        statements = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
