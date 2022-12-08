package com.sakamoto.skillreport.repository;

import com.sakamoto.skillreport.model.Reference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRepository extends CrudRepository<Reference, Long> {
    Reference findByCode(String code);
}