package com.stepaniuk.movieapi.exceptions;

import com.stepaniuk.movieapi.exceptions.country.CountryNotFoundException;
import com.stepaniuk.movieapi.exceptions.director.DirectorNotFoundException;
import com.stepaniuk.movieapi.exceptions.genre.GenreNotFoundException;
import com.stepaniuk.movieapi.exceptions.movie.MovieNotFoundException;
import com.stepaniuk.movieapi.exceptions.review.ReviewNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultExceptionHandler {

  @ExceptionHandler({CountryNotFoundException.class, DirectorNotFoundException.class,
      GenreNotFoundException.class, MovieNotFoundException.class, ReviewNotFoundException.class})
  public ResponseEntity<ApiError> handleException(RuntimeException e, HttpServletRequest request) {
    ApiError apiError = new ApiError(
        request.getRequestURI(),
        e.getMessage(),
        HttpStatus.NOT_FOUND.value(),
        LocalDateTime.now()
    );

    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }
}
