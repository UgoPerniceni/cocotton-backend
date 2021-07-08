package fr.esgi.cocotton.profile.infrastructure.controller;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import fr.esgi.cocotton.AbstractBigTest;
import fr.esgi.cocotton.authentication.application.Login;
import fr.esgi.cocotton.authentication.application.dto.LoginDTO;
import fr.esgi.cocotton.authentication.application.dto.RegisterDTO;
import fr.esgi.cocotton.profile.domain.Profile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpStatus.*;

public class ProfileControllerBigTest extends AbstractBigTest {

    @Autowired
    Login login;

    private String token;
    private String currentProfileToken;
    private String testProfileId;
    private String currentProfileId;

    Profile profile = Profile.builder()
            .lastName("userlastName")
            .firstName("userfirstName")
            .username("profileName")
            .password("@_Strong-password01")
            .email("testeur@mail.fr")
            .birthDate(LocalDate.of(2011, 11, 11))
            .build();

    RegisterDTO registerDTO = RegisterDTO.builder()
            .lastName(profile.getLastName())
            .firstName(profile.getFirstName())
            .username(profile.getUsername())
            .password(profile.getPassword())
            .email(profile.getEmail())
            .birthDate(profile.getBirthDate())
            .build();

    LoginDTO loginDTO = LoginDTO.builder()
            .username(profile.getUsername())
            .password(profile.getPassword())
            .build();

    @Before
    public void init() {
        this.testProfileId = registerTestUser();
        this.token = tokenProvider();
        this.currentProfileId = given()
                .headers(
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON
                )
                .contentType(JSON)
                .body(toJson(this.registerDTO))
                .when()
                .post("/auth/register")
                .getHeader("Location")
                .replace("/profiles/", "");

        this.currentProfileToken = login.execute(this.loginDTO).get("Authorization").get(0);
    }

    @Test
    public void should_register_1_profile() {
        RegisterDTO currentRegisterDTO = RegisterDTO.builder()
                .lastName(profile.getLastName())
                .firstName(profile.getFirstName())
                .username("profile.getUsername()")
                .password(profile.getPassword())
                .email("profile@getEmail")
                .birthDate(profile.getBirthDate())
                .build();

        Response response = given()
                .headers(
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON
                )
                .contentType(JSON)
                .body(toJson(currentRegisterDTO))
                .when()
                .post("/auth/register");

        response
                .then()
                .log().all()
                .statusCode(CREATED.value());

        String profileId = response.getHeader("Location")
                .replace("/profiles/", "");

        LoginDTO currentLoginDTO = LoginDTO.builder()
                .username("profile.getUsername()")
                .password(profile.getPassword())
                .build();

        String currentProfileToken = login.execute(currentLoginDTO).get("Authorization").get(0);

        given()
                .headers(
                        "Authorization",
                        currentProfileToken
                )
                .when()
                .delete("/api/profiles/" + profileId)
                .then()
                .log().all()
                .statusCode(NO_CONTENT.value());
    }

    @Test
    public void should_get_1_profile_by_id() {
        Profile fetchedProfile = given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/profiles/" + this.currentProfileId)
                .then()
                .log().all()
                .statusCode(OK.value())
                .extract()
                .as(Profile.class);

        this.profile.setId(this.currentProfileId);
        this.profile.setPassword(null);

        Assert.assertEquals(this.profile, fetchedProfile);
    }

    @Test
    public void should_get_1_profile_by_username() {
        Profile fetchedProfile = given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/profiles/" + this.currentProfileId)
                .then()
                .log().all()
                .statusCode(OK.value())
                .extract()
                .as(Profile.class);

        this.profile.setId(this.currentProfileId);
        this.profile.setPassword(null);

        Assert.assertEquals(this.profile, fetchedProfile);
    }

    @Test
    public void should_get_list_of_2_profiles() {
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/profiles/")
                .then()
                .log().all()
                .statusCode(OK.value())
                .body("$", hasSize(2));
    }

    @Test
    public void should_bad_request_when_get_profile_id_with_wrong_id() {
        String wrongFileId = "impossible";
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .get("/api/profiles/" + wrongFileId)
                .then()
                .log().all()
                .statusCode(NOT_FOUND.value());
    }

    @Test
    public void should_not_allowed_when_delete_not_owned_profile_by_id() {
        String wrongFileId = "impossible";
        given()
                .headers(
                        "Authorization",
                        this.token
                )
                .when()
                .delete("/api/profiles/" + wrongFileId)
                .then()
                .log().all()
                .statusCode(FORBIDDEN.value());
    }

    @After
    public void clear() {
        given()
                .headers(
                        "Authorization",
                        this.currentProfileToken
                )
                .when()
                .delete("/api/profiles/" + this.currentProfileId)
                .then()
                .log().all()
                .statusCode(NO_CONTENT.value());

        deleteTestUser(this.testProfileId, this.token);
    }

}
