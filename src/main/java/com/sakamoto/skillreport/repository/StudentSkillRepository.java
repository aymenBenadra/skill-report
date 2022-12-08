package com.sakamoto.skillreport.repository;

import com.sakamoto.skillreport.model.StudentSkill;
import com.sakamoto.skillreport.model.StudentSkillId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSkillRepository extends CrudRepository<StudentSkill, StudentSkillId> {
    @Modifying
    @Query("UPDATE StudentSkill ss SET ss.level = ss.level + 1 WHERE ss.id = ?1")
    void upgradeLevel(StudentSkillId id);

    @Modifying
    @Query("UPDATE StudentSkill ss SET ss.level = ss.level - 1 WHERE ss.id = ?1")
    void downgradeLevel(StudentSkillId id);
}