package com.stepaniuk.movieapi.review;

import com.stepaniuk.movieapi.review.payload.request.CreateReviewRequest;
import com.stepaniuk.movieapi.review.payload.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/review", produces = "application/json")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService service;

  @PostMapping
  public ResponseEntity<ReviewResponse> createReview(@RequestBody CreateReviewRequest request) {
    var review = service.createReview(request);
    return new ResponseEntity<>(review, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Long id) {
    return ResponseEntity.ok(service.getReviewById(id));
  }

  @GetMapping
  public ResponseEntity<Page<ReviewResponse>> getAllReviews(Pageable pageable,
      @Nullable @RequestParam(required = false) Long movieId) {
    return ResponseEntity.ok(service.getAllReviews(pageable, movieId));
  }
}
