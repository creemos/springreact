package ru.springreact.springreact.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.springreact.springreact.model.Student;
import ru.springreact.springreact.repo.StudentRepository;

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

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable(value = "id") long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok().body(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = {"application/xml","application/json"})
    public Student saveStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") long id, @RequestBody Student student) {
        Student newStudent = studentRepository.findById(id).get();
        newStudent.setFirstname(student.getFirstname());
        newStudent.setPatronymic(student.getPatronymic());
        newStudent.setLastname(student.getLastname());
        newStudent.setGender(student.getGender());
        final Student updatedStudent = studentRepository.save(newStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping(value = "/{id}")
    void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

}
