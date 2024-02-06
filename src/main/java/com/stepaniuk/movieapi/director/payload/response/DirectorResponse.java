package com.stepaniuk.movieapi.director.payload.response;

import com.stepaniuk.movieapi.country.payload.response.CountryResponse;
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
public class DirectorResponse {
  @NotNull
  private Long id;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  @NotNull
  private LocalDate birthDate;
  @NotNull
  private CountryResponse birthCountry;
  @NotNull
  private String description;
  @NotNull
  private List<String> photoUrls;
}
