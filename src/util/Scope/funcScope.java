package util.Scope;

import util.Type.ReturnType;

public class funcScope extends Scope {
    public ReturnType returnType;

    public funcScope(Scope parent) {
        super(parent);
        scopeType = ScopeType.Func;
    }

    public void getReturn() {
        hasReturn = true;
    }
}
