package com.stepaniuk.movieapi.movie;

import com.stepaniuk.movieapi.movie.payload.response.MovieResponse;
import com.stepaniuk.movieapi.shared.InstantMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {InstantMapper.class})
public interface MovieMapper {

    MovieResponse toResponse(Movie movie);
}