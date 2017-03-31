package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.Note;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bashir
 * @since 11/22/16
 */
@Repository
public interface NoteDao {

    Note save(Note note);

    void markNoteAsInactiveForUser(int userId, int noteId);

    List<Note> findActiveNoteListByUserId(int userId);

    List<Note> findTopStickyNoteByUser(int numberOfNotes, int userId);

    int markExpiredNoteAsInactive();

    List<Note> findUpcomingReminderNoteByUser(int userId);

    int getRemainingReminderCountForUser(int userId);

    int getStickyNoteCountForUser(int userId);
}
