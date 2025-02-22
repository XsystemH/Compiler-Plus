package util.Type;

import Parser.MxParser;

import java.util.Objects;

public class Type extends BasicType{
    public int dim;
    public boolean isEmptyArray;
    public boolean isVoid = false;

    public Type() {
        isEmptyArray = true;
    }

    public Type(BasicType other) {
        super(other);
        if (other instanceof Type) dim = ((Type) other).dim;
    }

    public Type(ReturnType ret) {
        if (ret.isVoid) return;
        dim = ret.dim;
        isInt = ret.isInt;
        isString = ret.isString;
        isBool = ret.isBool;
        typeName = ret.typeName;
    }

    public Type(String typeName) {
        super(typeName);
        dim = 0;
    }

    public Type(String typeName, int dim) {
        super(typeName);
        this.dim = dim;
    }

    public Type(MxParser.TypeContext ctx) {
        super(ctx);
        dim = ctx.Left_Bracket().size();
    }

    public Type(MxParser.BasicTypeContext ctx) {
        super(ctx);
    }

    public boolean isEqual(Type t) {
        if (t == null) return false;
        if (dim != t.dim) return false;
        if (isInt != t.isInt) return false;
        if (isBool != t.isBool) return false;
        if (isNull != t.isNull) return false;
        return Objects.equals(typeName, t.typeName);
    }

    public boolean isBasic() {
        return dim == 0 && (isInt || isBool || isString);
    }

    public int getSize() {
        return 4;
    }
}
