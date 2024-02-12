package com.stepaniuk.movieapi.country.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CountryResponse {

  @NotNull
  private Long id;
  @NotNull
  private String name;
  @NotNull
  private String imageUrl;
}

