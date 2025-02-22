package AST.Stmt;

import AST.ASTVisitor;
import util.position;

public class continueStmtNode extends StmtNode{
    public continueStmtNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
