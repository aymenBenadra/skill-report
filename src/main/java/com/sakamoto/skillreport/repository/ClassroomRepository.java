package com.sakamoto.skillreport.repository;

import com.sakamoto.skillreport.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Classroom findByCode(String code);
}