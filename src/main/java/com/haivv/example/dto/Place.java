package com.haivv.example.dto;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {

    @JsonProperty(value = "predictions")
    private List<Prediction> predictions;

    @Data
    public static class Prediction {
        @JsonProperty(value = "description")
        private String description;
        @JsonProperty(value = "place_id")
        private String place_id;
        @JsonProperty(value = "structured_formatting")
        private Structured structured_formatting;

        private double lat = -111.222;
    }

    @Data
    public static class Structured {
        @JsonProperty(value = "main_text")
        private String main_text;
        @JsonProperty(value = "secondary_text")
        private String secondary_text;
    }
}
