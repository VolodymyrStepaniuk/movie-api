package com.stepaniuk.movieapi.genre.payload.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GenreResponse {

  @NotNull
  private Long id;
  @NotNull
  private String genreName;
}
