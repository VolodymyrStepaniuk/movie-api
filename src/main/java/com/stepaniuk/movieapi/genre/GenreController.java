package com.stepaniuk.movieapi.genre;

import com.stepaniuk.movieapi.genre.payload.GenreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/genre", produces = "application/json")
@RequiredArgsConstructor
public class GenreController {
  private final GenreService genreService;

  @GetMapping
  public ResponseEntity<Page<GenreResponse>> getAllGenres(Pageable pageable){
    return ResponseEntity.ok(genreService.getAllGenres(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<GenreResponse> getGenreById(@PathVariable Long id){
    return ResponseEntity.ok(genreService.getGenreById(id));
  }
}
