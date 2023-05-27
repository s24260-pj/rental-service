package com.example.demo.movie.controller;


import com.example.demo.movie.model.Movie;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rental")
public class RentalController {
    String movieResourceUrl = "http://localhost:8080/movies";
    private final RestTemplate restTemplate;

    public RentalController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(restTemplate.getForEntity(movieResourceUrl + "/" + id, Movie.class).getBody());
    }

    @PutMapping("/return_movie/{id}")
    public ResponseEntity<Movie> returnMovie(@PathVariable(value = "id") Long id) {
        HttpEntity<Boolean> request = new HttpEntity<Boolean>(true);
        return ResponseEntity.ok(restTemplate.exchange(movieResourceUrl + "/update_is_available/" + id, HttpMethod.PUT, request, Movie.class).getBody());
    }

    @PutMapping("/rent_movie/{id}")
    public ResponseEntity<Movie> rentMovie(@PathVariable(value = "id") Long id) {
        HttpEntity<Boolean> request = new HttpEntity<Boolean>(false);
        return ResponseEntity.ok(restTemplate.exchange(movieResourceUrl + "/update_is_available/" + id, HttpMethod.PUT, request, Movie.class).getBody());
    }
}
