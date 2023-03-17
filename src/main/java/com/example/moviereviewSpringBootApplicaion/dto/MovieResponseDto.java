package com.example.moviereviewSpringBootApplicaion.dto;

import com.example.moviereviewSpringBootApplicaion.entity.Movie;
import com.example.moviereviewSpringBootApplicaion.enums.CertificateType;
import com.example.moviereviewSpringBootApplicaion.enums.Genre;
import com.example.moviereviewSpringBootApplicaion.enums.Language;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MovieResponseDto {
    private Long movieId;
    private String name;
    private String description;
    private Language language;
    private Date releaseDate;
    private CertificateType certificateType;
    private Genre genre;
    private List<String> actors = new ArrayList<>();

    public MovieResponseDto(Movie movie) {
        this.movieId = movie.getMovieId();
        this.name = movie.getName();
        this.description = movie.getDescription();
        this.language = movie.getLanguage();
        this.releaseDate = movie.getReleaseDate();
        this.certificateType = movie.getCertificateType();
        this.genre = movie.getGenre();
        this.actors = movie.getActors();
    }
}
