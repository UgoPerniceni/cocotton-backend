package fr.esgi.cocotton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server.port=9091")
@Slf4j
public abstract class AbstractBigTest {

    @Autowired
    ObjectMapper objectMapper;

    @Value("${server.port}")
    private Integer port;

    @Before
    public void setUpRestAssured() {
        RestAssured.port = port;
    }

    public <T> String toJson(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e){
            log.error("", e);
            return null;
        }
    }
}
