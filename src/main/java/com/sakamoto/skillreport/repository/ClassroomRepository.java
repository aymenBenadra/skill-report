package com.sakamoto.skillreport.repository;

import com.sakamoto.skillreport.model.Classroom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends CrudRepository<Classroom, Long> {
    Classroom findByCode(String code);
}