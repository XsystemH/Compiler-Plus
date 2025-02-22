package util.Scope;

import AST.ClassNode;
import AST.FuncNode;
import util.Decl.*;
import util.Type.Type;

import java.util.HashMap;

public class globalScope extends Scope{
    public HashMap<String, ClassDecl> classes;
    public HashMap<String, FuncDecl> functions;

    public globalScope(Scope parent) {
        super(parent);
        classes = new HashMap<>();
        functions = new HashMap<>();
        scopeType = ScopeType.Global;

        functions.put("print", new FuncDecl("print"));
        functions.put("println", new FuncDecl("println"));
        functions.put("printInt", new FuncDecl("printInt"));
        functions.put("printlnInt", new FuncDecl("printlnInt"));
        functions.put("getString", new FuncDecl("getString"));
        functions.put("getInt", new FuncDecl("getInt"));
        functions.put("toString", new FuncDecl("toString"));

        ClassDecl array = new ClassDecl("array");
        array.functions.put("size", new FuncDecl("size"));
        ClassDecl string = new ClassDecl("string");
        string.functions.put("length", new FuncDecl("length"));
        string.functions.put("substring", new FuncDecl("substring"));
        string.functions.put("parseInt", new FuncDecl("parseInt"));
        string.functions.put("ord", new FuncDecl("ord"));
        classes.put("array", array);
        classes.put("string", string);
    }

    public boolean containsType(Type t){
        if (t.isInt || t.isBool || t.isString) return true;
        return classes.containsKey(t.typeName);
    }

    public void defineFunction(FuncNode node) {
        if (functions.get(node.name) != null) {
            throw new RuntimeException("Multiple Definitions");
        }
        functions.put(node.name, new FuncDecl(node));
    }

    public void defineClass(ClassNode node) {
        if (classes.get(node.name) != null) {
            throw new RuntimeException("Multiple Definitions");
        }
        classes.put(node.name, new ClassDecl(node));
    }

    public Type getClassType(String name) {
        ClassDecl c = classes.get(name);
        Type type = new Type(c.name);
        type.members = c.vars;
        return type;
    }

    public FuncDecl getFunction(String name) {
        return functions.get(name);
    }

    public ClassDecl getClass(String name) {
        return classes.get(name);
    }
}
