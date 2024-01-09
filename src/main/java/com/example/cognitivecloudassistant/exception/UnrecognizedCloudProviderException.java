package com.example.cognitivecloudassistant.exception;

public class UnrecognizedCloudProviderException extends RuntimeException{
    public UnrecognizedCloudProviderException(String message){
        super(message);
    }
}
