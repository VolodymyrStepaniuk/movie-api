package com.stepaniuk.movieapi.country;

import com.stepaniuk.movieapi.country.payload.response.CountryResponse;
import com.stepaniuk.movieapi.shared.InstantMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
    InstantMapper.class})
public interface CountryMapper {

  CountryResponse toResponse(Country country);
}
