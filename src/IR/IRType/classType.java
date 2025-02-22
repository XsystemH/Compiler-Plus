package IR.IRType;

public class classType extends IRType{
    public String name;
    // todo member?

    public classType(String name) {
        this.name = name;
    }

    @Override
    public String getString() {
        return "%class." + name;
    }

    @Override
    public String getDefault() {
        return "null";
    }
}
