package fr.esgi.cocotton.infrastructure.profile.controller;

import fr.esgi.cocotton.application.profile.FindAllProfiles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProfileControllerTest {

    @InjectMocks
    ProfileController profileController;

    @Mock
    FindAllProfiles findAllProfiles;

    @Test
    public void should_get_list_of_profiles(){
        profileController.findAll();
        verify(findAllProfiles).execute();
    }
}
