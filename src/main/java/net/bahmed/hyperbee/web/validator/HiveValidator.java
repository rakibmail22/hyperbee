package net.bahmed.hyperbee.web.validator;

import net.bahmed.hyperbee.domain.Hive;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author azim
 * @since 11/30/16
 */
@Component
public class HiveValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return Hive.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "hive.name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "hive.description.required");
    }
}
