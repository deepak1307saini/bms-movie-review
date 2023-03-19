package com.example.moviereviewSpringBootApplicaion.service;

import com.example.moviereviewSpringBootApplicaion.dto.ResponseDto;
import com.example.moviereviewSpringBootApplicaion.dto.ReviewDto;
import com.example.moviereviewSpringBootApplicaion.dto.ReviewResponseDto;
import com.example.moviereviewSpringBootApplicaion.entity.Review;
import com.example.moviereviewSpringBootApplicaion.helper.ReviewHelper;
import com.example.moviereviewSpringBootApplicaion.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewHelper reviewHelper;

    @Override
    public List<ReviewResponseDto> getAllReviews(long movieId) {
        reviewHelper.movieCheck(movieId);
        return reviewRepository.findByMovieId(movieId)
                .stream()
                .map(ReviewResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponseDto getReview(long movieId, long userId) {
        reviewHelper.movieCheck(movieId);
        Review review = reviewHelper.getReview(movieId, userId);
        return new ReviewResponseDto(review);
    }

    @Override
    public ResponseDto addReview(long movieId, ReviewDto reviewDto) {
        reviewHelper.movieCheck(movieId);
        reviewHelper.canAdd(movieId, reviewDto.getUserId());
        Review review = reviewHelper.toEntity(reviewDto, movieId);
        review.setDateTime(new Date());
        reviewRepository.save(review);

        return new ResponseDto(true, String.format("review for movie %d added successfully", reviewDto.getMovieId()));
    }

    @Override
    public ResponseDto editReview(long movieId, ReviewDto reviewDto) {
        reviewHelper.movieCheck(movieId);
        reviewHelper.canEdit(movieId, reviewDto.getUserId());

        Review review = reviewHelper.getReview(movieId, reviewDto.getUserId());
        review.setComment(reviewDto.getComment());
        reviewRepository.save(review);

        return new ResponseDto(true, String.format("review for movie %d updated successfully", review.getMovieId()));
    }

    @Override
    public ResponseDto deleteReview(long movieId, long userId) {
        reviewHelper.movieCheck(movieId);
        reviewHelper.canDelete(movieId, userId);
        Review review = reviewHelper.getReview(movieId, userId);
        reviewRepository.delete(review);
        return new ResponseDto(true, String.format("review for movie Id %d deleted successfully", review.getMovieId()));
    }
}
