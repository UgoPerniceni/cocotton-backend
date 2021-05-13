package fr.esgi.cocotton.application.profile;

import fr.esgi.cocotton.domain.models.profile.ProfileDao;
import fr.esgi.cocotton.infrastructure.common.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FindProfileByEmailTest {

    @InjectMocks
    private FindProfileByEmail findProfileByEmail;

    @Mock
    private ProfileDao profileDao;

    @Test(expected = ResourceNotFoundException.class)
    public void should_not_return_profile_when_email_does_not_exist() {
        findProfileByEmail.execute("test@test.fr");
        verify(profileDao).findByEmail("test@test.fr");
    }
}
