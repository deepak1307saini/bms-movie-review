package com.example.moviereviewSpringBootApplicaion.dto;


import com.example.moviereviewSpringBootApplicaion.enums.CertificateType;
import com.example.moviereviewSpringBootApplicaion.enums.Genre;
import com.example.moviereviewSpringBootApplicaion.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class MovieDto {


    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Language language;

    @NotBlank
    private CertificateType certificateType;

    @NotBlank
    private Date releaseDate;

    @NotBlank
    private Genre genre;

    @NotEmpty
    private List<String> actorNames;

}
