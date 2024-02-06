package com.stepaniuk.movieapi.review.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateReviewRequest {
  @NotNull
  private Long movieId;
  private Long userId;
  @NotNull
  private Double rating;
  private String comment;
}
