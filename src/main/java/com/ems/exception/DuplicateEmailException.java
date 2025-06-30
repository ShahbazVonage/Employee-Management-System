package com.ems.exception;

/**
 * Custom Exception for handling duplicate Email,
 * Email is unique for every employees
 */
public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String msg){
        super(msg);
    }
}
