package ru.springreact.springreact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @PostMapping(consumes = {"application/xml","application/json"})
    public SchoolClass saveSchoolClass(@RequestBody SchoolClass schoolClass){
        return schoolClassRepository.save(schoolClass);
    }
/*
    @PutMapping("/{class_id}")
    public ResponseEntity<SchoolClass> updateSchoolClass(@PathVariable(value = "class_id") Long class_id, @RequestBody SchoolClass schoolClass) {
        SchoolClass newSchoolClass = schoolClassRepository.findById(class_id).get();
        newSchoolClass.setCode(schoolClass.getCode());
        newSchoolClass.setYear(schoolClass.getYear());
        newSchoolClass.setTeacher(schoolClass.getTeacher());
        newSchoolClass.setStudents(schoolClass.getStudents());
        final SchoolClass updatedSchoolClass = schoolClassRepository.save(newSchoolClass);
        return ResponseEntity.ok(updatedSchoolClass);
    }
*/
    @PutMapping("/{class_id}/addstudent")
    public ResponseEntity<SchoolClass> addStudent(@PathVariable(value = "class_id") Long class_id, @RequestBody Student student) {
        SchoolClass newSchoolClass = schoolClassRepository.findById(class_id).get();
        newSchoolClass.addStudent(student);
        final SchoolClass updatedSchoolClass = schoolClassRepository.save(newSchoolClass);
        return ResponseEntity.ok(updatedSchoolClass);
    }

    @PutMapping("/{class_id}/addteacher")
    public ResponseEntity<SchoolClass> addTeacher(@PathVariable(value = "class_id") Long class_id, @RequestBody Teacher teacher) {
        SchoolClass newSchoolClass = schoolClassRepository.findById(class_id).get();
        newSchoolClass.addTeacher(teacher);
        final SchoolClass updatedSchoolClass = schoolClassRepository.save(newSchoolClass);
        return ResponseEntity.ok(updatedSchoolClass);
    }
}
