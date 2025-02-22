package IR;

import java.util.ArrayList;

public class Program {
    public ArrayList<IRElement> elements = new ArrayList<>();

    public String getString() {
        StringBuilder str = new StringBuilder();
        str.append("declare i32 @scanf(ptr, ...);\n");
        str.append("declare i32 @printf(ptr, ...);\n");
        str.append("declare i32 @sprintf(ptr, ptr, ...);\n");
        str.append("declare void @print(ptr);\n");
        str.append("declare void @println(ptr);\n");
        str.append("declare void @printInt(i32);\n");
        str.append("declare void @printlnInt(i32);\n");
        str.append("declare ptr @malloc(i32);\n");
        str.append("declare ptr @.malloc(i32);\n");
        str.append("declare ptr @.malloc_array(i32, i32);\n");
        str.append("declare i32 @.arr.size(ptr);\n");
        str.append("declare ptr @getString();\n");
        str.append("declare i32 @getInt();\n");
        str.append("declare ptr @toString(i32);\n");
        str.append("declare ptr @.str.add(ptr, ptr);\n");
        str.append("declare i1 @.str.eq(ptr, ptr);\n");
        str.append("declare i1 @.str.ne(ptr, ptr);\n");
        str.append("declare i1 @.str.lt(ptr, ptr);\n");
        str.append("declare i1 @.str.le(ptr, ptr);\n");
        str.append("declare i1 @.str.gt(ptr, ptr);\n");
        str.append("declare i1 @.str.ge(ptr, ptr);\n");
        str.append("declare i32 @.str.length(ptr);\n");
        str.append("declare ptr @.str.substr(ptr, i32, i32);\n");
        str.append("declare i32 @.str.parseInt(ptr);\n");
        str.append("declare i32 @.str.ord(ptr, i32);\n");
        for (IRElement r : elements) {
            str.append(r.getString()).append("\n");
        }
        return str.toString();
    }
}
