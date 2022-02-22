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

    @DeleteMapping(value = "/{id}")
    void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

}
