package net.bahmed.hyperbee.domain;

import net.bahmed.hyperbee.domain.enums.DisplayStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static net.bahmed.hyperbee.utils.constant.Constant.*;

/**
 * @author bashir
 * @author rumman
 * @author rayed
 * @author azim
 * @author zoha
 * @since 11/21/16
 */

@NamedQueries({
        @NamedQuery(name = "Notice.findAllNotice",
                query = "SELECT notice FROM Notice notice ORDER BY notice.id DESC"),
        @NamedQuery(name = "Notice.findLatestNotices",
                query = "SELECT notice FROM Notice notice WHERE notice.displayStatus = 'ACTIVE' ORDER BY notice.id DESC")
})
@Entity
@Table(name = "notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    @Column(columnDefinition = DATE_TIME_FIELD, name = "date_created")
    private Calendar dateCreated;

    @Column(columnDefinition = DATE_TIME_FIELD, name = "date_expired")
    @DateTimeFormat(pattern = "dd-MM-yy")
    private Calendar dateExpired;

    @Enumerated(EnumType.STRING)
    @Column(name = DISPLAY_STATUS_FIELD, columnDefinition = DISPLAY_STATUS_ENUM)
    private DisplayStatus displayStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "notice_hive",
            joinColumns = @JoinColumn(name = "notice_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "hive_id", nullable = false)
    )
    private List<Hive> hiveList;

    public Notice() {
        this.dateCreated = new GregorianCalendar();
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

    public Calendar getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Calendar dateExpired) {
        this.dateExpired = dateExpired;
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

    public List<Hive> getHiveList() {
        return hiveList;
    }

    public void setHiveList(List<Hive> hiveList) {
        this.hiveList = hiveList;
    }

    public boolean isNew() {
        return id == 0;
    }

    public String getRemindDateFormatted() {
        if (null == dateExpired) {

            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

        return sdf.format(dateExpired.getTimeInMillis());
    }
}
