package net.bahmed.hyperbee.domain.enums;

import static net.bahmed.hyperbee.utils.constant.Constant.NOTE_REMINDER;
import static net.bahmed.hyperbee.utils.constant.Constant.NOTE_STICKY;

/**
 * @author bashir
 * @since 11/28/16
 */
public enum NoteType {

    STICKY(NOTE_STICKY),
    REMINDER(NOTE_REMINDER);

    String noteType;

    NoteType(String noteType) {

        this.noteType = noteType;
    }
}
