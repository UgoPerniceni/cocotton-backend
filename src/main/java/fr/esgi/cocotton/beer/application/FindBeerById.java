package fr.esgi.cocotton.beer.application;

import fr.esgi.cocotton.beer.infrastructure.error.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FindBeerById {

    private final RestTemplate restTemplate;
    private final String endpoint;

    @Autowired
    public FindBeerById(RestTemplateBuilder restTemplateBuilder, @Value("${external.api.endpoint}") String endpoint) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        this.endpoint = endpoint;
    }

    public Object execute(int id) {
        return restTemplate.getForObject(endpoint + "/"+ id, Object.class);
    }
}
