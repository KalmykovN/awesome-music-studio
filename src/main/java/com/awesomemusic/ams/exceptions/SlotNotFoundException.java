package com.awesomemusic.ams.exceptions;

public class SlotNotFoundException extends RuntimeException {
    public SlotNotFoundException(String message) {
        super(message);
    }

    public SlotNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}