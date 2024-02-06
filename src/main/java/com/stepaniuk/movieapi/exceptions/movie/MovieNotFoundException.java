package com.stepaniuk.movieapi.exceptions.movie;

import lombok.Getter;

@Getter
public class MovieNotFoundException extends RuntimeException{
    private final Long id;
    public MovieNotFoundException(Long id) {
        super("No movie found with id: " + id);
        this.id = id;
    }
}
