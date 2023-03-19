package com.example.moviereviewSpringBootApplicaion.config;

import com.example.moviereviewSpringBootApplicaion.dto.MovieDto;
import com.example.moviereviewSpringBootApplicaion.dto.deserializer.MovieDtoDeSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    public ConsumerFactory<String, MovieDto> consumerFactory() {

        // Creating a Map of string-object pairs
        Map<String, Object> config = new HashMap<>();

        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        config.put(ConsumerConfig.CLIENT_ID_CONFIG, "movie-consumer");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "review-movie-consumer-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, MovieDtoDeSerializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    // Creating a Listener
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MovieDto> movieListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MovieDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
