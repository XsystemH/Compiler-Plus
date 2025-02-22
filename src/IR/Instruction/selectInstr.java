package IR.Instruction;

import IR.Expression.Expr;
import IR.Expression.Register.Reg;
import IR.IRType.IRType;

public class selectInstr extends Instr{
    public Reg result = null;
    public Expr cond = null;
    public IRType ty1 = null;
    public IRType ty2 = null;
    public Expr val1 = null;
    public Expr val2 = null;

    @Override
    public String getString() {
        return result.getString() + " = select i1 " + cond.getString() + ", " + ty1.getString() + " " + val1.getString()
                + ", " + ty2.getString() + " " + val2.getString();
    }

    @Override
    public int getSpSize() {
        return 1;
    }
}
