package net.bahmed.hyperbee.web.validator;

import net.bahmed.hyperbee.domain.Notice;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author rumman
 * @since 11/28/16
 */
@Component
public class NoticeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Notice.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "notice.title.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "notice.description.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateExpired", "notice.dateExpired.required");

        Notice notice = (Notice) target;

        if (notice.getHiveList().size() == 0) {
            errors.rejectValue("hiveList", "notice.hivelist.required");
        }
    }
}
