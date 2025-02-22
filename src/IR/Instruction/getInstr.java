package IR.Instruction;

import IR.Expression.Expr;
import IR.Expression.Register.Reg;
import IR.IRType.IRType;

import java.util.ArrayList;

public class getInstr extends Instr{
    public Reg result = null;
    public IRType type = null;
    public Expr ptr = null;
    public ArrayList<Expr> idx = new ArrayList<>();

    @Override
    public String getString() {
        StringBuilder str = new StringBuilder(result.getString() + " = getelementptr " + type.getString() + ", ptr " + ptr.getString());
        for (Expr e : idx) {
            str.append(", i32 ").append(e.getString());
        }
        return str.toString();
    }

    @Override
    public int getSpSize() {
        return 1;
    }
}
