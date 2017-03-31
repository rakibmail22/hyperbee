package net.bahmed.hyperbee.web.helper;

import net.bahmed.hyperbee.domain.User;
import net.bahmed.hyperbee.domain.enums.DisplayStatus;
import net.bahmed.hyperbee.domain.enums.NoteType;
import net.bahmed.hyperbee.service.BuzzService;
import net.bahmed.hyperbee.service.NoteService;
import net.bahmed.hyperbee.service.UserService;
import net.bahmed.hyperbee.web.security.AuthUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

import static net.bahmed.hyperbee.utils.constant.Constant.*;


/**
 * @author rayed
 * @author bashir
 * @author zoha
 * @since 11/24/16 12:12 PM
 */
@Component
public class SessionHelper {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    @Autowired
    private BuzzService buzzService;

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    public void persistInSession(User user) {
        AuthUser authUser = new AuthUser();
        authUser.setId(user.getId());
        authUser.setUsername(user.getUsername());
        authUser.setRoleList(user.getRoleList());

        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        session.setAttribute("authUser", authUser);
        initializeNoteStatForUser(authUser.getId());
    }

    public AuthUser getAuthUserFromSession() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpSession session = servletRequestAttributes.getRequest().getSession();

        return (AuthUser) session.getAttribute("authUser");
    }

    public void invalidateSession() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpSession session = servletRequestAttributes.getRequest().getSession();
        session.invalidate();
    }

    public int getUserIdFromSession() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpSession session = servletRequestAttributes.getRequest().getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("authUser");

        return authUser.getId();
    }

    public HttpSession getHttpSession() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();

        return session;
    }

    public void initializeNoteStatForUser(int userId) {
        int stickyNoteCount = noteService.getStickyNoteCountForUser(userId);
        int reminderCount = noteService.getRemainingReminderCountForUser(userId);

        HttpSession session = getHttpSession();
        session.setAttribute(SESSION_VARIABLE_STICKY_COUNT, stickyNoteCount);
        session.setAttribute(SESSION_VARIABLE_REMINDER_COUNT, reminderCount);
    }

    public void incrementNoteCountByOne(NoteType noteType) {
        if (noteType == NoteType.STICKY) {
            incrementSessionAttribute(SESSION_VARIABLE_STICKY_COUNT, 1);
        } else if (noteType == NoteType.REMINDER) {
            incrementSessionAttribute(SESSION_VARIABLE_REMINDER_COUNT, 1);
        }
    }

    public void decrementNoteCountByOne(String noteType) {
        if (noteType.equals(NOTE_STICKY)) {
            decrementSessionAttribute(SESSION_VARIABLE_STICKY_COUNT, 1);
        } else if (noteType.equals(NOTE_REMINDER)) {
            decrementSessionAttribute(SESSION_VARIABLE_REMINDER_COUNT, 1);
        }
    }

    public void setStatInSession() {
        AuthUser authUserFromSession = getAuthUserFromSession();

        if (authUserFromSession.isAdmin()) {
            int activeUser = userService.findByDisplayStatus(DisplayStatus.ACTIVE);
            int inactiveUser = userService.findByDisplayStatus(DisplayStatus.INACTIVE);

            int activeBuzz = buzzService.getActiveCount();
            int inactiveBuzz = buzzService.getInactiveCount();
            int flaggedBuzz = buzzService.getFlaggedCount();
            int pinnedBuzz = buzzService.getPinnedCount();

            setStat("activeUsers", activeUser);
            setStat("inactiveUsers", inactiveUser);

            setStat(SESSION_VARIABLE_ACTIVE_BUZZ_COUNT, activeBuzz);
            setStat(SESSION_VARIABLE_INACTIVE_BUZZ_COUNT, inactiveBuzz);
            setStat(SESSION_VARIABLE_FLAGGED_BUZZ_COUNT, flaggedBuzz);
            setStat(SESSION_VARIABLE_PINNED_BUZZ_COUNT, pinnedBuzz);
        } else {
            int activeBuzz = buzzService.getActiveCountByUser(authUserFromSession.getId());
            int flaggedBuzz = buzzService.getFlaggedCountByUser(authUserFromSession.getId());
            int pinnedBuzz = buzzService.getPinnedCountByUser(authUserFromSession.getId());

            setStat(SESSION_VARIABLE_ACTIVE_BUZZ_COUNT, activeBuzz);
            setStat(SESSION_VARIABLE_FLAGGED_BUZZ_COUNT, flaggedBuzz);
            setStat(SESSION_VARIABLE_PINNED_BUZZ_COUNT, pinnedBuzz);
        }
    }

    public void setStat(String key, int value) {
        HttpSession httpSession = getHttpSession();
        httpSession.setAttribute(key, value);
    }

    public int getStat(String key) {
        return (Integer) getHttpSession().getAttribute(key);
    }

    public void decrementSessionAttribute(String key, int increment) {
        int count = getStat(key);
        setStat(key, count - increment);
    }

    public void incrementSessionAttribute(String key, int increment) {
        int count = getStat(key);
        setStat(key, count + increment);
    }
}
