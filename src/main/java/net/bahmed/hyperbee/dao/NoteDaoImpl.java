package net.bahmed.hyperbee.dao;

import net.bahmed.hyperbee.domain.Note;
import net.bahmed.hyperbee.domain.enums.DisplayStatus;
import net.bahmed.hyperbee.domain.enums.NoteType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

import static net.bahmed.hyperbee.utils.constant.Constant.REMINDER_NOTE_COUNT_DASHBOARD;
import static net.bahmed.hyperbee.utils.constant.Constant.STICKY_NOTE_COUNT_DASHBOARD;

/**
 * @author bashir
 * @since 11/22/16
 */
@Repository
public class NoteDaoImpl implements NoteDao {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    private static final String NOTE_ARCHIVE_SCHEDULER_NATIVE_QUERY = "UPDATE note n SET " +
            " n.display_status = 'INACTIVE' WHERE n.date_remind < curdate() AND n.date_remind IS NOT NULL;";

    private static final String NOTE_REMAINING_REMINDER_COUNT_QUERY = "SELECT COUNT(*) " +
            " FROM note n WHERE n.type='REMINDER' AND n.date_remind > now() AND n.display_status = 'ACTIVE' " +
            " AND n.user_id=?;";

    private static final String NOTE_STICKY_COUNT_QUERY = "SELECT COUNT(*) FROM note n " +
            " WHERE n.type='STICKY' AND n.display_status= 'ACTIVE' AND n.user_id=?;";

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Note save(Note note) {
        if (note.isNew()) {
            em.persist(note);
        } else {
            note = em.merge(note);
        }
        em.flush();
        return note;
    }

    @Override
    @Transactional
    public void markNoteAsInactiveForUser(int userId, int noteId) {
        em.createNamedQuery("Note.updateDisplayStatusForUser")
                .setParameter("userId", userId)
                .setParameter("noteId", noteId)
                .setParameter("displayStatus", DisplayStatus.INACTIVE)
                .executeUpdate();
        em.flush();
    }

    @Override
    public List<Note> findActiveNoteListByUserId(int userId) {

        return em.createNamedQuery("Note.findNoteByUserId", Note.class)
                .setParameter("userId", userId)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .getResultList();
    }

    @Override
    public List<Note> findTopStickyNoteByUser(int numberOfNotes, int userId) {

        return em.createNamedQuery("Note.findTopStickyNoteByUserId", Note.class)
                .setParameter("userId", userId)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .setParameter("type", NoteType.STICKY)
                .setMaxResults(STICKY_NOTE_COUNT_DASHBOARD)
                .getResultList();
    }

    @Override
    @Transactional
    public int markExpiredNoteAsInactive() {
        return em.createNativeQuery(NOTE_ARCHIVE_SCHEDULER_NATIVE_QUERY).executeUpdate();
    }

    @Override
    public List<Note> findUpcomingReminderNoteByUser(int userId) {

        return em.createNamedQuery("Note.reminderForUserDash", Note.class)
                .setParameter("userId", userId)
                .setParameter("displayStatus", DisplayStatus.ACTIVE)
                .setParameter("type", NoteType.REMINDER)
                .setMaxResults(REMINDER_NOTE_COUNT_DASHBOARD)
                .getResultList();
    }

    public int getRemainingReminderCountForUser(int userId) {
        Query query = em.createNativeQuery(NOTE_REMAINING_REMINDER_COUNT_QUERY);
        query.setParameter(1, userId);

        BigInteger count = (BigInteger) query.getSingleResult();
        return count.intValue();
    }

    public int getStickyNoteCountForUser(int userId) {
        Query query = em.createNativeQuery(NOTE_STICKY_COUNT_QUERY);
        query.setParameter(1, userId);

        BigInteger count = (BigInteger) query.getSingleResult();

        return count.intValue();
    }
}
