package com.haivv.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haivv.example.dto.Place;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author haivv
 */
@Service
public class PlacesService {

    private static final Logger logger = LoggerFactory.getLogger(PlacesService.class);

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";

    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String TYPE_DETAIL = "/details";

    private static final String OUT_JSON = "/json";

    private static final String PARAM_INOUT = "input";
    private static final String PARAM_PLACE_ID = "place_id";

    // KEY!
    private static final String API_KEY = "AIzaSyCTKmgoAIsowok_-81Sbj7QjpzdJgEX74g";

    private ObjectMapper objectMapper;

    @Autowired
    public PlacesService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Place autocomplete(String input) {
        String result = getGoogleAPI(input, TYPE_AUTOCOMPLETE, PARAM_INOUT);
        Place place = null;
        try {
            place = objectMapper.readValue(result, Place.class);

            place.getPredictions().forEach(item -> {
                logger.info("JSON String Result {}", item);
            });
        } catch (IOException e) {
            logger.info("Error {}", e);
        }

        return place;
    }

    public void detail(String input) {
        String result = getGoogleAPI(input, TYPE_DETAIL, PARAM_PLACE_ID);
        logger.info("JSON String Result {}", result);
    }

    private String getGoogleAPI(String input, String type, String parram) {
        try {
            StringBuilder url = new StringBuilder(PLACES_API_BASE);
            url.append(type);
            url.append(OUT_JSON);
            url.append(String.format("?%s=", parram)).append(URLEncoder.encode(input, "utf8"));
            url.append("&key=" + API_KEY);

            URL urlForGetRequest = new URL(url.toString());
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();

                return response.toString();
            } else {
                logger.info("GET NOT WORKED");
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return "";
    }
}
