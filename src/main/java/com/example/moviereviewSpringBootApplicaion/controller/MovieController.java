package com.example.moviereviewSpringBootApplicaion.controller;

import com.example.moviereviewSpringBootApplicaion.dto.MovieDto;
import com.example.moviereviewSpringBootApplicaion.dto.MovieResponseDto;
import com.example.moviereviewSpringBootApplicaion.dto.ResponseDto;
import com.example.moviereviewSpringBootApplicaion.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody MovieDto movieDto) {
        MovieResponseDto movieResponseDto= movieService.addMovie(movieDto);
        return new ResponseEntity<>(movieResponseDto, HttpStatus.OK);
    }


    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovie(@PathVariable Long movieId) {
        MovieResponseDto movieResponseDto= movieService.getMovie(movieId);
        return new ResponseEntity<>(movieResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getMovies() {
       List< MovieResponseDto> movieResponseDtos = movieService.getMovies();
        return new ResponseEntity<>(movieResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long movieId) {
        ResponseDto responseDto= movieService.deleteMovie(movieId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<?> updateMovie(@PathVariable long movieId,@Valid @RequestBody MovieDto movieDto) {

        ResponseDto responseDto= movieService.updateMovie(movieId, movieDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
