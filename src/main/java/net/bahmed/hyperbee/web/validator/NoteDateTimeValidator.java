package net.bahmed.hyperbee.web.validator;

import net.bahmed.hyperbee.domain.Note;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;

/**
 * @author bashir
 * @since 11/26/16
 */
@Component
public class NoteDateTimeValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {

        return Note.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Note note = (Note) target;

        if (null != note.getDateRemind() && Calendar.getInstance().compareTo(note.getDateRemind()) < 0) {
            errors.rejectValue("dateRemind", "note.date.past");
        }
    }
}
