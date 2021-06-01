package com.example.finalprojectdanielasamuil.api.geocode;

import com.example.finalprojectdanielasamuil.api.geocode.GeocodeResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;

import static com.example.finalprojectdanielasamuil.UrlMapping.GEOCODE;

@RestController
@RequestMapping(GEOCODE)
public class GeocodeController {

    @GetMapping()
    public GeocodeResult getGeocode(@RequestParam String address) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        Request request = new Request.Builder()
                .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress)
                .get()
                .addHeader("x-rapidapi-key", "43781aae48mshc1de059d6b673aep14677djsn260e1889ab9b")
                .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
                .build();

        ResponseBody responseBody = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();
        GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);
        System.out.println(result.getResults().get(0));
        return result;
    }

}
