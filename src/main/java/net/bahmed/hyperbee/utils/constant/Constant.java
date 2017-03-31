package net.bahmed.hyperbee.utils.constant;

/**
 * @author bashir
 * @author rayed
 * @author zoha
 * @since 11/21/16
 */
public interface Constant {

    String DISPLAY_ACTIVE = "ACTIVE";
    String DISPLAY_INACTIVE = "INACTIVE";
    String DISPLAY_STATUS_ENUM = "ENUM('ACTIVE','INACTIVE') default 'ACTIVE'";
    String ADMIN_ROLE = "ADMIN";
    String USER_ROLE = "USER";
    String USER_STATUS_ENUM = "ENUM('ADMIN','USER')";
    String DISPLAY_STATUS_FIELD = "display_status";
    String APPROVED = "APPROVED";
    String REJECTED = "REJECTED";
    String PENDING = "PENDING";
    String RES_STATUS_ENUM = "ENUM('APPROVED','REJECTED','PENDING')";
    String DATE_TIME_FIELD = "DATETIME";
    String TEXT_FIELD = "TEXT";
    String HIGH_PRIORITY = "HIGH";
    String LOW_PRIORITY = "LOW";
    String MEDIUM_PRIORITY = "MEDIUM";
    String PRIORITY_ENUM = "enum('HIGH','LOW','MEDIUM')";
    String NOTE_STICKY = "STICKY";
    String NOTE_REMINDER = "REMINDER";
    String NOTE_TYPE_ENUM = "enum('STICKY','REMINDER')";

    int STICKY_NOTE_COUNT_DASHBOARD = 4;
    int REMINDER_NOTE_COUNT_DASHBOARD = 4;

    String PROFILE_ATTRIBUTE = "profile";
    String USER_ATTRIBUTE = "user";
    String AUTH_USER_ATTRIBUTE = "authUser";

    String SESSION_VARIABLE_STICKY_COUNT = "stickyCount";
    String SESSION_VARIABLE_REMINDER_COUNT = "reminderCount";

    String SESSION_VARIABLE_ACTIVE_BUZZ_COUNT = "activeBuzz";
    String SESSION_VARIABLE_INACTIVE_BUZZ_COUNT = "inactiveBuzz";
    String SESSION_VARIABLE_PINNED_BUZZ_COUNT = "pinnedBuzz";
    String SESSION_VARIABLE_FLAGGED_BUZZ_COUNT = "flaggedBuzz";;
}
