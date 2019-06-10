package com.git.kinomax094.exeption;

public class WrongNameException extends RuntimeException {

    private String message;

    public WrongNameException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
