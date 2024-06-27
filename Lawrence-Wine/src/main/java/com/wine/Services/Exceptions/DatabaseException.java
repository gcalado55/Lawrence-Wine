package com.wine.Services.Exceptions;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String msg){
        super(msg);
    }
}
