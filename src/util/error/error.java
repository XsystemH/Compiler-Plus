package util.error;

import util.position;

public abstract class error extends RuntimeException{
    public position pos;
    public String message;

    public error(String msg, position pos) {
        this.pos = pos;
        this.message = msg;
    }

    public String toString() {
        return message;
    }
}
