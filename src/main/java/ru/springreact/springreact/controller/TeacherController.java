package ru.springreact.springreact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.springreact.springreact.model.Teacher;
import ru.springreact.springreact.repo.TeacherRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping
    public Iterable<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> findTeacherById(@PathVariable(value = "id") long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            return ResponseEntity.ok().body(teacher.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Teacher saveTeacher(@Validated @RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

}
