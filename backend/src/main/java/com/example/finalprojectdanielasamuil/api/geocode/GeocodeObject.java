package com.example.finalprojectdanielasamuil.api.geocode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeObject {

    @JsonProperty("place_id")
    String placeId;

    @JsonProperty("address_components")
    List<AddressComponent> addressComponents;

    @JsonProperty("formatted_address")
    String formattedAddress;

    GeocodeGeometry geometry;
}