package com.example.moviereviewSpringBootApplicaion.dto;

import com.example.moviereviewSpringBootApplicaion.entity.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private long movieId;
    private long userId;
    private String comment;
    @Max(10)
    private Integer movieRating;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateTime;
}
