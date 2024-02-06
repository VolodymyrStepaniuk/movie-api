package com.stepaniuk.movieapi.movie;

import com.stepaniuk.movieapi.director.DirectorRepository;
import com.stepaniuk.movieapi.exceptions.movie.MovieNotFoundException;
import com.stepaniuk.movieapi.country.CountryRepository;
import com.stepaniuk.movieapi.genre.GenreRepository;
import com.stepaniuk.movieapi.movie.payload.request.CreateMovieRequest;
import com.stepaniuk.movieapi.movie.payload.response.MovieResponse;
import java.util.HashSet;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieService {

  private final MovieRepository movieRepository;
  private final MovieMapper movieMapper;
  private final DirectorRepository directorRepository;
  private final GenreRepository genreRepository;
  private final CountryRepository countryRepository;

  public MovieResponse createMovie(CreateMovieRequest createMovieRequest) {
    Movie movie = new Movie();

    var directorIds = createMovieRequest.getDirectorIds();
    var directors = directorRepository.findAllById(directorIds);

    var genreIds = createMovieRequest.getGenreIds();
    var genres = genreRepository.findAllById(genreIds);

    var countryIds = createMovieRequest.getProducingCountryIds();
    var countries = countryRepository.findAllById(countryIds);

    movie.setTitle(createMovieRequest.getTitle());
    movie.setRequiredAge(createMovieRequest.getRequiredAge());
    movie.setYear(createMovieRequest.getYear());
    movie.setProducingCountries(new HashSet<>(countries));
    movie.setGenres(new HashSet<>(genres));
    movie.setDirectors(new HashSet<>(directors));
    movie.setDuration(createMovieRequest.getDuration());
    movie.setLanguage(createMovieRequest.getLanguage());
    movie.setDescription(createMovieRequest.getDescription());
    movie.setImageUrls(createMovieRequest.getImageUrls());

    var savedMovie = movieRepository.save(movie);

    return movieMapper.toResponse(savedMovie);
  }

  public Page<MovieResponse> getAllMovies(Pageable pageable, @Nullable String title,
      @Nullable List<Long> genreIds) {

    Specification<Movie> specification = Specification.where(null);
    if (title != null && !title.isEmpty()) {
      specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder
          .like(root.get("title"), title + "%")
      );
    }

    if (genreIds != null && !genreIds.isEmpty()) {
      for (Long genreId : genreIds) {
        Specification<Movie> genreSpecification = (root, query, criteriaBuilder) ->
            root.join("genres").get("id").in(genreId);
        specification = specification.and(genreSpecification);
      }
    }

    return movieRepository.findAll(specification, pageable).map(movieMapper::toResponse);
  }

  public MovieResponse getMovieById(Long id) {
    return movieMapper.toResponse(movieRepository.findById(id)
        .orElseThrow(() -> new MovieNotFoundException(id)));
  }
}
