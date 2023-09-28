package com.stepaniuk.movieapi.movie;

import com.stepaniuk.movieapi.exceptions.MovieNotFoundException;
import com.stepaniuk.movieapi.movie.payload.request.CreateMovieRequest;
import com.stepaniuk.movieapi.movie.payload.response.MovieListResponse;
import com.stepaniuk.movieapi.movie.payload.response.MovieResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService  {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieResponse create(CreateMovieRequest createMovieRequest) {
        Movie movie = new Movie();

        movie.setTitle(createMovieRequest.getTitle());
        movie.setYear(createMovieRequest.getYear());
        movie.setProduction(createMovieRequest.getProduction());
        movie.setGenres(createMovieRequest.getGenres());
        movie.setDirector(createMovieRequest.getDirector());
        movie.setDuration(createMovieRequest.getDuration());
        movie.setLanguage(createMovieRequest.getLanguage());
        movie.setDescription(createMovieRequest.getDescription());

        movieRepository.save(movie);

        return movieMapper.toResponse(movie);
    }


    public MovieListResponse getAll() {
        var listOfMovieResponse = movieRepository.findAll().stream().map(movieMapper::toResponse).toList();
        return new MovieListResponse(listOfMovieResponse,listOfMovieResponse.size());
    }
    public Page<MovieResponse> getAll(Pageable pageable) {
        return movieRepository.findAll(pageable).map(movieMapper::toResponse);
    }

    public MovieResponse getByIdToResponse(Long id) {
        return movieMapper.toResponse(getById(id));
    }

    private Movie getById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(MovieNotFoundException::new);
    }
    public void uploadMovieImage(Long movieId, byte[] image) {
        Movie movie = getById(movieId);
        movie.setImage(image);
        movieRepository.save(movie);
    }

    public byte[] getMovieImage(Long movieId) {
        Movie movie = getById(movieId);
        byte[] image = movie.getImage();
        if(image.length==0){
            throw new MovieNotFoundException();
        }
        return image;
    }

    public Page<MovieResponse> findByTitleContaining(String title, Pageable pageable){
        Page<Movie> movie = movieRepository.findByTitleContaining(title, pageable);
        if(movie.isEmpty()){
            throw new MovieNotFoundException("No movies with title "+title+" found.");
        }
        return movie.map(movieMapper::toResponse);
    }
    public Page<MovieResponse> findByGenre(Genre genre, Pageable pageable){
        Page<Movie> movie = movieRepository.findByGenre(genre, pageable);
        if(movie.isEmpty()){
            throw new MovieNotFoundException("No movies with genre "+genre+" found.");
        }
        return movie.map(movieMapper::toResponse);
    }
}
