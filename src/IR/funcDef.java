package IR;

import IR.IRType.IRType;
import IR.Instruction.Instr;

import java.util.ArrayList;

public class funcDef extends IRElement {
    public IRType returnType;
    public String className;
    public String name;
    public ArrayList<String> params = new ArrayList<>();
    public ArrayList<IRType> paramTypes = new ArrayList<>();
    public ArrayList<Instr> alloc = new ArrayList<>();

    public ArrayList<block> blocks = new ArrayList<>();

    @Override
    public String getString() {
        StringBuilder str = new StringBuilder("define ");
        if (returnType != null) {
            str.append(returnType.getString()).append(" @");
        }
        else {
            str.append("void @");
        }
        if (className != null) {
            str.append(className).append("..");
        }
        str.append(name).append("(");
        for (int i = 0; i < params.size(); i++) {
            if (i > 0) str.append(", ");
            str.append(paramTypes.get(i).getString()).append(" %");
            str.append(params.get(i));
        }
        str.append(") {\n");
//        for (BasicBlock block : cfg.rpo) {
//            str.append(block.Label.getString()).append("\n");
////            for (phiInstr phi : block.phiMap.values()) {
////                str.append(phi.getString()).append("\n");
////            }
//            for (Instr instr : block.Instrs) {
//                str.append("  ").append(instr.getString()).append("\n");
//            }
//            str.append("  ").append(block.Ctrl.getString()).append("\n");
//        }
        str.append("}\n");
        return str.toString();
    }
}
