package com.sakamoto.skillreport.repositories;

import com.sakamoto.skillreport.models.Classroom;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ClassroomRepository extends Repository<Classroom, Long> {
    Classroom save(Classroom classroom);

    Optional<Classroom> findById(Long id);

    Long count();
}