package AST.Expr;

import AST.ASTVisitor;
import util.Type.Type;
import util.position;

public class logicExprNode extends ExprNode{
    public ExprNode lhs, rhs;

    public enum logicOpType {
        land, lor;
    }
    public logicOpType opCode;

    public logicExprNode(position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
