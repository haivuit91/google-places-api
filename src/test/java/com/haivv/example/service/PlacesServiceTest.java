package com.haivv.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tech.redroma.google.places.GooglePlacesAPI;
import tech.redroma.google.places.requests.GetPhotoRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PlacesServiceTest {

    @Autowired
    private PlacesService placesService;

    @Autowired
    private GooglePlacesAPI client;

    @Test
    public void getPlaces() {
//        placesService.autocomplete("147 Hoang Quoc Viet");
//        placesService.detail("ChIJwaJMuzyrNTERF61DDz7w2To");
        GetPhotoRequest.Builder detailsRequest = GetPhotoRequest.newBuilder()
                .withPhotoReference("");
        System.out.println(client.getPhoto(detailsRequest.build()));
    }
}
