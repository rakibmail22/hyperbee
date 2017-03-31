package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.dao.NoteDao;
import net.bahmed.hyperbee.dao.UserDao;
import net.bahmed.hyperbee.domain.Note;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static net.bahmed.hyperbee.utils.constant.Constant.STICKY_NOTE_COUNT_DASHBOARD;

/**
 * @author bashir
 * @since 11/22/16
 */
@Service
public class NoteServiceImpl implements NoteService {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    @Autowired
    NoteDao noteDao;

    @Autowired
    UserDao userDao;

    @Override
    public List<Note> findActiveNotesForUser(int userId) {

        return noteDao.findActiveNoteListByUserId(userId);
    }

    @Override
    public List<Note> findTopStickyNoteByUser(int userId) {
        List<Note> noteList = noteDao.findTopStickyNoteByUser(STICKY_NOTE_COUNT_DASHBOARD, userId);
        log.debug("Top Sticky Note Dashboard: " + noteList.size());

        return noteList;
    }

    @Override
    @Transactional
    public void saveNoteForUser(Note note, int userId) {
        User user = userDao.findById(userId);
        note.setUser(user);
        noteDao.save(note);
    }

    @Override
    @Transactional
    public void markNoteAsInactiveForUser(int userId, int noteId) {

        noteDao.markNoteAsInactiveForUser(userId, noteId);
    }

    @Override
    public List<Note> findUpcomingReminderNoteByUser(int userId) {

        return noteDao.findUpcomingReminderNoteByUser(userId);
    }

    @Override
    public int getRemainingReminderCountForUser(int userId) {

        return noteDao.getRemainingReminderCountForUser(userId);
    }

    @Override
    public int getStickyNoteCountForUser(int userId) {

        return noteDao.getStickyNoteCountForUser(userId);
    }
}
