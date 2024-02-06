package com.stepaniuk.movieapi.director.payload.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateDirectorRequest {

  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  @NotNull
  private LocalDate birthDate;
  @NotNull
  private Long countryId;
  @NotNull
  private String description;
  @NotNull
  private List<String> photoUrls;
}
