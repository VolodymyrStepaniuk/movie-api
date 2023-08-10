package com.stepaniuk.movieapi.repositories;

import com.stepaniuk.movieapi.entitys.Genre;
import com.stepaniuk.movieapi.entitys.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    Page<Movie> findByTitleContaining(String title, Pageable pageable);
    @Query("SELECT m FROM movies m WHERE :genre MEMBER OF m.genres")
    Page<Movie> findByGenre(@Param("genre") Genre genre, Pageable pageable);
}
