package Clases.entities;

import java.io.IOException;

public class ProcessingJsonFailedException extends IOException {
    public ProcessingJsonFailedException(String msg) {
        super(msg);
    }

    public ProcessingJsonFailedException(String msg, Throwable t) {
        super(msg, t);
    }
}