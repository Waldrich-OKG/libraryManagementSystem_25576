package com.waldrich.libraryManagementSystem_25576.exceptionsHandler;

public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException(String email){
        super("This email "+email+" is already taken!!!");
    }
}
