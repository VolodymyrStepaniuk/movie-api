package com.stepaniuk.movieapi.exceptions;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(){
        super("No movie found");
    }
    public MovieNotFoundException(String message){
        super(message);
    }
    public MovieNotFoundException(Throwable throwable){
        super(throwable);
    }
}
