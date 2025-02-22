package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import util.position;

public class ifStmtNode extends StmtNode{
    public ExprNode condition;
    public StmtNode thenBlock;
    public StmtNode elseBlock;

    public ifStmtNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
