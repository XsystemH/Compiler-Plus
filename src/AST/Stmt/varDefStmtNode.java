package AST.Stmt;

import AST.ASTVisitor;
import AST.Expr.ExprNode;
import util.Type.Type;
import util.position;

import java.util.ArrayList;

public class varDefStmtNode extends StmtNode{
    public Type type;
    public ArrayList<String> name;
    public ArrayList<ExprNode> expr;

    public varDefStmtNode(position pos) {
        super(pos);
        name = new ArrayList<>();
        expr = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
