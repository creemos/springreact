package ru.springreact.springreact.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "students")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;

    private String firstname;
    private String patronymic;
    private String lastname;
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classId")
    @JsonIgnore
    private SchoolClass schoolClass;

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public Student() {
    }

    public Student(String firstname, String patronymic, String lastname, String gender) {
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.lastname = lastname;
        this.gender = gender;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Long getstudentId() {
        return studentId;
    }

    public void setstudentId(Long studentId) {
        this.studentId = studentId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
