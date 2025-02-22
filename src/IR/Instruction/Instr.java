package IR.Instruction;

import IR.IRElement;

public abstract class Instr extends IRElement {
    public Instr() {}

    public abstract String getString();

    public abstract int getSpSize();
}
