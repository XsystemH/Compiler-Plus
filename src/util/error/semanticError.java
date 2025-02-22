package util.error;

import util.position;

public class semanticError extends error{
    public semanticError(String msg, position pos) {
        super(msg, pos);
    }
}
