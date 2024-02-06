package com.stepaniuk.movieapi.review.payload.response;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ReviewResponse {
  @NotNull
  private Long id;
  @NotNull
  private Long movieId;
  private Long userId;
  @NotNull
  private Double rating;
  private String comment;
  @NotNull
  private Instant createdAt;
  @NotNull
  private Instant lastModifiedAt;
}
