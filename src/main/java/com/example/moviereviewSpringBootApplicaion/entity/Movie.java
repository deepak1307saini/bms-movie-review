package com.example.moviereviewSpringBootApplicaion.entity;

import com.example.moviereviewSpringBootApplicaion.enums.CertificateType;
import com.example.moviereviewSpringBootApplicaion.enums.Genre;
import com.example.moviereviewSpringBootApplicaion.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "movies")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Movie {

    @MongoId(FieldType.OBJECT_ID)
    private String _id;

    private long movieId;
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "release_date", columnDefinition = "DATE", nullable = false)
    private Date releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CertificateType certificateType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    private List<String> actors = new ArrayList<>();

}