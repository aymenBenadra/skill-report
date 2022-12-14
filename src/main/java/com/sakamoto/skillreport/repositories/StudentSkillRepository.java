package com.sakamoto.skillreport.repositories;

import com.sakamoto.skillreport.models.StudentSkill;
import com.sakamoto.skillreport.models.StudentSkillId;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface StudentSkillRepository extends Repository<StudentSkill, StudentSkillId> {
    Optional<StudentSkill> findById(StudentSkillId id);

    StudentSkill save(StudentSkill studentSkill);

    void delete(StudentSkill studentSkill);

    Long count();
}