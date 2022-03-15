package ru.springreact.springreact.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "classes")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long classId;

    private Long year;
    private String code;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    @OneToMany(mappedBy = "schoolClass", cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return this.students;
    }

    public SchoolClass() {
    }

    public SchoolClass(Long classId, Long year, String code, Teacher teacher, List<Student> students) {
        this.year = year;
        this.code = code;
        this.teacher = teacher;
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setSchoolClass(this);
    }

    public void addTeacher(Teacher teacher) {
       this.teacher = teacher;

    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public Long getId() {
        return this.classId;
    }

    public void setId(Long classId) {
        this.classId = classId;
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