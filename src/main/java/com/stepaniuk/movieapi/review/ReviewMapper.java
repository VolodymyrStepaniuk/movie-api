package com.stepaniuk.movieapi.review;

import com.stepaniuk.movieapi.review.payload.ReviewResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReviewMapper {
  ReviewResponse toResponse(Review review);
}
