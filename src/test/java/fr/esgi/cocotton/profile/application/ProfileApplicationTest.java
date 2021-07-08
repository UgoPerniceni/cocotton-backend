package fr.esgi.cocotton.profile.application;

import fr.esgi.cocotton.authentication.application.Login;
import fr.esgi.cocotton.authentication.application.Register;
import fr.esgi.cocotton.authentication.application.dto.LoginDTO;
import fr.esgi.cocotton.authentication.application.dto.RegisterDTO;
import fr.esgi.cocotton.common.exception.ResourceNotFoundException;
import fr.esgi.cocotton.common.exception.ResourceOwnershipException;
import fr.esgi.cocotton.profile.domain.Profile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileApplicationTest {

    @Autowired
    Login login;
    @Autowired
    Register register;
    @Autowired
    AddProfile addProfile;
    @Autowired
    DeleteProfileById deleteProfileById;
    @Autowired
    FindAllProfiles findAllProfiles;
    @Autowired
    FindProfileById findProfileById;

    private String currentProfileId;
    private String userId;
    private String token;

    RegisterDTO registerDTO = RegisterDTO.builder()
            .lastName("tester_lastName")
            .firstName("tester_firstName")
            .username("appprofileName")
            .password("@_Strong-password01")
            .email("testeurApp@mail.fr")
            .birthDate(LocalDate.of(2020, 1, 8))
            .build();

    LoginDTO loginDTO = LoginDTO.builder()
            .username("appprofileName")
            .password("@_Strong-password01")
            .build();

    Profile mockProfile = Profile.builder()
            .lastName("userlastName")
            .firstName("userfirstName")
            .username("mockprofileName")
            .password("@_Strong-password01")
            .email("mock@mail.fr")
            .birthDate(LocalDate.of(2011, 11, 11))
            .build();

    @Before
    public void init() {
        this.userId = register.execute(this.registerDTO).toString().replace("/profiles/", "");
        this.token = login.execute(this.loginDTO).get("Authorization").get(0);
//        this.currentProfileId = addProfile.execute(this.profileTester);
    }

    @Test
    public void should_find_profile_by_id() {
        Profile profile = findProfileById.execute(this.userId);

        assertThat(profile).isNotNull();
        assertThat(profile.getId()).isEqualTo(this.userId);
    }

    @Test
    public void should_save_profile() {
        String id = addProfile.execute(this.mockProfile);
        assertThat(id).isNotNull();
        LoginDTO mockLoginDTO = LoginDTO.builder()
                .username(this.mockProfile.getUsername())
                .password(this.mockProfile.getPassword())
                .build();
        String mockToken = login.execute(mockLoginDTO).get("Authorization").get(0);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", mockToken);
        deleteProfileById.execute(id, headers);
    }

    @Test
    public void should_find_all_profiles() {
        List<Profile> profileList = findAllProfiles.execute();
        assertThat(profileList).hasSize(1);
    }

    @Test(expected = ResourceOwnershipException.class)
    public void should_not_delete_not_owned_profile_by_id() {
        String id = addProfile.execute(this.mockProfile);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", this.token);
        deleteProfileById.execute(id, headers);
        assertThat(findProfileById.execute(id)).isNotNull();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void should_delete_profile_by_id() {
        String id = addProfile.execute(this.mockProfile);
        LoginDTO mockLoginDTO = LoginDTO.builder()
                .username(this.mockProfile.getUsername())
                .password(this.mockProfile.getPassword())
                .build();
        String mockToken = login.execute(mockLoginDTO).get("Authorization").get(0);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", mockToken);
        deleteProfileById.execute(id, headers);
        assertThat(findProfileById.execute(id)).isNull();
    }

    @After
    public void clear() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", this.token);
        deleteProfileById.execute(this.userId, headers);
    }
}
