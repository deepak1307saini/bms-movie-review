package com.example.moviereviewSpringBootApplicaion.service;

import com.example.moviereviewSpringBootApplicaion.dto.MovieDto;
import com.example.moviereviewSpringBootApplicaion.dto.MovieResponseDto;
import com.example.moviereviewSpringBootApplicaion.dto.ResponseDto;

import java.util.List;

public interface MovieService {
    MovieResponseDto addMovie(MovieDto movieDto);

    List<MovieResponseDto> getMovies();

    MovieResponseDto getMovie(Long movieId);

    ResponseDto updateMovie(Long movieId, MovieDto movieRequestDTO);

    ResponseDto deleteMovie(Long movieId);
}
