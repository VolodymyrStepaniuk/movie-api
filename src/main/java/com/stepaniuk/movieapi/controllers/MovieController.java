package com.stepaniuk.movieapi.controllers;

import com.stepaniuk.movieapi.entitys.Genre;
import com.stepaniuk.movieapi.entitys.Movie;
import com.stepaniuk.movieapi.interfaces.ControllerInterface;
import com.stepaniuk.movieapi.services.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/movie")
@RequiredArgsConstructor
public class MovieController implements ControllerInterface<Movie> {
    private final MovieService service;

    @Override
    public List<Movie> getAll() {
        return service.getAll();
    }

    @Override
    public Movie getById(Long id) {
        return service.getById(id);
    }

    @Override
    public void add(Movie body) {
        service.save(body);
    }

    public ResponseEntity.HeadersBuilder<?> changeById(Long id){
        service.update(service.getById(id));
        return ResponseEntity.ok();
    }
    public ResponseEntity.HeadersBuilder<?> deleteById(Long id){
        service.delete(service.getById(id));
        return ResponseEntity.ok();
    }

    @SneakyThrows
    @PostMapping("/{movieId}/upload-image")
    public ResponseEntity<String> uploadImage(@PathVariable Long movieId, @RequestParam("image") MultipartFile image) {
        // Обробка завантаження зображення та збереження його у полі "image" об'єкта Movie
        service.uploadMovieImage(movieId, image.getBytes());
        return ResponseEntity.ok("Image uploaded successfully.");
    }

    @GetMapping("/{movieId}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long movieId) {
        byte[] imageBytes = service.getMovieImage(movieId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }
    @GetMapping
    public ResponseEntity<Page<Movie>> getAllByPagination(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviesPage = service.getAll(pageable);
        return ResponseEntity.ok(moviesPage);
    }
    @GetMapping("/title")
    public ResponseEntity<Page<Movie>> getByTitleContaining(@RequestParam("title") String title, @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviesPage = service.findByTitleContaining(title,pageable);
        return ResponseEntity.ok(moviesPage);
    }
    @GetMapping("/genre")
    public ResponseEntity<Page<Movie>> getByGenres(@RequestParam("genre") Genre genre, @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviesPage = service.findByGenres(genre,pageable);
        return ResponseEntity.ok(moviesPage);
    }
}
