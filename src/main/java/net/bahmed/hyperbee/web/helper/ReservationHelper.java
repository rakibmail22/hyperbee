package net.bahmed.hyperbee.web.helper;

import net.bahmed.hyperbee.service.ReservationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rumman
 * @since 11/30/16
 */
@Component
public class ReservationHelper {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);
    private static final int RESERVATION_TO_DISPLAY_IN_SIDEBAR = 3;

    @Autowired
    private ReservationService reservationService;

    public Calendar getCalendarFromString(String dateTime) {
        Calendar calendar = new GregorianCalendar();

        String regExp = "^([0-9]+)/([0-9]+)/([0-9]+) ([0-9]+):([0-9]+) (AM|PM)$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(dateTime);

        while (matcher.find()) {

            calendar.set(Calendar.MONTH, Integer.parseInt(matcher.group(1)) - 1);
            log.debug("MONTH: " + matcher.group(1));

            calendar.set(Calendar.DATE, Integer.parseInt(matcher.group(2)));
            log.debug("DATE: " + matcher.group(2));

            calendar.set(Calendar.YEAR, Integer.parseInt(matcher.group(3)));
            log.debug("YEAR: " + matcher.group(3));

            calendar.set(Calendar.HOUR, Integer.parseInt(matcher.group(4)));
            log.debug("MONTH: " + matcher.group(4));

            calendar.set(Calendar.MINUTE, Integer.parseInt(matcher.group(5)));
            log.debug("MONTH: " + matcher.group(5));

            calendar.set(Calendar.AM_PM, (matcher.group(6)).equals("AM") ? 0 : 1);
            log.debug("MONTH: " + matcher.group(6));

        }

        return calendar;
    }

    public void persistInSession() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        session.setAttribute("cachedReservationList", reservationService.findLatestReservation(RESERVATION_TO_DISPLAY_IN_SIDEBAR));
    }
}
