package ru.springreact.springreact.model;

import javax.persistence.*;

@Entity
@Table(name = "students")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long student_id;

    private String firstname;
    private String patronymic;
    private String lastname;
    private String gender;

    @ManyToOne(fetch = FetchType.EAGER)
    private SchoolClass schoolClass;

    public void setSchoolClass(SchoolClass schoolClass) {
    }


}