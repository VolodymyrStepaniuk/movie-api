package com.stepaniuk.movieapi.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ControllerInterface<T> {
    @GetMapping("/all")
    List<T> getAll();

    @GetMapping("/{id}")
    T getById(@PathVariable("id") Long id);
    @PostMapping
    void add(@RequestBody T body);

    @PutMapping("/{id}")
    ResponseEntity.HeadersBuilder<?> changeById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity.HeadersBuilder<?> deleteById(@PathVariable Long id);
}
