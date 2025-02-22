package AST;

import AST.Stmt.StmtNode;
import util.Type.ReturnType;
import util.Type.Type;
import util.position;

import java.util.ArrayList;

public class FuncNode extends ASTNode {
    public String name;
    public ReturnType returnType;
    public ArrayList<Type> paramType;
    public ArrayList<String> paramName;
    public ArrayList<StmtNode> body;

    public FuncNode(position pos) {
        super(pos);
        paramType = new ArrayList<>();
        paramName = new ArrayList<>();
        body = new ArrayList<>();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
