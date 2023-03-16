package com.example.moviereviewSpringBootApplicaion.dto.deserializer;

import com.example.moviereviewSpringBootApplicaion.dto.MovieDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class MovieDtoDeSerializer implements Deserializer<MovieDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public MovieDto deserialize(String s, byte[] bytes) {
        try {
            if(bytes == null) return null;
            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), MovieDto.class);
        } catch (Exception e) {
            throw new SerializationException("Error while de-serializing Movie " + e.toString());
        }
    }
}