package com.stepaniuk.movieapi.exceptions.country;

import lombok.Getter;

@Getter
public class CountryNotFoundException extends RuntimeException{
  private final Long id;
  public CountryNotFoundException(Long id) {
    super("No country found with id: " + id);
    this.id = id;
  }
}
