package net.bahmed.hyperbee.web.validator;

import net.bahmed.hyperbee.domain.Buzz;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author zoha
 * @since 11/27/16
 */
@Component
public class BuzzValidator implements Validator {

    @Override
    public boolean supports(Class<?> referenceClass) {
        return referenceClass.isAssignableFrom(Buzz.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "buzz.message.required");
    }
}
