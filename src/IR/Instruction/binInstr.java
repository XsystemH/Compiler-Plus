package IR.Instruction;

import IR.Expression.Expr;
import IR.Expression.Register.Reg;
import IR.IRType.IRType;

public class binInstr extends Instr {
    public Reg result;
    public enum binaryOP {
        add, sub, mul, sdiv, srem, shl, ashr, and, or, xor
    }
    String[] opName = {"add", "sub", "mul", "sdiv", "srem", "shl", "ashr", "and", "or", "xor"};
    public binaryOP op;
    public IRType type;
    public Expr operand1;
    public Expr operand2;

    @Override
    public String getString() {
        return result.getString() + " = " +opName[op.ordinal()] + " " +
                type.getString() + " " + operand1.getString() + ", " + operand2.getString();
    }

    @Override
    public int getSpSize() {
        return 1;
    }
}
