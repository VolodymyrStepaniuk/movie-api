package com.stepaniuk.movieapi.movie.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Duration;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateMovieRequest {
    @NotNull
    private String title;
    @NotNull
    private Integer requiredAge;
    @NotNull
    private Integer year;
    @NotNull
    @Size(min = 1)
    private List<Long> producingCountryIds;
    @NotNull
    @Size(min = 1)
    private List<Long> genreIds;
    @NotNull
    @Size(min = 1)
    private List<Long> directorIds;
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
