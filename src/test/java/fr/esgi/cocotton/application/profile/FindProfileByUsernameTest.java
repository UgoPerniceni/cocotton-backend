package fr.esgi.cocotton.application.profile;

import fr.esgi.cocotton.domain.models.profile.ProfileDao;
import fr.esgi.cocotton.infrastructure.common.exception.ProfileWithUsernameNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FindProfileByUsernameTest {

    @InjectMocks
    private FindProfileByUsername findProfileByUsername;

    @Mock
    private ProfileDao profileDao;

    @Test(expected = ProfileWithUsernameNotFoundException.class)
    public void should_not_return_profile_when_username_does_not_exist() {
        findProfileByUsername.execute("test");
        verify(profileDao).findByUsername("test");
    }
}
