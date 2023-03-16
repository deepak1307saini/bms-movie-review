package com.example.moviereviewSpringBootApplicaion.service;

import com.example.moviereviewSpringBootApplicaion.dto.ResponseDto;
import com.example.moviereviewSpringBootApplicaion.dto.ReviewDto;
import com.example.moviereviewSpringBootApplicaion.dto.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    List<ReviewResponseDto> getAllReviews(long movieId);

    ReviewResponseDto getReview(long movieId, long userId);

    ResponseDto addReview(long movieId, ReviewDto reviewDto);

    ResponseDto editReview(long movieId, ReviewDto reviewDto);

    ResponseDto deleteReview(long movieId, long userId);
}
