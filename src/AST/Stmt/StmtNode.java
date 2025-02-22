package AST.Stmt;

import AST.ASTNode;
import util.position;

public abstract class StmtNode extends ASTNode {
    public StmtNode(position pos) {
        super(pos);
    }
}
