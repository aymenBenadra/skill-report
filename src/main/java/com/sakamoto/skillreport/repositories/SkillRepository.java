package com.sakamoto.skillreport.repositories;

import com.sakamoto.skillreport.models.Skill;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface SkillRepository extends Repository<Skill, Long> {
    Skill save(Skill skill);

    Optional<Skill> findById(Long id);

    Long count();
}