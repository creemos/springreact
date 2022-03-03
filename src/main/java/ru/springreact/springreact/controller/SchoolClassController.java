package ru.springreact.springreact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.springreact.springreact.model.SchoolClass;
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
}
