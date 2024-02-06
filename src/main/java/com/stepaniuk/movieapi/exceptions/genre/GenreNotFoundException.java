package com.stepaniuk.movieapi.exceptions.genre;

import lombok.Getter;

@Getter
public class GenreNotFoundException extends RuntimeException {

  private final Long id;

  public GenreNotFoundException(Long id) {
    super("No genre found with id: " + id);
    this.id = id;
  }
}
