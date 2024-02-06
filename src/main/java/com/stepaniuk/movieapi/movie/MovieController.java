package com.stepaniuk.movieapi.movie;

import com.stepaniuk.movieapi.movie.payload.request.CreateMovieRequest;
import com.stepaniuk.movieapi.movie.payload.response.MovieResponse;
import java.util.List;
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
@RequestMapping(path = "/movie", produces = "application/json")
@RequiredArgsConstructor
public class MovieController {

  private final MovieService service;

  @GetMapping
  public ResponseEntity<Page<MovieResponse>> getAllMovies(Pageable pageable,
      @Nullable @RequestParam(required = false) String title,
      @Nullable @RequestParam(required = false) List<Long> genreIds) {
    return ResponseEntity.ok(service.getAllMovies(pageable, title, genreIds));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
    return ResponseEntity.ok(service.getMovieById(id));
  }

  @PostMapping
  public ResponseEntity<MovieResponse> createMovie(@RequestBody CreateMovieRequest request) {
    var movie = service.createMovie(request);
    return new ResponseEntity<>(movie, HttpStatus.CREATED);
  }
}
