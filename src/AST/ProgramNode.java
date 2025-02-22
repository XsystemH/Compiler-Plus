package AST;

import AST.Stmt.varDefStmtNode;
import util.position;

import java.util.ArrayList;

public class ProgramNode extends ASTNode {
    public ArrayList<ASTNode> members;

    public ProgramNode(position pos) {
        super(pos);
        members = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
