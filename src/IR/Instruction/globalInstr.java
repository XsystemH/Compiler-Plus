package IR.Instruction;

import IR.Expression.Register.Reg;
import IR.IRType.IRType;

public class globalInstr extends Instr{
    public Reg result;
    public IRType type;

    @Override
    public String getString() {
        return result.getString() + " = global " + type.getString()+ " " + type.getDefault();
    }

    @Override
    public int getSpSize() {
        return 0;
    }
}
