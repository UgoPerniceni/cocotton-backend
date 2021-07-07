package fr.esgi.cocotton.beer.application;

import fr.esgi.cocotton.beer.infrastructure.error.RestTemplateResponseErrorHandler;
import fr.esgi.cocotton.profile.application.FindProfileFromToken;
import fr.esgi.cocotton.profile.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FindAllBeers {

    private final RestTemplate restTemplate;
    private final String endpoint;
    private final String minorEndpoint;
    private final FindProfileFromToken findProfileFromToken;

    @Autowired
    public FindAllBeers(RestTemplateBuilder restTemplateBuilder, @Value("${external.api.endpoint}") String endpoint, @Value("${external.api.minor.endpoint}") String minorEndpoint, FindProfileFromToken findProfileFromToken) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        this.endpoint = endpoint;
        this.minorEndpoint = minorEndpoint;
        this.findProfileFromToken = findProfileFromToken;
    }

    public List<?> execute(String token) {
        Profile profile = findProfileFromToken.execute(token);
        if(!profile.isAdult()){
            return restTemplate.getForObject(minorEndpoint, List.class);
        }
        return restTemplate.getForObject(endpoint, List.class);
    }
}
