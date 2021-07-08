package fr.esgi.cocotton.profile.infrastructure.controller;

import fr.esgi.cocotton.profile.application.*;
import fr.esgi.cocotton.profile.domain.Profile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {
    @InjectMocks
    ProfileController profileController;
    @Mock
    AddProfile addProfile;
    @Mock
    DeleteProfileById deleteProfileById;
    @Mock
    FindAllProfiles findAllProfiles;
    @Mock
    FindProfileById findProfileById;

    Profile profile = Profile.builder()
            .lastName("userlastName")
            .firstName("userfirstName")
            .username("profileName")
            .password("@_Strong-password01")
            .email("testeur@mail.fr")
            .birthDate(LocalDate.of(2011, 11, 11))
            .build();
    
    private final String token = "a token";
    private final String id = "anId";
    
    @Before
    public void init(){
        
    }
    
    @Test
    public void should_get_list_of_profiles() {
        profileController.findAll();
        verify(findAllProfiles).execute();
    }

    @Test
    public void should_find_1_profile_by_id() {
        profileController.findById(id);
        verify(findProfileById).execute(this.id);
    }

    @Test
    public void should_delete_profile_by_id() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", this.token);
        profileController.deleteById(this.id, headers);
        verify(deleteProfileById).execute(this.id, headers);
    }
}
