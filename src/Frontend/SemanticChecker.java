package Frontend;

import AST.*;
import AST.Cons.*;
import AST.Expr.*;
import AST.Stmt.*;
import util.Decl.ClassDecl;
import util.Decl.FuncDecl;
import util.Scope.*;
import util.Type.ReturnType;
import util.Type.Type;
import util.error.semanticError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class SemanticChecker implements ASTVisitor {
    private globalScope gScope;
    private Scope curScope;

    public SemanticChecker(globalScope gScope) {
        curScope = this.gScope = gScope;
    }

    @Override
    public void visit(ProgramNode it) {
        ArrayList<varDefStmtNode> noPre = new ArrayList<>();
        for (ASTNode child : it.members) {
            child.accept(this);
        }
//        it.mainFn.accept(this);
    }

    @Override
    public void visit(ClassNode it) {
        curScope = new classScope(curScope);
        curScope.className = it.name;
        for (varDefStmtNode v : it.vars) {
            v.accept(this);
        }
        for (FuncNode f : it.functions) {
            f.accept(this);
        }
        if (it.constructor != null) {
            curScope = new funcScope(curScope);
            curScope.returnType = new ReturnType("void");
            it.constructor.accept(this);
            curScope = curScope.parent;
        }
        curScope = curScope.parent;
    }

    @Override
    public void visit(FuncNode it) {
        curScope = new funcScope(curScope);
        curScope.returnType = it.returnType;
        for (int i = 0; i < it.paramName.size(); i++) {
            curScope.defineVariable(it.paramName.get(i), it.paramType.get(i), it.pos, false);
        }
        for (StmtNode s : it.body) {
            if (s != null)
                s.accept(this);
        }
        if (!curScope.hasReturn && !it.returnType.isVoid) {
            throw new semanticError("Missing Return Statement", it.pos);
        }
        curScope = curScope.parent;
    }

    @Override
    public void visit(MainNode it) {
        curScope = new funcScope(curScope);
        curScope.returnType = new ReturnType("int");
        for (StmtNode s : it.statements) {
            if (s != null) // skip empty
                s.accept(this);
        }
        curScope = curScope.parent;
    }

    @Override
    public void visit(blockStmtNode it) {
        curScope = new Scope(curScope);
        for (StmtNode s : it.statements) {
            if (s != null) // skip empty
                s.accept(this);
        }
        curScope = curScope.parent;
    }

    @Override
    public void visit(varDefStmtNode it) {
        if (!gScope.containsType(it.type)) {
            throw new semanticError("Undefined Identifier", it.pos);
        }

        for (ExprNode expr : it.expr) {
            if (expr == null) continue;
            expr.accept(this);
            if (!expr.type.isEqual(it.type)) {
                if (expr.type.isNull) {
                    if (it.type.dim == 0 && (it.type.isInt || it.type.isBool || it.type.isString))
                        throw new semanticError("Type Mismatch", it.pos);
                }
                else throw new semanticError("Type Mismatch", it.pos);
            }
        }
        for (String name : it.name) {
            if (gScope.getFunction(name) != null) {
                throw new semanticError("Multiple Definitions", it.pos);
            }
            curScope.defineVariable(name, it.type, it.pos, false);
        }
    }

    @Override
    public void visit(ifStmtNode it) {
        it.condition.accept(this);
        if (!it.condition.type.isEqual(new Type("bool"))) {
            throw new semanticError("Invalid Type", it.pos);
        }
        curScope = new Scope(curScope);
        if (it.thenBlock != null)
            it.thenBlock.accept(this);
        curScope = curScope.parent;
        curScope = new Scope(curScope);
        if (it.elseBlock != null) {
            it.elseBlock.accept(this);
        }
        curScope = curScope.parent;
    }

    @Override
    public void visit(forStmtNode it) {
        curScope = new loopScope(curScope);
        if (it.initialStmt != null) {
            it.initialStmt.accept(this);
        }
        if (it.conditionExpr != null) {
            it.conditionExpr.accept(this);
            if (!it.conditionExpr.type.isEqual(new Type("bool"))) {
                throw new semanticError("Invalid Type", it.pos);
            }
        }
        if (it.incrementExpr != null) {
            it.incrementExpr.accept(this);
        }
        if (it.bodyStmt != null)
            it.bodyStmt.accept(this);
        curScope = curScope.parent;
    }

    @Override
    public void visit(whileStmtNode it) {
        it.condition.accept(this);
        if (!it.condition.type.isEqual(new Type("bool"))) {
            throw new semanticError("Type Mismatch", it.pos);
        }
        curScope = new loopScope(curScope);
        if (it.body != null)
            it.body.accept(this);
        curScope = curScope.parent;
    }

    @Override
    public void visit(returnStmtNode it) {
        if (!curScope.isInFunction()) {
            throw new semanticError("Return outside Function", it.pos);
        }
        if (it.expr != null) {
            it.expr.accept(this);
            if (!it.expr.type.isEqual(curScope.returnType)) {
                if (it.expr.type.isNull) {
                    if (curScope.returnType.isBasic()) {
                        throw new semanticError("Type Mismatch", it.pos);
                    }
                }
                else throw new semanticError("Type Mismatch", it.pos);
            }
        }
        curScope.getReturn();
    }

    @Override
    public void visit(breakStmtNode it) {
        if (!curScope.isInLoop()) {
            throw new semanticError("Invalid Control Flow", it.pos);
        }
    }

    @Override
    public void visit(continueStmtNode it) {
        if (!curScope.isInLoop()) {
            throw new semanticError("Invalid Control Flow", it.pos);
        }
    }

    @Override
    public void visit(expressionStmtNode it) {
        it.expression.accept(this);
    }

    @Override
    public void visit(priorityExprNode it) {
        it.expr.accept(this);
        it.type = it.expr.type;
        it.isLeft = it.expr.isLeft;
    }

    @Override
    public void visit(constantExprNode it) {
        it.constant.accept(this);
        it.type = it.constant.type;
        it.isLeft = false;
    }

    @Override
    public void visit(variableExprNode it) {
        if (!curScope.containsVariable(it.name, true)) {
            throw new semanticError("Undefined Identifier", it.pos);
        }
        it.type = curScope.getType(it.name, true);
        it.isLeft = true;
    }

    @Override
    public void visit(thisExprNode it) {
        if (curScope.isInClass() == null) {
            throw new semanticError("Invalid This", it.pos);
        }
        it.type = gScope.getClassType(curScope.isInClass());
        it.isLeft = false;
    }

    @Override
    public void visit(funcCallExprNode it) {
        HashMap<String, FuncDecl> classFuncs = new HashMap<>();
        if (curScope.isInClass() != null) {
            classFuncs = gScope.classes.get(curScope.isInClass()).functions;
        }
        FuncDecl func;
        if (gScope.getFunction(it.funcName) == null) {
            if (classFuncs.isEmpty())
                throw new semanticError("Undefined Identifier", it.pos);
            else if (!classFuncs.containsKey(it.funcName)) {
                throw new semanticError("Undefined Identifier", it.pos);
            }
            else {
                func = classFuncs.get(it.funcName);
            }
        }
        else {
            func = gScope.getFunction(it.funcName);
        }
        if (classFuncs.containsKey(it.funcName)) {
            func = classFuncs.get(it.funcName); // in classFunc first
        }
        int t = 0;
        for (ExprNode expr : it.parameters) {
            expr.accept(this);
            if (!expr.type.isEqual(func.paramType.get(t))) {
                if (func.paramType.get(t).isBasic() || !expr.type.isNull) {
                    throw new semanticError("Type Mismatch", it.pos);
                }
            }
            t++;
        }
        if (t != func.paramType.size()) {
            throw new semanticError("Type Mismatch", it.pos);
        }
        it.type = func.retType;
        it.isLeft = false;
    }

    @Override
    public void visit(classMemExprNode it) {
        it.className.accept(this);
        String className = it.className.type.typeName;
        ClassDecl cla = gScope.getClass(className);
        if (cla == null) {
            if (it.className.type.isString)
                cla = gScope.getClass("string");
            else if (it.className.type.dim > 0)
                cla = gScope.getClass("array");
            else throw new semanticError("Undefined Identifier", it.pos);
        }
        if (cla.vars.get(it.memName) == null) {
            throw new semanticError("Undefined Identifier", it.pos);
        }
        it.type = cla.vars.get(it.memName);
        it.isLeft = true;
    }

    @Override
    public void visit(classFuncExprNode it) {
        it.className.accept(this);
        for (ExprNode expr : it.parameters) {
            expr.accept(this);
        }
        ClassDecl cla = gScope.getClass(it.className.type.typeName);
        if (cla == null) {
            if (it.className.type.isString)
                cla = gScope.getClass("string");
            else if (it.className.type.dim > 0)
                cla = gScope.getClass("array");
            else throw new semanticError("Undefined Identifier", it.pos);
        }
        if (cla.functions.get(it.funcName) == null) {
            if (it.className.type.dim == 0 || !Objects.equals(it.funcName, "size"))
                throw new semanticError("Undefined Identifier", it.pos);
            else it.type = new ReturnType("int");
        }
        else it.type = cla.functions.get(it.funcName).retType;
        it.isLeft = false;
    }

    @Override
    public void visit(arrayVisitExprNode it) {
        it.index.accept(this);
        if (!it.index.type.isEqual(new Type("int"))) {
            throw new semanticError("Type Mismatch", it.pos);
        }
        it.arrayName.accept(this);
        it.type = new Type(it.arrayName.type);
        it.type.dim -= 1;
        if (it.type.dim < 0) {
            throw new semanticError("Dimension Out Of Bound", it.pos);
        }
        it.isLeft = true;
    }

    @Override
    public void visit(newArrExprNode it) {
        for (ExprNode expr : it.expr) {
            expr.accept(this);
            if (!expr.type.isInt) {
                throw new semanticError("Type Mismatch", it.pos);
            }
        }
        if (it.arr != null) {
            it.arr.accept(this);
            if (!it.arr.type.isEqual(it.type)) {
                throw new semanticError("Type Mismatch", it.pos);
            }
        }
        it.isLeft = false;
    }

    @Override
    public void visit(newVarExprNode it) {
        it.isLeft = false;
    }

    @Override
    public void visit(nullExprNode it) {
        it.type = new Type("null");
        it.isLeft = false;
    }

    @Override
    public void visit(leftExprNode it) {
        it.expr.accept(this);
        if (!it.expr.type.isInt) {
            throw new semanticError("Type Mismatch", it.pos);
        }
        if ((it.opCode==leftExprNode.leftOpType.add||it.opCode==leftExprNode.leftOpType.sub)&&!it.expr.isLeft) {
            throw new semanticError("Type Mismatch", it.pos);
        }
        it.type = it.expr.type;
        it.isLeft = it.opCode==leftExprNode.leftOpType.add||it.opCode==leftExprNode.leftOpType.sub;
    }

    @Override
    public void visit(rightExprNode it) {
        it.expr.accept(this);
        if (!it.expr.type.isInt) throw new semanticError("Type Mismatch", it.pos);
        if (!it.expr.isLeft) {
            throw new semanticError("Type Mismatch", it.pos);
        }
        it.type = it.expr.type;
        it.isLeft = false;
    }

    @Override
    public void visit(binaryExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.type == null) {
            throw new semanticError("Type Mismatch", it.pos);
        }
        if (!it.lhs.type.isEqual(it.rhs.type)) {
            throw new semanticError("Type Mismatch", it.pos);
        }
        if (it.opCode == binaryExprNode.binaryOpType.add) {
            if (!it.lhs.type.isInt && !it.lhs.type.isString) {
                throw new semanticError("Invalid Type", it.pos);
            }
        }
        else if (!it.lhs.type.isInt) {
            throw new semanticError("Type Mismatch", it.pos);
        }
        it.type = it.lhs.type;
        it.isLeft = false;
    }

    @Override
    public void visit(boolExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.type.isEqual(it.rhs.type)) {
            if (it.lhs.type.isBasic() || !it.rhs.type.isNull)
                throw new semanticError("Type Mismatch", it.pos);
        }
        if (it.opCode != boolExprNode.boolOpType.equal && it.opCode != boolExprNode.boolOpType.notEqual) {
            if (it.lhs.type.dim > 0 || it.lhs.type.isBool) {
                throw new semanticError("Type Mismatch", it.pos);
            }
        }
        it.type = new Type("bool");
        it.isLeft = false;
    }

    @Override
    public void visit(logicExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (!it.lhs.type.isEqual(it.rhs.type) || !it.lhs.type.isBool) {
            throw new semanticError("Type Mismatch", it.pos);
        }
        it.type = new Type("bool");
        it.isLeft = false;
    }

    @Override
    public void visit(notExprNode it) {
        it.expr.accept(this);
        if (!it.expr.type.isBool) {
            throw new semanticError("Type Mismatch", it.pos);
        }
        it.type = new Type("bool");
        it.isLeft = false;
    }

    @Override
    public void visit(ternaryExprNode it) {
        it.condition.accept(this);
        it.case0.accept(this);
        it.case1.accept(this);
        if (!it.condition.type.isBool) {
            throw new semanticError("Invalid Type", it.pos);
        }
        if (!it.case0.type.isEqual(it.case1.type)) {
            if (it.case0.type.isNull) {
                if (it.case1.type.isBasic()) {
                    throw new semanticError("Type Mismatch", it.pos);
                }
            }
            else if (it.case1.type.isNull) {
                if (it.case0.type.isBasic()) {
                    throw new semanticError("Type Mismatch", it.pos);
                }
            }
            else throw new semanticError("Type Mismatch", it.pos);
        }
        it.type = it.case0.type;
        it.isLeft = false;
    }

    @Override
    public void visit(assignExprNode it) {
        it.lhs.accept(this);
        it.rhs.accept(this);
        if (it.lhs.type == null) {
            throw new semanticError("Type Mismatch", it.pos);
        }
        if (!it.lhs.type.isEqual(it.rhs.type)) {
            if (it.rhs.type.isNull) {
                if (it.lhs.type.dim == 0 && (it.lhs.type.isInt || it.lhs.type.isBool || it.lhs.type.isString))
                    throw new semanticError("Type Mismatch", it.pos);
            }
            else throw new semanticError("Type Mismatch", it.pos);
        }
        if (!it.lhs.isLeft) {
            throw new semanticError("Type Mismatch", it.pos);
        }
        it.type = new Type("null");
        it.isLeft = false;
    }

    @Override
    public void visit(formatStringNode it) {
        for (ExprNode expr : it.exprList) {
            expr.accept(this);
            if (!expr.type.isBool && !expr.type.isInt && !expr.type.isString) {
                throw new semanticError("Type Mismatch", it.pos);
            }
        }
        it.type = new Type("string");
    }

    @Override
    public void visit(arrConsNode it) {
        Type contentType = null;
        for (ConsNode cons : it.content) {
            cons.accept(this);
            if (contentType == null) {
                contentType = cons.type;
            }
            if (contentType.isEmptyArray) {
                contentType = cons.type;
            }
//            if (!cons.type.isEqual(contentType) && !cons.type.isEmptyArray) {
//                throw new semanticError("Type Mismatch", it.pos);
//            }
        }
        if (contentType == null) {
            contentType = new Type();
        }
        it.type = new Type(contentType);
        it.type.dim += 1;
    }

    @Override
    public void visit(boolConsNode it) {
        it.type = new Type("bool");
    }

    @Override
    public void visit(intConsNode it) {
        it.type = new Type("int");
    }

    @Override
    public void visit(strConsNode it) {
        it.type = new Type("string");
    }
}
