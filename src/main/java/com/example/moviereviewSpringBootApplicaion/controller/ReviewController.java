package com.example.moviereviewSpringBootApplicaion.controller;

import com.example.moviereviewSpringBootApplicaion.dto.ResponseDto;
import com.example.moviereviewSpringBootApplicaion.dto.ReviewDto;
import com.example.moviereviewSpringBootApplicaion.dto.ReviewResponseDto;
import com.example.moviereviewSpringBootApplicaion.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies/{movieId}/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping("/")
    public ResponseDto AddReview(@PathVariable long movieId,@RequestBody ReviewDto reviewDto){
        return reviewService.addReview(movieId, reviewDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllReviews(@PathVariable long movieId) {
        List<ReviewResponseDto> reviewResponseDtoS= reviewService.getAllReviews(movieId);
        return new ResponseEntity<>(reviewResponseDtoS, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getReview(@PathVariable long movieId, @PathVariable long userId) {
        ReviewResponseDto reviewResponseDto = reviewService.getReview(movieId, userId);
        return new ResponseEntity<>(reviewResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addReview(@PathVariable long movieId, @Valid @RequestBody ReviewDto reviewDto) {
        ResponseDto responseDto = reviewService.addReview(movieId, reviewDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> editReview(@PathVariable long movieId, @Valid @RequestBody ReviewDto reviewDto) {
        ResponseDto responseDto = reviewService.editReview(movieId, reviewDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReview(@PathVariable long movieId, @RequestBody ReviewDto reviewDto) {
        ResponseDto responseDto = reviewService.deleteReview(movieId, reviewDto.getUserId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
