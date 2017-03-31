package net.bahmed.hyperbee.domain;

import net.bahmed.hyperbee.domain.enums.DisplayStatus;
import net.bahmed.hyperbee.domain.enums.NotePriority;
import net.bahmed.hyperbee.domain.enums.NoteType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static net.bahmed.hyperbee.utils.constant.Constant.*;


/**
 * @author bashir
 * @author rayed
 * @author azim
 * @author zoha
 * @since 11/21/16
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Note.findNoteByUserId",
                query = "SELECT n FROM Note n WHERE n.user.id = :userId AND n.displayStatus = :displayStatus " +
                        " ORDER BY n.dateRemind"),
        @NamedQuery(name = "Note.updateDisplayStatusForUser",
                query = "UPDATE Note n SET n.displayStatus = :displayStatus WHERE n.id = :noteId AND n.user.id = :userId"),
        @NamedQuery(name = "Note.findTopStickyNoteByUserId",
                query = "SELECT n FROM Note n WHERE n.user.id = :userId AND n.displayStatus = :displayStatus " +
                        " AND  n.noteType = :type ORDER BY n.id DESC"),
        @NamedQuery(name = "Note.reminderForUserDash",
                query = "SELECT n FROM Note n WHERE n.user.id = :userId AND n.displayStatus = :displayStatus " +
                        " AND  n.noteType = :type ORDER BY n.dateRemind")
})
@Table(name = "note")
public class Note implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 20, message = "{note.title.long}")
    private String title;

    @NotNull
    @Size(min = 1, max = 250, message = "{note.description.required}")
    private String description;

    @Column(name = "date_created", columnDefinition = DATE_TIME_FIELD)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateCreated;

    @Column(name = "date_remind", columnDefinition = DATE_TIME_FIELD)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateRemind;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = PRIORITY_ENUM)
    private NotePriority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = DISPLAY_STATUS_FIELD, columnDefinition = DISPLAY_STATUS_ENUM)
    private DisplayStatus displayStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = NOTE_TYPE_ENUM)
    private NoteType noteType;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public Note() {

        priority = NotePriority.LOW;
        displayStatus = displayStatus.ACTIVE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Calendar getDateRemind() {
        return dateRemind;
    }

    public void setDateRemind(Calendar dateRemind) {
        this.dateRemind = dateRemind;
    }

    public NotePriority getPriority() {
        return priority;
    }

    public void setPriority(NotePriority priority) {
        this.priority = priority;
    }

    public DisplayStatus getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(DisplayStatus displayStatus) {
        this.displayStatus = displayStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public void setNoteType(NoteType noteType) {
        this.noteType = noteType;
    }

    public boolean isNew() {

        return (id == 0);
    }

    public String getRemindDateFormatted() {

        if (null == dateRemind) {

            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        return sdf.format(dateRemind.getTimeInMillis());
    }

    public String getNoteTypeAsString() {

        return (noteType == NoteType.REMINDER) ? NOTE_REMINDER : NOTE_STICKY;
    }

    public String toString() {

        return "Title: " + getTitle() + "\n Description: " + getDescription();
    }
}
