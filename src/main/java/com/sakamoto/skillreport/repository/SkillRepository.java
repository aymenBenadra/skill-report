package com.sakamoto.skillreport.repository;

import com.sakamoto.skillreport.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Skill findByCode(String code);
}