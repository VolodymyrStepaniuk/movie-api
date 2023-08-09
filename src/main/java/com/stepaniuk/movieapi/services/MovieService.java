package com.stepaniuk.movieapi.services;

import com.stepaniuk.movieapi.entitys.Movie;
import com.stepaniuk.movieapi.exceptions.MovieNotFoundException;
import com.stepaniuk.movieapi.interfaces.ServiceInterface;
import com.stepaniuk.movieapi.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService implements ServiceInterface<Movie> {
    private final MovieRepository repository;
    @Override
    public void save(Movie movie) {
        repository.save(movie);
    }

    @Override
    public List<Movie> getAll() {
        return repository.findAll();
    }
    public Page<Movie> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void delete(Movie movie) {
        repository.delete(movie);
    }

    @Override
    public void update(Movie movie) {
        //TODO: add some functional
    }

    @Override
    public Movie getById(Long id) {
        return repository.findById(id).orElseThrow(MovieNotFoundException::new);
    }
    public void uploadMovieImage(Long movieId, byte[] image) {
        Movie movie = getById(movieId);
        movie.setImage(image);
        repository.save(movie);
    }

    public byte[] getMovieImage(Long movieId) {
        Movie movie = getById(movieId);
        return movie.getImage();
    }
}
