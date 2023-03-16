
package com.example.moviereviewSpringBootApplicaion.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document(collection = "reviews")
public class Review {
    private long movieId;
    private long userId;
    private String comment;
    @Max(10)
    private Integer movieRating;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateTime;
}