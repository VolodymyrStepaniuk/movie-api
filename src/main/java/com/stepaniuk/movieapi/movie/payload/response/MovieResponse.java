package com.stepaniuk.movieapi.movie.payload.response;

import com.stepaniuk.movieapi.movie.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class MovieResponse {
    private Long id;
    private String title;
    private Integer year;
    private String production;
    private List<Genre> genres;
    private String director;
    private Double duration;
    private String language;
    private String description;
}
