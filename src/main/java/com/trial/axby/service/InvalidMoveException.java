package com.trial.axby.service;

public class InvalidMoveException  extends RuntimeException {
    public InvalidMoveException(Exception exception){
        super(exception);
    }
    public InvalidMoveException(String message, Exception exception){
        super(message,exception);
    }
    public InvalidMoveException(String message){
        super(message);
    }
}
