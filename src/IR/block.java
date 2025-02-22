package IR;

import IR.Instruction.Instr;

import java.util.ArrayList;

public class block {
    public label Label;
    public ArrayList<Instr> instrs = new ArrayList<>();
    public Instr ctrl;

    public String getString() {
        StringBuilder ret = new StringBuilder();
        ret.append(Label.getString()).append("\n");
        for (Instr s : instrs) {
            ret.append(s.getString()).append("\n");
        }
        ret.append(ctrl.getString()).append("\n");
        return ret.toString();
    }

    public int getSpSize() {
        int ret = 0;
        for (Instr s : instrs) {
            ret += s.getSpSize();
        }
        return ret;
    }
}
