package fr.esgi.cocotton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.Validatable;
import com.jayway.restassured.response.ValidatableResponse;
import fr.esgi.cocotton.authentication.application.dto.LoginDTO;
import fr.esgi.cocotton.authentication.application.dto.RegisterDTO;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasSize;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@ActiveProfiles("integration")
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:test.properties")
@SpringBootTest(webEnvironment = DEFINED_PORT, properties = "server.port=9090")
public class AbstractBigTest {

    private static final Logger LOGGER = getLogger(AbstractBigTest.class);

    RegisterDTO registerDTO = RegisterDTO.builder()
            .lastName("tester_lastName")
            .firstName("tester_firstName")
            .username("tester_username")
            .email("tester_email@gmail.com")
            .password("Azerty1_$")
            .birthDate(LocalDate.of(2020, 1, 8))
            .build();

    LoginDTO loginDTO = LoginDTO.builder()
            .username("tester_username")
            .password("Azerty1_$")
            .build();

    @Autowired
    ObjectMapper objectMapper;

    @Value("${server.port}")
    private Integer port;

    @Before
    public void setupRestassured() {
        RestAssured.port = port;
    }

    public <T> String toJson(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public String registerTestUser() {
        String testUserId = given()
                .contentType(JSON)
                .body(toJson(this.registerDTO))
                .when()
                .post("/auth/register")
                .getHeader("Location")
                .replace("/profiles/", "");
        return testUserId;
    }

    public String tokenProvider() {
        return given()
                .contentType(JSON)
                .body(toJson(this.loginDTO))
                .when()
                .post("/auth/login")
                .getHeader("Authorization");
    }

    public void deleteTestUser(String testUserId, String token) {
        ValidatableResponse response = given()
                .headers(
                        "Authorization",
                        token
                )
                .when()
                .delete("/api/profiles/" + testUserId)
                .then()
                .log().all()
                .statusCode(NO_CONTENT.value());
        System.out.println(response);
    }

    @Test
    void contextLoads() {
    }
}
