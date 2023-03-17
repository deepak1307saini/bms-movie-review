package com.example.moviereviewSpringBootApplicaion.service;

import com.example.moviereviewSpringBootApplicaion.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Autowired
    private MovieService movieService;

    @KafkaListener(topics = "movies", groupId = "review-movie-consumer-group", containerFactory = "movieListenerContainerFactory")
    public void listenMovie(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String operation, MovieDto movieDto) {
        switch (operation) {
            case "add": movieService.addMovie(movieDto); break;
            case "update": movieService.updateMovie(movieDto.getId(), movieDto); break;
            case "delete": movieService.deleteMovie(movieDto.getId()); break;
        }
    }
}
