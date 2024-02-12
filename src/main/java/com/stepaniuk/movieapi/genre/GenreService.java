package com.stepaniuk.movieapi.genre;

import com.stepaniuk.movieapi.exceptions.genre.GenreNotFoundException;
import com.stepaniuk.movieapi.genre.payload.GenreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {

  private final GenreRepository genreRepository;
  private final GenreMapper genreMapper;

  public Page<GenreResponse> getAllGenres(Pageable pageable) {
    return genreRepository.findAll(pageable).map(genreMapper::toResponse);
  }

  public GenreResponse getGenreById(Long id) {
    return genreMapper.toResponse(
        genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException(id)));
  }
}
