package IR.Expression.Constant;

public class intCons extends Cons{
    public int value;

    public intCons(int v) {
        value = v;
    }

    @Override
    public String getString() {
        return "" + value;
    }
}
