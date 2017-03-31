package net.bahmed.hyperbee.web.helper;

import net.bahmed.hyperbee.domain.Note;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.bahmed.hyperbee.domain.enums.NoteType.REMINDER;
import static net.bahmed.hyperbee.domain.enums.NoteType.STICKY;

/**
 * @author bashir
 * @since 11/23/16
 */
@Component
public class NoteHelper {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    public Note getEmptyNote() {

        return new Note();
    }

    public void setCalendarValFromString(Note note, String dateTime) {

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
            note.setDateRemind(calendar);
        }
    }

    public void processNoteForSaving(Note note, String remindDate) {

        note.setDateCreated(new GregorianCalendar());

        if (remindDate.isEmpty()) {

            note.setNoteType(STICKY);

            return;
        }
        setCalendarValFromString(note, remindDate);
        note.setNoteType(REMINDER);
    }
}
