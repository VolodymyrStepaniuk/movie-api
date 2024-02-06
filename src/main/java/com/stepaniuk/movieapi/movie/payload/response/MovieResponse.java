package com.stepaniuk.movieapi.movie.payload.response;

import com.stepaniuk.movieapi.country.payload.response.CountryResponse;
import com.stepaniuk.movieapi.director.payload.response.DirectorResponse;
import com.stepaniuk.movieapi.genre.payload.response.GenreResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MovieResponse {
    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Integer requiredAge;
    @NotNull
    private Integer year;
    @NotNull
    @Size(min = 1)
    private Set<CountryResponse> producingCountries;
    @NotNull
    @Size(min = 1)
    private Set<GenreResponse> genres;
    @NotNull
    @Size(min = 1)
    private Set<DirectorResponse> directors;
    @NotNull
    private Duration duration;
    @NotNull
    private String language;
    @NotNull
    private String description;
    private String trailerUrl;
    private String videoUrl;
    @NotNull
    private List<String> imageUrls;
}
