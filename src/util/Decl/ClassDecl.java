package util.Decl;

import AST.ClassNode;
import AST.FuncNode;
import AST.Stmt.varDefStmtNode;
import Parser.MxParser;
import util.Type.Type;

import java.util.HashMap;

public class ClassDecl {
    public String name;
    public HashMap<String, Type> vars;
    public HashMap<String, Integer> idx;
    public HashMap<String, FuncDecl> functions;
    public boolean hasConstructor = false;

    public ClassDecl(String name) {
        this.name = name;
        this.vars = new HashMap<>();
        this.idx = new HashMap<>();
        this.functions = new HashMap<>();
    }

    public ClassDecl(ClassNode cla) {
        this.name = cla.name;
        this.vars = new HashMap<>();
        this.idx = new HashMap<>();
        this.functions = new HashMap<>();
        this.hasConstructor = (cla.constructor != null);

        int i = 0;
        for (varDefStmtNode var : cla.vars) {
            for (String name : var.name) {
                this.vars.put(name, var.type);
                this.idx.put(name, i++);
            }
        }
        for (FuncNode func : cla.functions) {
            this.functions.put(func.name, new FuncDecl(func));
        }
    }

    public int getSize() {
        return vars.size() * 4;
    }
}
