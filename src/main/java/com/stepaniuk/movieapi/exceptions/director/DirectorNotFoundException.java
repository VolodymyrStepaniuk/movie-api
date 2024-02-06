package com.stepaniuk.movieapi.exceptions.director;

import lombok.Getter;

@Getter
public class DirectorNotFoundException extends RuntimeException{
  private final Long id;
  public DirectorNotFoundException(Long id) {
    super("No director found with id: " + id);
    this.id = id;
  }
}
