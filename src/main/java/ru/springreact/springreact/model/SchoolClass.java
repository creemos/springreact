package ru.springreact.springreact.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "classes")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long class_id;

    private Long year;
    private String code;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Teacher teacher;

    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();


    public List<Student> getStudents() {
        return this.students;
    }

    public SchoolClass() {
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setSchoolClass(this);
    }


    public SchoolClass(Long class_id, Long year, String code, Teacher teacher, List<Student> students) {
        this.class_id = class_id;
        this.year = year;
        this.code = code;
        this.teacher = teacher;
        this.students = students;
    }
    
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public Long getId() {
        return this.class_id;
    }

    public void setId(Long class_id) {
        this.class_id = class_id;
    }

    public Long getYear() {
        return this.year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}