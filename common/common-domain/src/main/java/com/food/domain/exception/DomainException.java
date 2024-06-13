package com.food.domain.exception;

public class DomainException extends RuntimeException {
    public DomainException(String msg){
        super(msg);
    }

    public DomainException(String msg, Throwable cause){
        super(msg, cause);
    }
}
