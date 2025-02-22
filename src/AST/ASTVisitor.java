package AST;

import AST.Stmt.*;
import AST.Expr.*;
import AST.Cons.*;

public interface ASTVisitor {
    void visit(ProgramNode it);
    void visit(ClassNode it);
    void visit(FuncNode it);
    void visit(MainNode it);

    void visit(blockStmtNode it);
    void visit(varDefStmtNode it);
    void visit(ifStmtNode it);
    void visit(forStmtNode it);
    void visit(whileStmtNode it);
    void visit(returnStmtNode it);
    void visit(breakStmtNode it);
    void visit(continueStmtNode it);
    void visit(expressionStmtNode it);

    void visit(priorityExprNode it);
    void visit(constantExprNode it);
    void visit(variableExprNode it);
    void visit(thisExprNode it);
    void visit(funcCallExprNode it);
    void visit(classMemExprNode it);
    void visit(classFuncExprNode it);
    void visit(arrayVisitExprNode it);
    void visit(newArrExprNode it);
    void visit(newVarExprNode newVarExprNode);
    void visit(nullExprNode it);
    void visit(leftExprNode it);
    void visit(rightExprNode it);
    void visit(binaryExprNode it);
    void visit(boolExprNode it);
    void visit(logicExprNode it);
    void visit(notExprNode it);
    void visit(ternaryExprNode it);
    void visit(assignExprNode it);
    void visit(formatStringNode it);

    void visit(arrConsNode it);
    void visit(boolConsNode it);
    void visit(intConsNode it);
    void visit(strConsNode it);

}
