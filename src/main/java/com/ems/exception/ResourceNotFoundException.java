package com.ems.exception;

 //Custom Exception for handling employees not found
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
