package com.stepaniuk.movieapi.movie;

import com.stepaniuk.movieapi.country.CountryMapper;
import com.stepaniuk.movieapi.director.DirectorMapper;
import com.stepaniuk.movieapi.genre.GenreMapper;
import com.stepaniuk.movieapi.movie.payload.MovieResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
    CountryMapper.class, DirectorMapper.class, GenreMapper.class})
public interface MovieMapper {

  MovieResponse toResponse(Movie movie);
}
