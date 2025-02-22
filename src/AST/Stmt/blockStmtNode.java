package AST.Stmt;

import AST.ASTVisitor;
import util.position;

import java.util.ArrayList;

public class blockStmtNode extends StmtNode{
    public ArrayList<StmtNode> statements;

    public blockStmtNode(position pos) {
        super(pos);
        statements = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
