package ru.springreact.springreact.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.springreact.springreact.model.SchoolClass;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    
    Iterable<SchoolClass> findByTeacherIsNotNull();
    SchoolClass findByTeacher_TeacherId(long teacherId); 

}
