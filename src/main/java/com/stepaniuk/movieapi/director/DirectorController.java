package com.stepaniuk.movieapi.director;

import com.stepaniuk.movieapi.director.payload.request.CreateDirectorRequest;
import com.stepaniuk.movieapi.director.payload.response.DirectorResponse;
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
@RequestMapping(path = "/director", produces = "application/json")
@RequiredArgsConstructor
public class DirectorController {

  private final DirectorService service;

  @GetMapping
  public ResponseEntity<Page<DirectorResponse>> getAllDirectors(Pageable pageable,
      @Nullable @RequestParam(required = false) List<Long> directorIds) {
    return ResponseEntity.ok(service.getAllDirectors(pageable, directorIds));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DirectorResponse> getDirectorById(@PathVariable Long id) {
    return ResponseEntity.ok(service.getDirectorById(id));
  }

  @PostMapping
  public ResponseEntity<DirectorResponse> createDirector(
      @RequestBody CreateDirectorRequest request) {
    return new ResponseEntity<>(service.createDirector(request), HttpStatus.CREATED);
  }
}
