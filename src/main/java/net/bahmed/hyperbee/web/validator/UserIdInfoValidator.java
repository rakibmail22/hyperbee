package net.bahmed.hyperbee.web.validator;

import net.bahmed.hyperbee.web.command.UserIdInfo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author azim
 * @since 11/30/16
 */
@Component
public class UserIdInfoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserIdInfo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userIdList", "userIdInfo.userList.required");
    }
}
