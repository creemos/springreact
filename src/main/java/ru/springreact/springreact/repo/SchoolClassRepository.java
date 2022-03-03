package ru.springreact.springreact.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.springreact.springreact.model.SchoolClass;

@Repository
public interface SchoolClassRepository extends CrudRepository<SchoolClass, Long> {
    
}
