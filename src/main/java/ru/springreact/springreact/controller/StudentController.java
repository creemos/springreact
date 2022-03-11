package ru.springreact.springreact.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.springreact.springreact.model.Student;
import ru.springreact.springreact.repo.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public Iterable<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> findStudentById(@PathVariable(value = "studentId") long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            return ResponseEntity.ok().body(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/available_students")
    public List<Student> findAvailableStudents(){
        return studentRepository.findBySchoolClass_ClassIdNotNull();
    }

    @PostMapping(consumes = {"application/xml","application/json"})
    public Student saveStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "studentId") Long studentId, @RequestBody Student student) {
        Student newStudent = studentRepository.findById(studentId).get();
        newStudent.setFirstname(student.getFirstname());
        newStudent.setPatronymic(student.getPatronymic());
        newStudent.setLastname(student.getLastname());
        newStudent.setGender(student.getGender());
        final Student updatedStudent = studentRepository.save(newStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping(value = "/{studentId}")
    void deleteStudent(@PathVariable Long studentId) {
        studentRepository.deleteById(studentId);
    }

}
