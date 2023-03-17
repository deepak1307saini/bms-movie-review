package com.example.moviereviewSpringBootApplicaion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder

public class ResponseDto {

    private boolean success;
    private String message;

    public ResponseDto(boolean success,String message){
        this.success=success;
        this.message=message;
    }
}
