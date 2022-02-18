package ru.springreact.springreact.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.springreact.springreact.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
