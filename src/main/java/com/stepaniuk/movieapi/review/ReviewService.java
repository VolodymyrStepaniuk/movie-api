package com.stepaniuk.movieapi.review;

import com.stepaniuk.movieapi.exceptions.review.ReviewNotFoundException;
import com.stepaniuk.movieapi.review.payload.CreateReviewRequest;
import com.stepaniuk.movieapi.review.payload.ReviewResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final ReviewMapper reviewMapper;

  public ReviewResponse createReview(CreateReviewRequest createReviewRequest) {
    Review review = new Review();
    review.setMovieId(createReviewRequest.getMovieId());
    review.setUserId(createReviewRequest.getUserId());
    review.setRating(createReviewRequest.getRating());
    review.setComment(createReviewRequest.getComment());

    var savedReview = reviewRepository.save(review);
    return reviewMapper.toResponse(savedReview);
  }

  public ReviewResponse getReviewById(Long id) {
    var review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    return reviewMapper.toResponse(review);
  }

  public Page<ReviewResponse> getAllReviews(Pageable pageable, @Nullable Long movieId) {
    Specification<Review> specification = Specification.where(null);
    if (movieId != null) {
      specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder
          .equal(root.get("movieId"), movieId));
    }
    return reviewRepository.findAll(specification, pageable).map(reviewMapper::toResponse);
  }
}
