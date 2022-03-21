package ru.springreact.springreact.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.springreact.springreact.model.SchoolClass;
import ru.springreact.springreact.model.Student;
import ru.springreact.springreact.model.Teacher;
import ru.springreact.springreact.repo.SchoolClassRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/classes")
public class SchoolClassController {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @GetMapping
    public Iterable<SchoolClass> findAllClaasses() {
        return schoolClassRepository.findAll();
    }

    @GetMapping("/with_teachers")
    public Iterable<SchoolClass> findByTeacherIsNotNull() {
        return schoolClassRepository.findByTeacherIsNotNull();
    };

    @GetMapping("/{classId}")
    public ResponseEntity<SchoolClass> findStudentById(@PathVariable(value = "classId") long classId) {
        Optional<SchoolClass> schoolClass = schoolClassRepository.findById(classId);
        if (schoolClass.isPresent()) {
            return ResponseEntity.ok().body(schoolClass.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = { "application/xml", "application/json" })
    public SchoolClass saveSchoolClass(@RequestBody SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

    @PutMapping("/{classId}")
    public ResponseEntity<SchoolClass> updateSchoolClass(@PathVariable(value = "classId") Long classId,
            @RequestBody SchoolClass schoolClass) {
        SchoolClass newSchoolClass = schoolClassRepository.findById(classId).get();
        newSchoolClass.setCode(schoolClass.getCode());
        newSchoolClass.setYear(schoolClass.getYear());
        newSchoolClass.setTeacher(schoolClass.getTeacher());
        final SchoolClass updatedSchoolClass = schoolClassRepository.save(newSchoolClass);
        return ResponseEntity.ok(updatedSchoolClass);
    }

    @PutMapping("/{classId}/addstudent")
    public ResponseEntity<SchoolClass> addStudent(@PathVariable(value = "classId") Long classId,
            @RequestBody Student student) {
        SchoolClass newSchoolClass = schoolClassRepository.findById(classId).get();
        newSchoolClass.addStudent(student);
        final SchoolClass updatedSchoolClass = schoolClassRepository.save(newSchoolClass);
        return ResponseEntity.ok(updatedSchoolClass);
    }

    @PutMapping("/{classId}/addteacher")
    public ResponseEntity<SchoolClass> addTeacher(@PathVariable(value = "classId") Long classId,
            @RequestBody Teacher teacher) {
        SchoolClass newSchoolClass = schoolClassRepository.findById(classId).get();
        newSchoolClass.addTeacher(teacher);
        final SchoolClass updatedSchoolClass = schoolClassRepository.save(newSchoolClass);
        return ResponseEntity.ok(updatedSchoolClass);
    }

    @PutMapping("/{classId}/deletestudent")
    public ResponseEntity<SchoolClass> deleteStudentFromClass(@PathVariable(value = "classId") Long classId,
            @RequestBody List<Student> students) {
        SchoolClass newSchoolClass = schoolClassRepository.findById(classId).get();
        newSchoolClass.setStudents(students);
        final SchoolClass updatedSchoolClass = schoolClassRepository.save(newSchoolClass);
        return ResponseEntity.ok(updatedSchoolClass);
    }

    @PutMapping("/{classId}/clearteacher")
    void clearTeacher(@PathVariable long classId) {
        SchoolClass schoolClass = schoolClassRepository.findById(classId).get();
        schoolClass.setTeacher(null);
        schoolClassRepository.save(schoolClass);
    }

    @PutMapping("/find_relation")
    void findRelation(@RequestBody long teacherId) {
        SchoolClass schoolClass = schoolClassRepository.findByTeacher_TeacherId(teacherId);
        if (schoolClass != null) {
            schoolClass.setTeacher(null);
            schoolClassRepository.save(schoolClass);
        }
    }

    @DeleteMapping(value = "/{classId}")
    void deleteSchoolClass(@PathVariable Long classId) {
        SchoolClass schoolClass = schoolClassRepository.findById(classId).get();
        schoolClass.setTeacher(null);
        schoolClass.getStudents().forEach(student -> {
            student.setSchoolClass(null);
        });
        schoolClassRepository.deleteById(classId);
    }
}
