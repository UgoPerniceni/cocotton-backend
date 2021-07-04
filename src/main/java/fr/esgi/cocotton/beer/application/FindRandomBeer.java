package fr.esgi.cocotton.beer.application;

import fr.esgi.cocotton.beer.infrastructure.error.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FindRandomBeer {

    private final RestTemplate restTemplate;
    private final String endpoint;

    @Autowired
    public FindRandomBeer(RestTemplateBuilder restTemplateBuilder, @Value("${external.api.endpoint}") String endpoint) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        this.endpoint = endpoint;
    }

    public Object execute() {
        return restTemplate.getForObject(endpoint + "/random", Object.class);
    }
}