package net.bahmed.hyperbee.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
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
@Table(name = "profile")
public class Profile implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = DATE_TIME_FIELD, name = "date_of_birth")
    @DateTimeFormat(pattern = "dd-MM-yy")
    private Calendar dateOfBirth;


    private String address;

    @Column(name = "contact_no")
    private String contactNo;

    private String skills;

    @Column(name = "work_history")
    private String workHistory;

    private String designation;

    private String school;

    private String college;

    private String university;

    @Column(name = "job_experience_years")
    private int jobExperienceYears;

    @Column(columnDefinition = DATE_TIME_FIELD, name = "joining_date")
    @DateTimeFormat(pattern = "dd-MM-yy")
    private Calendar joiningDate;

    private String gender;

    @Column(name = "image")
    private String imagePath;

    @Column(name = "cover_image")
    private String coverImage;

    public Profile() {
        this.dateOfBirth = new GregorianCalendar();
        this.joiningDate = new GregorianCalendar();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(String workHistory) {
        this.workHistory = workHistory;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getJobExperienceYears() {
        return jobExperienceYears;
    }

    public void setJobExperienceYears(int jobExperienceYears) {
        this.jobExperienceYears = jobExperienceYears;
    }

    public Calendar getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Calendar joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
