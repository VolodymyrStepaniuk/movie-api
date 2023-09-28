package com.stepaniuk.movieapi.movie.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieListResponse {
    @JsonProperty("content")
    List<MovieResponse> content;
    @JsonProperty("size")
    int size;
}