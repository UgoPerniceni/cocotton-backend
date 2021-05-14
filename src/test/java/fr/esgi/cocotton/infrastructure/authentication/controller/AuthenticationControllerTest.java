package fr.esgi.cocotton.infrastructure.authentication.controller;

import fr.esgi.cocotton.application.authentication.Login;
import fr.esgi.cocotton.application.authentication.Register;
import fr.esgi.cocotton.application.authentication.dto.LoginDTO;
import fr.esgi.cocotton.application.authentication.dto.RegisterDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {

    @InjectMocks AuthenticationController authenticationController;

    @Mock
    Register register;

    @Mock
    Login login;

    @Test
    public void should_register_profile() {
        final RegisterDTO registerDTO = new RegisterDTO("Kelig", "Martin", "Keligzer", "kelig@martin.fr", "Test1234!", "M", LocalDate.of(1997, 4, 4));
        authenticationController.save(registerDTO);
        verify(register).execute(registerDTO);
    }

    @Test
    public void should_login_profile() {
        final LoginDTO loginDTO = new LoginDTO("Kelig", "Test1234!");
        authenticationController.login(loginDTO);
        verify(login).execute(loginDTO);
    }

}
