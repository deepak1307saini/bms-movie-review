package com.example.moviereviewSpringBootApplicaion.service;

import com.example.moviereviewSpringBootApplicaion.dto.MovieDto;
import com.example.moviereviewSpringBootApplicaion.dto.MovieResponseDto;
import com.example.moviereviewSpringBootApplicaion.dto.ResponseDto;
import com.example.moviereviewSpringBootApplicaion.entity.Movie;
import com.example.moviereviewSpringBootApplicaion.helper.MovieHelper;
import com.example.moviereviewSpringBootApplicaion.repository.MovieRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    MovieHelper movieHelper;


    @Override
    public MovieResponseDto addMovie(MovieDto movieDto) {
        movieHelper.canAdd(movieDto);
        Movie movie = new Movie();
        movieHelper.mapMovieRequestToMovie(movieDto, movie);
        movieRepository.save(movie);
        return new MovieResponseDto(movie);
    }

    @Override
    public List<MovieResponseDto> getMovies() {
        List<Movie> movies = new ArrayList<>();

        movieRepository.findAll()
                .forEach(movies::add);

        return movieHelper.moviesToMovieDto(movies);
    }

    @Override
    public MovieResponseDto getMovie(Long movieId) {
        movieHelper.checkMovie(movieId);
        Movie movie = movieHelper.getMovie(movieId);
        return new MovieResponseDto(movie);
    }

    @Override
    public ResponseDto updateMovie(Long movieId, MovieDto movieDto) {
        movieHelper.canUpdate(movieId);
        Movie movie = movieHelper.getMovie(movieId);
        movieHelper.mapMovieRequestToMovie(movieDto, movie);
        movieRepository.save(movie);
        return new ResponseDto(true, String.format("movie %s updated successfully", movie.getName()));
    }

    @Override
    public ResponseDto deleteMovie(Long movieId) {
        movieHelper.canDelete(movieId);
        Movie movie = movieHelper.getMovie(movieId);
        movieRepository.delete(movie);
        return new ResponseDto(true, String.format("movie %s deleted successfully", movie.getName()));
    }
}
