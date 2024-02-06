package com.stepaniuk.movieapi.exceptions.review;

import lombok.Getter;

@Getter
public class ReviewNotFoundException extends RuntimeException {
private final Long id;

public ReviewNotFoundException(Long id) {
    super("No review found with id: " + id);
    this.id = id;
}
}
