package com.maple.sysant.common.exception;

public class GlobalException extends RuntimeException{

    private GlobalException(){
        super();
    }

    public GlobalException(String message) {
        super(message);
    }
}
