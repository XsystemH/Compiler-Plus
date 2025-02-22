package IR.Instruction;

import IR.Expression.Register.Reg;

public class preStrInstr extends Instr{
    public Reg reg;
    public int size;
    public String str;

    @Override
    public String getString() {
        return reg.getString() + " = private unnamed_addr constant [" + size + " x i8] c\"" + str + "\\00" + "\"";
    }

    @Override
    public int getSpSize() {
        return 0;
    }
}
