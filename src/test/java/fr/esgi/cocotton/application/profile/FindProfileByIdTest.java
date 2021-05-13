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
public class FindProfileByIdTest {

    @InjectMocks
    private FindProfileById findProfileById;

    @Mock
    private ProfileDao profileDao;

    @Test(expected = ResourceNotFoundException.class)
    public void should_return_profile_when_id_does_not_exist() {
        findProfileById.execute("test-id");
        verify(profileDao).findById("test-id");
    }
}
