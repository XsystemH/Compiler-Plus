package IR.Instruction;

import IR.Expression.Register.Reg;
import IR.IRType.IRType;

import java.util.ArrayList;

public class allocaInstr extends Instr{
    public Reg result = null;
    public IRType type = null;

    public ArrayList<Instr> defs = new ArrayList<>();
    public ArrayList<Instr> uses = new ArrayList<>();
    public boolean oneBlock = true;
    public String blockName = null;

    @Override
    public String getString() {
//        if (isRemoved)
//            return "//" + result.getString() + " = alloca " + type.getString();
        return result.getString() + " = alloca " + type.getString();
    }

    @Override
    public int getSpSize() {
        return 2;
    }
}
