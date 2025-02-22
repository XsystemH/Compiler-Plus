package IR.Instruction;

import IR.Expression.Expr;
import IR.Expression.Register.Reg;
import IR.IRType.IRType;

public class storeInstr extends Instr{
    public IRType type = null;
    public Expr value = null;
    public Reg ptr = null;

    @Override
    public String getString() {
        return "store " + type.getString() + " " + value.getString() + ", ptr " + ptr.getString();
    }

    @Override
    public int getSpSize() {
        return 0;
    }
}
