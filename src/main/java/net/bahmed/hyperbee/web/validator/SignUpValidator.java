package net.bahmed.hyperbee.web.validator;


import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.service.UserService;
import net.bahmed.hyperbee.web.command.SignUpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author rayed
 * @since 11/27/16 10:22 AM
 */
@Component
public class SignUpValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> signUpValidationClass) {
        return SignUpInfo.class.isAssignableFrom(signUpValidationClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password1", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "verifyPassword.required");

        SignUpInfo signUpInfo = (SignUpInfo) target;

        if (!signUpInfo.getPassword1().equals(signUpInfo.getPassword2())) {
            errors.rejectValue("password1", "password.mismatch");
        }

        User user = userService.findByUsernameOrEmail(signUpInfo.getUsername(), signUpInfo.getEmail());

        if (user != null) {
            if (user.getUsername().equals(signUpInfo.getUsername())) {
                errors.rejectValue("username", "username.unique");
            }
            if (user.getEmail().equals(signUpInfo.getEmail())) {
                errors.rejectValue("email", "email.unique");
            }
        }
    }
}
