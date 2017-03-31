package net.bahmed.hyperbee.domain;

import net.bahmed.hyperbee.domain.enums.DisplayStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static net.bahmed.hyperbee.utils.constant.Constant.DISPLAY_STATUS_ENUM;
import static net.bahmed.hyperbee.utils.constant.Constant.DISPLAY_STATUS_FIELD;

/**
 * @author bashir
 * @author rayed
 * @author duity
 * @author azim
 * @author zoha
 * @since 11/21/16
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "User.findByUsernameOrEmail",
                    query = "SELECT u FROM User u WHERE u.username = :username OR u.email = :email ")
})
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = DISPLAY_STATUS_FIELD, columnDefinition = DISPLAY_STATUS_ENUM)
    private DisplayStatus displayStatus;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "user")
    private List<Activity> activityList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Note> noteList;

    @OneToMany(mappedBy = "user")
    private List<Post> postList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false)
    )
    private List<Role> roleList;

    @ManyToMany(mappedBy = "userList")
    private List<Hive> hiveList;

    @OneToMany(mappedBy = "user")
    private List<Notice> noticeList;

    @OneToMany(mappedBy = "user")
    private List<Buzz> buzzList;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservationList;

    public User() {
        displayStatus = DisplayStatus.ACTIVE;
        activityList = new ArrayList<Activity>();
        noteList = new ArrayList<Note>();
        postList = new ArrayList<Post>();
        roleList = new ArrayList<Role>();
        hiveList = new ArrayList<Hive>();
        noticeList = new ArrayList<Notice>();
        buzzList = new ArrayList<Buzz>();
        reservationList = new ArrayList<Reservation>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DisplayStatus getDisplayStatus() {

        return displayStatus;
    }

    public void setDisplayStatus(DisplayStatus displayStatus) {
        this.displayStatus = displayStatus;
    }

    public Profile getProfile() {

        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Activity> getActivityList() {

        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    public List<Note> getNoteList() {

        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    public List<Post> getPostList() {

        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public List<Role> getRoleList() {

        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Hive> getHiveList() {

        return hiveList;
    }

    public void setHiveList(List<Hive> hiveList) {
        this.hiveList = hiveList;
    }

    public List<Notice> getNoticeList() {

        return noticeList;
    }

    public void setNoticeList(List<Notice> noticeList) {
        this.noticeList = noticeList;
    }

    public List<Buzz> getBuzzList() {

        return buzzList;
    }

    public void setBuzzList(List<Buzz> buzzList) {
        this.buzzList = buzzList;
    }

    public List<Reservation> getReservationList() {

        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public String toString() {

        return "Username: " + username + "\nFist Name: " + firstName +
                "\nLast Name: " + lastName + "\nEmail: " + email +
                "\nPassword: " + password;

    }
}
