package util.Decl;

import AST.FuncNode;
import util.Type.ReturnType;
import util.Type.Type;

import java.util.ArrayList;

public class FuncDecl {
    public String name;
    public ReturnType retType;
    public ArrayList<Type> paramType;
    public ArrayList<String> paramName;

    public FuncDecl(FuncNode func) {
        name = func.name;
        retType = func.returnType;
        paramType = new ArrayList<>();
        paramName = new ArrayList<>();
        paramType.addAll(func.paramType);
        paramName.addAll(func.paramName);
    }

    public FuncDecl(String name) {
        this.name = name;
        paramType = new ArrayList<>();
        paramName = new ArrayList<>();
        switch (name) {
            case "print", "println" -> {
                retType = new ReturnType("void");
                paramType.add(new Type("string"));
            }
            case "printInt", "printlnInt" -> {
                retType = new ReturnType("void");
                paramType.add(new Type("int"));
            }
            case "getString" -> {
                retType = new ReturnType("string");
            }
            case "getInt", "parseInt", "ord", "size" -> {
                retType = new ReturnType("int");
            }
            case "toString" -> {
                retType = new ReturnType("string");
                paramType.add(new Type("int"));
            }
            case "length" -> {
                retType = new ReturnType("int");
                paramType.add(new Type("sting"));
            }
            case "substring" -> {
                retType = new ReturnType("string");
                paramType.add(new Type("int"));
                paramType.add(new Type("int"));
            }
        }
    }
}
