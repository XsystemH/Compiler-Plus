package IR.Instruction;

import IR.Expression.Expr;
import IR.Expression.Register.Reg;
import IR.IRType.IRType;

public class icmpInstr extends Instr{
    public Reg result = null;
    public enum condType {
        eq, ne, ugt, uge, ult, ule, sgt, sge, slt, sle
    }
    private String[] condName = {
        "eq", "ne", "ugt", "uge", "ult", "ule", "sgt", "sge", "slt", "sle"
    };
    public condType cond;
    public IRType type = null;
    public Expr op1 = null;
    public Expr op2 = null;

    @Override
    public String getString() {
        return result.getString() + " = icmp " + condName[cond.ordinal()] + " " + type.getString() + " " + op1.getString() + ", " + op2.getString();
    }

    @Override
    public int getSpSize() {
        return 1;
    }
}
