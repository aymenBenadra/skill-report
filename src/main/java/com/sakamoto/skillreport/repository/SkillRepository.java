package com.sakamoto.skillreport.repository;

import com.sakamoto.skillreport.model.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {
    Skill findByCode(String code);
}