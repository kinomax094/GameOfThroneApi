package com.git.kinomax094.exeption;

public class WrongPatternException extends RuntimeException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WrongPatternException(String name) {
        super();
        this.message = name;
    }
}
