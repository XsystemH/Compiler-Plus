package IR.Expression.Constant;

public class boolCons extends Cons{
    public boolean value;

    public boolCons(boolean value) {
        this.value = value;
    }

    @Override
    public String getString() {
        return value ? "true" : "false";
    }
}
