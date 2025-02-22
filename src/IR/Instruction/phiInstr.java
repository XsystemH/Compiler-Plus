package IR.Instruction;

import IR.Expression.Constant.boolCons;
import IR.Expression.Constant.intCons;
import IR.Expression.Constant.ptrCons;
import IR.Expression.Expr;
import IR.Expression.Register.Reg;
import IR.IRType.IRType;
import IR.IRType.IntType;

import java.util.ArrayList;

public class phiInstr extends Instr{
    public Reg result;
    public IRType type;
    public ArrayList<Expr> vals = new ArrayList<>();
    public ArrayList<String> blocks = new ArrayList<>();

    public Reg ori;

    public void addVal(Expr expr, String label) {
        vals.add(expr);
        blocks.add(label);
    }

    public void addEmpty(String label) {
        blocks.add(label);
        if (type instanceof IntType i) {
            if (i.N == 32) vals.add(new intCons(0));
            else if (i.N == 1) vals.add(new boolCons(false));
        }
        else vals.add(new ptrCons());
    }

    @Override
    public String getString() {
        StringBuilder str = new StringBuilder();
        str.append(result.getString());
        str.append(" = phi ");
        str.append(type.getString());
        str.append(" ");
        for (int i = 0; i < vals.size(); i++) {
            if (i > 0) {
                str.append(", ");
            }
            str.append("[ ");
            str.append(vals.get(i).getString());
            str.append(", ");
            str.append(blocks.get(i));
            str.append(" ]");
        }
        return str.toString();
    }

    @Override
    public int getSpSize() {
        return 1;
    }
}
