package IR.Instruction;

import IR.Expression.Expr;
import IR.IRType.IRType;

public class retInstr extends Instr {
    public IRType type = null;
    public Expr value;

    @Override
    public String getString() {
        if (type == null) {
            return "ret void";
        }
        else {
            return "ret " + type.getString() + " " + value.getString();
        }
    }

    @Override
    public int getSpSize() {
        return 0;
    }
}
