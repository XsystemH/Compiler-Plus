package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import util.position;

public class forStmtNode extends StmtNode{
    public StmtNode initialStmt;
    public ExprNode conditionExpr;
    public ExprNode incrementExpr;
    public StmtNode bodyStmt;

    public forStmtNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
