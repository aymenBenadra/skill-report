package com.sakamoto.skillreport.repository;

import com.sakamoto.skillreport.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {
    Reference findByCode(String code);
}