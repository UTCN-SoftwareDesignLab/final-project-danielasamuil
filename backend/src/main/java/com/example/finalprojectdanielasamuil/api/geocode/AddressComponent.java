package com.example.finalprojectdanielasamuil.api.geocode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressComponent {

    @JsonProperty("long_name")
    String longName;
    @JsonProperty("short_name")
    String shortName;
    List<String> types;
}
