package net.bahmed.hyperbee.utils.constant;

/**
 * @author duity
 * @author zoha
 * @since 11/23/16.
 */
public interface Messages {

    String PROFILE_SAVE_MESSAGE = " The Profile is updated ";

    String NOTE_DELETE_SUCCESS = "Note has been deleted";
    String NOTE_SAVE_SUCCESS = "Note has been saved";
    String NOTICE_LIST_VIEWED = "Notice List View";
    String NOTE_SAVE_FAILURE = "Note was not saved. Please Try Again";

    String NOTE_PAGE_VIEW_ACTIVITY = "Visited own notes page";
    String NOTE_SAVE_ACTIVITY = "Saved a new note";
    String NOTE_SAVE_FAILED = "Tried to save an invalid note";
    String NOTE_DELETE_ACTIVITY = "Deleted a note";

    //Notice Messages
    String NOTICE_VIEWED = "Notice Home Page Viewed";
    String NOTICE_ADD_VIEWED = "Notice Add Page Viewed";
    String NOTICE_EDIT_VIEWED = "Notice Edit Page Viewed";
    String NOTICE_SAVE_ERROR = "ERROR:: Notice Could Not be saved";
    String NOTICE_SAVED = "Saved Notice";
    String NOTICE_MODIFIED = "Modified Notice";
    String NOTICE_DELETED = "Deleted Notice";
    String NOTICE_ACCESS_DENIED = "Only Admin Users Can Access Notice Area";
    String NOTICE_SUCCESS= "Notice Saved Successfully";
    String NOTICE_HTML_PAGE_ACTIVE_KEY = "notice";
    String NOTICE_PAGE_ADD_HEADER = "Add Notice";
    String NOTICE_PAGE_EDIT_HEADER = "Edit Notice";
    String SUCCESS_HTML_CLASS = "alert alert-success";
    String FAILURE_HTML_CLASS = "alert alert-danger";
    String LOGGED_IN = "Logged In";
    String SIGNED_UP = "Signed Up";

    //Buzz Messages
    String BUZZ_SEND_SUCCESS = "Successfully sent buzz: <message>";
    String BUZZ_FLAG_SUCCESS = "Successfully flagged buzz: <message>";
    String BUZZ_DELETE_SUCCESS = "Successfully removed buzz: <message>";
    String BUZZ_PINNED_SUCCESS = "Successfully pinned buzz: <message>";
    String BUZZ_HISTORY_REQUEST = "Retrieved complete buzz list for viewing history";
    String BUZZ_ACCESS_DENIED = "Only administrators can view Buzz History!";

    //Conference Messages
    String CONFERENCE_LIST_VIEWED = "Conference Home Page Viewed";
    String CONFERENCE_ADD_VIEWED = "Conference Add Page Viewed";
    String CONFERENCE_EDIT_VIEWED = "Conference Edit Page Viewed";
    String CONFERENCE_SAVE_ERROR = "ERROR:: Conference Could Not be saved";
    String CONFERENCE_SAVED = "Saved Conference";
    String CONFERENCE_DELETED = "Deleted Conference";
    String CONFERENCE_HTML_PAGE_ACTIVE_KEY = "conferenceRoom";
    String CONFERENCE_PAGE_ADD_HEADER = "Add Conference Room";
    String CONFERENCE_PAGE_EDIT_HEADER = "Edit ConferemceRoom";

    //Reservation Messages
    String RESERVATION_LIST_VIEWED = "Reservation Home Page Viewed";
    String RESERVATION_ADD_VIEWED = "Reservation Add Page Viewed";
    String RESERVATION_EDIT_VIEWED = "Reservation Edit Page Viewed";
    String RESERVATION_SAVE_ERROR = "ERROR:: Reservation Could Not be saved";
    String RESERVATION_SAVED = "Saved Reservation";
    String RESERVATION_DELETED = "Deleted Reservation";
    String RESERVATION_HTML_PAGE_ACTIVE_KEY = "reservation";
    String RESERVATION_PAGE_ADD_HEADER = "Add Reservation";
    String RESERVATION_PAGE_EDIT_HEADER = "Edit Reservation";


    String VISIT_EDIT_PROFILE_ACTIVITY= "Visited profile field entry form.";

    String EDITED_PROFILE_ACTIVITY= "Profile is edited";
    String VISITED_PROFILE_ACTIVITY="Profile has been visited";
    String VISIT_STALKUSER_ACTIVITY="Visited stalk user page";
    String STALK_PROFILE_ACTIVITY="searched user profile";
    String STALK_USER_PROFILE_ACTIVITY="stalked user profile";
    String NO_USER_FOUND = "No user Found with This username.";

    //HTML Title for Done
    String TITLE_SUCCESS = "Success";
    String TITLE_FAILURE = "Failure";
    String TITLE_ACCESS_DENIED = "Access Denied";
}
