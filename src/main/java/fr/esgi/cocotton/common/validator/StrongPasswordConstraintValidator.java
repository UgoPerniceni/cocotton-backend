package fr.esgi.cocotton.common.validator;

import fr.esgi.cocotton.common.annotation.StrongPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StrongPasswordConstraintValidator implements ConstraintValidator<StrongPassword, String> {
    public void initialize(StrongPassword strongPassword) {}

    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if(password == null){
            return false;
        }
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[?!;*@#$%^&-+=()])(?=\\S+$).{8,20}$";
        return password.matches(regex);
    }
}
