package com.stepaniuk.movieapi.repositories;

import com.stepaniuk.movieapi.entitys.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    Page<Movie> findByTitleContaining(String title, Pageable pageable);
}
