package fr.esgi.cocotton.application.profile;

import fr.esgi.cocotton.domain.models.profile.Profile;
import fr.esgi.cocotton.domain.models.profile.ProfileDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddProfileTest {

    @InjectMocks
    private AddProfile addProfile;

    @Mock
    private ProfileDao profileDao;

    @Test
    public void should_create_profile(){
        Profile profile = new Profile("Kelig", "Martin", "Keligzer", "kelig@martin.fr", "Test1234!", "M", LocalDate.of(1997, 4,  4));
        addProfile.execute(profile);
        verify(profileDao).save(profile);
    }
}
