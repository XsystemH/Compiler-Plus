package IR.Expression.Register;

public class resReg extends Reg {
    public int num;

    public resReg(int num) {
        this.num = num;
    }

    @Override
    public String getString() {
        return "%.result." + num;
    }
}
