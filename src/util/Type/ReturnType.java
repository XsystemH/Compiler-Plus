package util.Type;

import Parser.MxParser;

import java.util.Objects;

public class ReturnType extends Type{

    public ReturnType(BasicType other) {
        super(other);
        if (other instanceof ReturnType) {
            isVoid = ((ReturnType)other).isVoid;
        }
    }

    public ReturnType(String typeName) {
        super(typeName);
        if (typeName.equals("void")) {
            isVoid = true;
        }
    }

    public ReturnType(MxParser.ReturnTypeContext ctx) {
        if (ctx.getText().equals("void")) {
            isVoid = true;
        }
        else {
            isVoid = false;
            Type t = new Type(ctx.type());
            isInt = t.isInt;
            isString = t.isString;
            isBool = t.isBool;
            isNull = t.isNull;
            typeName = t.typeName;
            dim = t.dim;
        }
    }
}
