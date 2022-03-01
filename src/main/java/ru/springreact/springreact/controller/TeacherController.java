package ru.springreact.springreact.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.springreact.springreact.model.Teacher;
import ru.springreact.springreact.repo.TeacherRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/teachers")
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

    @PostMapping(consumes = {"application/xml","application/json"})
    public Teacher saveStudent(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @DeleteMapping(value = "/{id}")
    void deleteTeacher(@PathVariable Long id) {
        teacherRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable(value = "id") long id, @RequestBody Teacher teacher) {
        Teacher newTeacher = teacherRepository.findById(id).get();
        newTeacher.setFirstname(teacher.getFirstname());
        newTeacher.setPatronymic(teacher.getPatronymic());
        newTeacher.setLastname(teacher.getLastname());
        newTeacher.setGender(teacher.getGender());
        newTeacher.setYear(teacher.getYear());
        newTeacher.setSubject(teacher.getSubject());
        final Teacher updatedTeacher = teacherRepository.save(newTeacher);
        return ResponseEntity.ok(updatedTeacher);
    }
}
