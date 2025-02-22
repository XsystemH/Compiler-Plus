package IR;

public class label {
    public String info;
    public int num;

    public label(int n) {
        info = "";
        num = n;
    }

    public label(String info, int n) {
        this.info = info;
        num = n;
    }

    public String getString() {
        if (num == -1) return info + ":";
        return "label_" + info + "_" + num + ":";
    }

    public String getLabel() {
        if (num == -1) return "%" + info;
        return "%label_" + info + "_" + num;
    }
}
