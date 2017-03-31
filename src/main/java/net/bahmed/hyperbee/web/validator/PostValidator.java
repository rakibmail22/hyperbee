package net.bahmed.hyperbee.web.validator;

import net.bahmed.hyperbee.domain.Post;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author azim
 * @since 11/30/16
 */
@Component
public class PostValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return Post.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "post.description.required");
    }
}
