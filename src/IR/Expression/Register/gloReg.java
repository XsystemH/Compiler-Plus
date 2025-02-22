package IR.Expression.Register;

public class gloReg extends varReg{
    public gloReg(String n) {
        super(n, 0, 0);
    }

    @Override
    public String getString() {
        return "@" + name;
    }
}
