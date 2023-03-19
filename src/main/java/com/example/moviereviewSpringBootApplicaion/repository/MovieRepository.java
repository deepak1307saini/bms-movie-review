package com.example.moviereviewSpringBootApplicaion.repository;

import com.example.moviereviewSpringBootApplicaion.entity.Movie;
import com.example.moviereviewSpringBootApplicaion.enums.Language;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie, Long> {
    boolean existsByNameAndLanguageAndMovieId(String name, Language language, long movieId);

    boolean existsByMovieId(long movieId);

    Optional<Movie> findByMovieId(long movieId);
}
