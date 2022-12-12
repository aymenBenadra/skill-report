package com.sakamoto.skillreport.repository;

import com.sakamoto.skillreport.model.StudentSkill;
import com.sakamoto.skillreport.model.StudentSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentSkillRepository extends JpaRepository<StudentSkill, StudentSkillId> {
}