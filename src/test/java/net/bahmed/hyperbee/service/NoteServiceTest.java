package net.bahmed.hyperbee.service;

import net.bahmed.hyperbee.dao.NoteDao;
import net.bahmed.hyperbee.dao.UserDao;
import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.domain.enums.NoteType;
import net.bahmed.hyperbee.domain.Note;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static net.bahmed.hyperbee.utils.constant.Constant.STICKY_NOTE_COUNT_DASHBOARD;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author bashir
 * @since 12/3/16
 */
public class NoteServiceTest {

    @InjectMocks
    private NoteService noteService;

    @Mock
    private NoteDao noteDao;

    @Mock
    private UserDao userDao;

    private Mock mock;

    @Before
    public void setup() {
        noteService = new NoteServiceImpl();
        MockitoAnnotations.initMocks(this);
        when(noteDao.findActiveNoteListByUserId(1))
                .thenReturn(createDummyNoteList());
        when(noteDao.findTopStickyNoteByUser(STICKY_NOTE_COUNT_DASHBOARD, 1))
                .thenReturn(getTopNoteByUser(NoteType.STICKY));
        when(noteDao.findUpcomingReminderNoteByUser(1))
                .thenReturn(getTopNoteByUser(NoteType.REMINDER));
    }

    @Test
    public void findAllActiveNoteForUserTest() {
        assertEquals(2, noteDao.findActiveNoteListByUserId(1).size());
    }

    @Test
    public void findTopDashBoardNoteForUserTest() {
        assertEquals(1, noteDao.findTopStickyNoteByUser(STICKY_NOTE_COUNT_DASHBOARD,1).size());
        assertEquals(1, noteDao.findUpcomingReminderNoteByUser(1).size());
    }

    private List<Note> createDummyNoteList() {
        Note note = new Note();
        note.setTitle("Test Note");
        note.setDescription("Test Note For Mock Test");
        note.setNoteType(NoteType.STICKY);
        note.setId(1);

        User user = new User();
        user.setId(1);

        note.setUser(user);
        List<Note> noteList = new ArrayList<>();
        noteList.add(note);

        note = new Note();
        note.setTitle("Test Note 2");
        note.setDescription("2nd Test Note For Mock Test");
        note.setNoteType(NoteType.REMINDER);
        note.setDateRemind(new GregorianCalendar());
        note.setId(1);
        noteList.add(note);

        return noteList;
    }

    private List<Note> getTopNoteByUser(NoteType noteType) {
        List<Note> noteList = createDummyNoteList();
        List<Note> typedNoteList = new ArrayList<>();
        for (Note note: noteList) {
            if (note.getNoteType()==noteType) {
                typedNoteList.add(note);
            }
        }
        return typedNoteList;
    }
}
