package net.bahmed.hyperbee.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static net.bahmed.hyperbee.utils.constant.Constant.DATE_TIME_FIELD;

/**
 * @author bashir
 * @author rayed
 * @author duity
 * @author azim
 * @author zoha
 * @since 11/21/16
 */
@Entity
@Table(name = "activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "activity_time", columnDefinition = DATE_TIME_FIELD)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar activityTime;

    private String summary;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Activity() {
        this.activityTime = new GregorianCalendar();
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getActivityTime() {

        return activityTime;
    }

    public void setActivityTime(Calendar activityTime) {
        this.activityTime = activityTime;
    }

    public String getSummary() {

        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateAndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

        return sdf.format(activityTime.getTimeInMillis());
    }
}
