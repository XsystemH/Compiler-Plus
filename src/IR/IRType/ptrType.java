package IR.IRType;

public class ptrType extends IRType{
    public ptrType(){}

    @Override
    public String getString() {
        return "ptr";
    }

    @Override
    public String getDefault() {
        return "null";
    }
}
