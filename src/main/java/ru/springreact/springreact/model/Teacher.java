package ru.springreact.springreact.model;
import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teacherId;

    private String firstname;
    private String patronymic;
    private String lastname;
    private Integer year;
    private String gender;
    private String subject;

    @OneToOne(mappedBy = "teacher")
    private SchoolClass schoolClass;

    public Teacher() {
    }

    public Teacher(String firstname, String patronymic, String lastname, Integer year, String gender, String subject) {
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.lastname = lastname;
        this.year = year;
        this.gender = gender;
        this.subject = subject;
    }
    
    public Long getId() {
        return teacherId;
    }

    public void setId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}