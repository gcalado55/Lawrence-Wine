package com.wine.Services.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Object id){
        super("Resource not found. ID = " + id);
    }
}
