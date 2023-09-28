package com.stepaniuk.movieapi.movie;

import com.stepaniuk.movieapi.movie.payload.request.CreateMovieRequest;
import com.stepaniuk.movieapi.movie.payload.response.MovieListResponse;
import com.stepaniuk.movieapi.movie.payload.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/movie")
@RequiredArgsConstructor
public class MovieController{
    private final MovieService service;
    @GetMapping("/all")
    public ResponseEntity<MovieListResponse> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getByIdToResponse(id));
    }
    @PostMapping
    public ResponseEntity<MovieResponse> create(@RequestBody CreateMovieRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }
//    @PutMapping("/{id}")
//    public ResponseEntity.BodyBuilder changeById(@PathVariable Long id){
//        service.update(service.getById(id));
//        return ResponseEntity.ok();
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity.HeadersBuilder<?> deleteById(@PathVariable Long id){
//        service.delete(service.getById(id));
//        return ResponseEntity.ok();
//    }

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
    public ResponseEntity<Page<MovieResponse>> getAllByPagination(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }
    @GetMapping("/title")
    public ResponseEntity<Page<MovieResponse>> getByTitleContaining(Pageable pageable,@RequestParam("title") String title){
        return ResponseEntity.ok(service.findByTitleContaining(title,pageable));
    }
    @GetMapping("/genre")
    public ResponseEntity<Page<MovieResponse>> getByGenres(Pageable pageable,@RequestParam("genre") Genre genre){
        return ResponseEntity.ok(service.findByGenre(genre,pageable));
    }
}
