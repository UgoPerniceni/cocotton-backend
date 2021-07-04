package fr.esgi.cocotton.beer.application;

import fr.esgi.cocotton.beer.infrastructure.error.RestTemplateResponseErrorHandler;
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

    @Autowired
    public FindAllBeers(RestTemplateBuilder restTemplateBuilder, @Value("${external.api.endpoint}") String endpoint) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        this.endpoint = endpoint;
    }

    public List<?> execute() {
        return restTemplate.getForObject(endpoint, List.class);
    }
}
