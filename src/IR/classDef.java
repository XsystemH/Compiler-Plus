package IR;

import IR.IRType.IRType;
import IR.IRType.classType;

import java.util.ArrayList;

public class classDef extends IRElement {
    public String name;
    public ArrayList<IRType> members = new ArrayList<>();

    @Override
    public String getString() {
        StringBuilder s = new StringBuilder("%class." + name + " = type { ");
        for (int i = 0; i < members.size(); i++) {
            if (i > 0) s.append(", ");
            if (members.get(i) instanceof classType) {
                s.append("ptr");
            }
            else s.append(members.get(i).getString());
        }
        s.append(" }\n");
        return s.toString();
    }
}
