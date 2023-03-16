package com.example.moviereviewSpringBootApplicaion.repository;

import com.example.moviereviewSpringBootApplicaion.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review,String> {
    List<Review> findByMovieId(long movieId);
    Optional<Review> findByMovieIdAndUserId(long movieId, long userId);

    boolean existsByMovieIdAndUserId(long movieId, long userId);
}
