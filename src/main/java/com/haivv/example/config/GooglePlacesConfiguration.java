package com.haivv.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.redroma.google.places.GooglePlacesAPI;

@Configuration
public class GooglePlacesConfiguration {

    @Bean
    public GooglePlacesAPI googlePlacesAPI() {
        return GooglePlacesAPI.create("AIzaSyCTKmgoAIsowok_-81Sbj7QjpzdJgEX74g");
    }
}
