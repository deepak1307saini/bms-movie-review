package com.example.moviereviewSpringBootApplicaion.helper;

import com.example.moviereviewSpringBootApplicaion.dto.MovieDto;
import com.example.moviereviewSpringBootApplicaion.dto.MovieResponseDto;
import com.example.moviereviewSpringBootApplicaion.entity.Movie;
import com.example.moviereviewSpringBootApplicaion.exception.DuplicateRecordException;
import com.example.moviereviewSpringBootApplicaion.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieHelper {

    @Autowired
    MovieRepository movieRepository;

    public void mapMovieRequestToMovie(MovieDto movieDto, Movie movie) {
        movie.setMovieId(movieDto.getId());
        movie.setName(movieDto.getName());
        movie.setDescription(movieDto.getDescription());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setLanguage(movieDto.getLanguage());
        movie.setGenre(movieDto.getGenre());
        movie.setCertificateType(movieDto.getCertificateType());
        movie.setActors(movieDto.getActorNames());
    }


    public void canAdd(MovieDto movieDto) {
        if (movieRepository.existsByNameAndLanguageAndMovieId(movieDto.getName(), movieDto.getLanguage(), movieDto.getId())) {
            throw new DuplicateRecordException(String.format("Movie Already Exists with Name: " + movieDto.getName() + " in Language: " + movieDto.getLanguage()));
        }
    }

    public void checkMovie(Long id) {
        if (!movieRepository.existsByMovieId(id))
            throw new EntityNotFoundException("invalid movie id");
    }

    public Movie getMovie(Long movieId) {
        return movieRepository.findByMovieId(movieId).get();
    }


    public void canUpdate(Long movieId) {
        checkMovie(movieId);
    }


    public void canDelete(Long movieId) {
        checkMovie(movieId);
    }

    public List<MovieResponseDto> moviesToMovieDto(List<Movie> movies) {
        return movies
                .stream()
                .map(movie -> new MovieResponseDto(movie))
                .collect(Collectors.toList());
    }
}
