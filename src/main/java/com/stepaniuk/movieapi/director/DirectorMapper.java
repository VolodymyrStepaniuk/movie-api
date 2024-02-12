package com.stepaniuk.movieapi.director;

import com.stepaniuk.movieapi.country.CountryMapper;
import com.stepaniuk.movieapi.director.payload.DirectorResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
    CountryMapper.class})
public interface DirectorMapper {
  DirectorResponse toResponse(Director director);
}
