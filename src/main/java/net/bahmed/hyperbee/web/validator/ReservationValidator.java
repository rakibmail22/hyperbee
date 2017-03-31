package net.bahmed.hyperbee.web.validator;

import net.bahmed.hyperbee.domain.Reservation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author rumman
 * @since 11/28/16
 */
@Component
public class ReservationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Reservation.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reservationFrom", "reservation.from.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reservationTo", "reservation.to.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "conferenceRoom", "reservation.conferenceRoom.required");
    }
}