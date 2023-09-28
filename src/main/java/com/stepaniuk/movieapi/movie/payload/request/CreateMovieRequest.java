package com.stepaniuk.movieapi.movie.payload.request;

import com.stepaniuk.movieapi.movie.Genre;
import jakarta.validation.constraints.NotNull;
import lombok.Value;


import java.util.List;
@Value
public class CreateMovieRequest {
    @NotNull String title;
    int year;
    String production;
    List<Genre> genres;
    @NotNull String director;
    double duration;
    String language;
    String description;
}
