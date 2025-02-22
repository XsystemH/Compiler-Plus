package Frontend;

import AST.*;
import AST.Cons.arrConsNode;
import AST.Cons.boolConsNode;
import AST.Cons.intConsNode;
import AST.Cons.strConsNode;
import AST.Expr.*;
import AST.Stmt.*;
import util.Scope.globalScope;

public class SymbolCollector implements ASTVisitor {
    public globalScope gScope;

    public SymbolCollector(globalScope gScope) {
        this.gScope = gScope;
    }

    @Override
    public void visit(ProgramNode it) {
        for (ASTNode child : it.members) {
            child.accept(this);
        }
    }

    @Override
    public void visit(ClassNode it) {
        gScope.defineClass(it);
    }

    @Override
    public void visit(FuncNode it) {
        gScope.defineFunction(it);
    }

    @Override
    public void visit(MainNode it) {}
    @Override
    public void visit(blockStmtNode it) {}
    @Override
    public void visit(varDefStmtNode it) {}
    @Override
    public void visit(ifStmtNode it) {}
    @Override
    public void visit(forStmtNode it) {}
    @Override
    public void visit(whileStmtNode it) {}
    @Override
    public void visit(returnStmtNode it) {}
    @Override
    public void visit(breakStmtNode it) {}
    @Override
    public void visit(continueStmtNode it) {}
    @Override
    public void visit(expressionStmtNode it) {}
    @Override
    public void visit(priorityExprNode it) {}
    @Override
    public void visit(constantExprNode it) {}
    @Override
    public void visit(variableExprNode it) {}
    @Override
    public void visit(thisExprNode it) {}
    @Override
    public void visit(funcCallExprNode it) {}
    @Override
    public void visit(classMemExprNode it) {}
    @Override
    public void visit(classFuncExprNode it) {}
    @Override
    public void visit(arrayVisitExprNode it) {}
    @Override
    public void visit(newArrExprNode it) {}
    @Override
    public void visit(newVarExprNode newVarExprNode) {}
    @Override
    public void visit(nullExprNode it) {}
    @Override
    public void visit(leftExprNode it) {}
    @Override
    public void visit(rightExprNode it) {}
    @Override
    public void visit(binaryExprNode it) {}
    @Override
    public void visit(boolExprNode it) {}
    @Override
    public void visit(logicExprNode it) {}
    @Override
    public void visit(notExprNode it) {}
    @Override
    public void visit(ternaryExprNode it) {}
    @Override
    public void visit(assignExprNode it) {}
    @Override
    public void visit(formatStringNode it) {}
    @Override
    public void visit(arrConsNode it) {}
    @Override
    public void visit(boolConsNode it) {}
    @Override
    public void visit(intConsNode it) {}
    @Override
    public void visit(strConsNode it) {}
}
