package util.Type;

import Parser.MxParser;

import java.util.HashMap;

public class BasicType {
    public String typeName;
    public boolean isInt = false, isBool = false, isString = false, isNull = false;
    public HashMap<String, Type> members;

    public BasicType() {
        members = new HashMap<>();
    }

    public BasicType(BasicType other) {
        isInt = other.isInt;
        isBool = other.isBool;
        isString = other.isString;
        isNull = other.isNull;
        typeName = other.typeName;
        members = other.members;
    }

    public BasicType(String typeName) {
        switch (typeName) {
            case "int" -> isInt = true;
            case "bool" -> isBool = true;
            case "string" -> isString = true;
            case "null" -> isNull = true;
            default -> this.typeName = typeName;
        }
    }

    public BasicType(MxParser.TypeContext ctx) {
        this(ctx.basicType().getText());
    }

    public BasicType(MxParser.BasicTypeContext ctx) {
        this(ctx.getText());
    }
}
