package ru.springreact.springreact.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.springreact.springreact.model.Student;
import ru.springreact.springreact.repo.StudentRepository;

import java.util.Optional;

@RestController
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

    @PostMapping
    public Student saveStudent(@Validated @RequestBody Student student) {
        return studentRepository.save(student);
    }
}
