package IR.Instruction;

import IR.Expression.Expr;
import IR.Expression.Register.Reg;
import IR.IRType.IRType;

import java.util.ArrayList;
import java.util.BitSet;

public class callInstr extends Instr{
    public Reg result = null;
    public IRType returnType = null;
    public String className = null;
    public String methodName = null;
    public ArrayList<IRType> paramTypes = new ArrayList<>();
    public ArrayList<Expr> paramExpr = new ArrayList<>();

    public BitSet occupied = new BitSet(20);

    @Override
    public String getString() {
        StringBuilder s;
        if (returnType != null) {
            s = new StringBuilder(result.getString() + " = call " + returnType.getString() + " @");
        }
        else {
            s = new StringBuilder("call void @");
        }
        if (className != null) {
            s.append(className).append("..");
        }
        s.append(methodName).append("(");
        for (int i = 0; i < paramTypes.size(); i++) {
            if (i > 0) {
                s.append(", ");
            }
            s.append(paramTypes.get(i).getString()).append(" ");
            s.append(paramExpr.get(i).getString());
        }
        s.append(")");
        return s.toString();
    }

    @Override
    public int getSpSize() {
        if (returnType == null) { return 0; }
        return 1;
    }
}
