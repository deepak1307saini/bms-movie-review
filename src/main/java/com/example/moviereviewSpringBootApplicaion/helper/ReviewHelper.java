package com.example.moviereviewSpringBootApplicaion.helper;

import com.example.moviereviewSpringBootApplicaion.dto.ReviewDto;
import com.example.moviereviewSpringBootApplicaion.entity.Review;
import com.example.moviereviewSpringBootApplicaion.exception.DuplicateRecordException;
import com.example.moviereviewSpringBootApplicaion.exception.NotFoundException;
import com.example.moviereviewSpringBootApplicaion.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReviewHelper {

    @Autowired
    ReviewRepository reviewRepository;

    public Review toEntity(ReviewDto reviewDto){
        return Review.builder()
                .movieId(reviewDto.getMovieId())
                .userId(reviewDto.getUserId())
                .movieRating((reviewDto.getMovieRating()))
                .comment(reviewDto.getComment())
                .dateTime(reviewDto.getDateTime())
                .build();
    }

    public void canAdd(long movieId,long userId){
        System.out.println("Deepak "+reviewRepository.existsByMovieIdAndUserId(movieId, userId));
        if (reviewRepository.existsByMovieIdAndUserId(movieId, userId)){
            throw new DuplicateRecordException(String.format("Review already exists for movie id : " + movieId + " by userId " + userId));
        }
    }

    public void canEdit(long userId,long movieId){

        if (! reviewRepository.existsByMovieIdAndUserId(movieId, userId)){
            throw new NotFoundException(String.format("Review not exists fot userId : %d",userId));
        }
    }

    public Review getReview(long userId,long movieId) {
        Optional<Review> review = reviewRepository.findByMovieIdAndUserId(movieId, userId);
        return review.orElseThrow(() -> new NotFoundException("invalid user Id"));
    }

    public void canDelete(long userId,long movieId){

        if (! reviewRepository.existsByMovieIdAndUserId(movieId, userId)){
            throw new NotFoundException(String.format("Review not exists for movie id : %d and userId : %d",movieId,userId));
        }
    }
}
