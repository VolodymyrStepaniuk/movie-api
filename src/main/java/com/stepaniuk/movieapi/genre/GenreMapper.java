package com.stepaniuk.movieapi.genre;

import com.stepaniuk.movieapi.genre.payload.response.GenreResponse;
import com.stepaniuk.movieapi.shared.InstantMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
    InstantMapper.class})
public interface GenreMapper {

  GenreResponse toResponse(Genre genre);
}
