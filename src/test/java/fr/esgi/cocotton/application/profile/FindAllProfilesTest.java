package fr.esgi.cocotton.application.profile;

import fr.esgi.cocotton.domain.models.profile.ProfileDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FindAllProfilesTest {

    @InjectMocks
    private FindAllProfiles findAllProfiles;

    @Mock
    private ProfileDao profileDao;

    @Test
    public void should_get_profiles_nominal() {
        findAllProfiles.execute();
        verify(profileDao).findAll();
    }
}
