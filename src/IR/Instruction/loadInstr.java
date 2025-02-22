package IR.Instruction;

import IR.Expression.Register.Reg;
import IR.IRType.IRType;

public class loadInstr extends Instr{
    public Reg result = null;
    public IRType type = null;
    public Reg pointer = null;

    @Override
    public String getString() {
        return result.getString() + " = load " + type.getString() + ", ptr " + pointer.getString();
    }

    @Override
    public int getSpSize() {
        return 1;
    }
}
