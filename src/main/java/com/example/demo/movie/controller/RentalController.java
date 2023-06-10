package com.example.demo.movie.controller;


import com.example.demo.movie.model.Movie;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Movie.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Gateway",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found movie.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "502",
                    description = "Bad Gateway.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "504",
                    description = "Gateway timeout.",
                    content = @Content
            )
    })
    @Operation(summary = "Get movie by id")
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(restTemplate.getForEntity(movieResourceUrl + "/" + id, Movie.class).getBody());
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Movie.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Gateway",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found movie.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "502",
                    description = "Bad Gateway.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "504",
                    description = "Gateway timeout.",
                    content = @Content
            )
    })
    @Operation(summary = "Return movie by id")
    @PutMapping("/return_movie/{id}")
    public ResponseEntity<Movie> returnMovie(@PathVariable(value = "id") Long id) {
        HttpEntity<Boolean> request = new HttpEntity<Boolean>(true);
        return ResponseEntity.ok(restTemplate.exchange(movieResourceUrl + "/update_is_available/" + id, HttpMethod.PUT, request, Movie.class).getBody());
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Movie.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Gateway",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found movie.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "502",
                    description = "Bad Gateway.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "504",
                    description = "Gateway timeout.",
                    content = @Content
            )
    })
    @Operation(summary = "Rent movie by id")
    @PutMapping("/rent_movie/{id}")
    public ResponseEntity<Movie> rentMovie(@PathVariable(value = "id") Long id) {
        HttpEntity<Boolean> request = new HttpEntity<Boolean>(false);
        return ResponseEntity.ok(restTemplate.exchange(movieResourceUrl + "/update_is_available/" + id, HttpMethod.PUT, request, Movie.class).getBody());
    }
}
