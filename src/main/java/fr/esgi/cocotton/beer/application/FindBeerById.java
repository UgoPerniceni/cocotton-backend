package fr.esgi.cocotton.beer.application;

import fr.esgi.cocotton.beer.infrastructure.error.RestTemplateResponseErrorHandler;
import fr.esgi.cocotton.profile.application.FindProfileFromToken;
import fr.esgi.cocotton.profile.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FindBeerById {

    private final RestTemplate restTemplate;
    private final String endpoint;
    private final FindProfileFromToken findProfileFromToken;

    @Autowired
    public FindBeerById(RestTemplateBuilder restTemplateBuilder, @Value("${external.api.endpoint}") String endpoint, FindProfileFromToken findProfileFromToken) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        this.findProfileFromToken = findProfileFromToken;
        this.endpoint = endpoint;
    }

    public Object execute(int id, String token) {
        Profile profile = findProfileFromToken.execute(token);
        if(!profile.isAdult()) {
            return null;
        }
        return restTemplate.getForObject(endpoint + "/"+ id, Object.class);
    }
}
