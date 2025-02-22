package util.Scope;

//import IR.Instruction.label;

public class loopScope extends Scope{
//    public label loopLabel;
//    public label skipLabel;

    public loopScope(Scope parent) {
        super(parent);
        scopeType = ScopeType.Loop;
    }
}
