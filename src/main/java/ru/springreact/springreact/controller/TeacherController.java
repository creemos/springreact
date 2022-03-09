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
    public ResponseEntity<Teacher> findTeacherById(@PathVariable(value = "id") long teacher_id) {
        Optional<Teacher> teacher = teacherRepository.findById(teacher_id);
        if (teacher.isPresent()) {
            return ResponseEntity.ok().body(teacher.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/available")
    public Iterable<Teacher> findAvailableTeachers() {
        return teacherRepository.findByTeacherIdIsNull();
    }

    @PostMapping(consumes = {"application/xml","application/json"})
    public Teacher saveStudent(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @DeleteMapping(value = "/{teacher_id}")
    void deleteTeacher(@PathVariable Long teacher_id) {
        teacherRepository.deleteById(teacher_id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable(value = "id") long teacher_id, @RequestBody Teacher teacher) {
        Teacher newTeacher = teacherRepository.findById(teacher_id).get();
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
