package IR.Instruction;

import IR.Expression.Expr;
import IR.label;

public class brInstr extends Instr{
    public Expr cond = null;
    public label trueLabel;
    public label falseLabel;
    public label destLabel;

    @Override
    public String getString() {
        if (cond != null) {
            return "br i1 " + cond.getString() + ", label " + trueLabel.getLabel() + ", label " + falseLabel.getLabel();
        }
        else {
            return "br label " + destLabel.getLabel();
        }
    }

    @Override
    public int getSpSize() {
        return 0;
    }
}
