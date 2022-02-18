package ru.springreact.springreact.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.springreact.springreact.model.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}
