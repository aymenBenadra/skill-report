package com.sakamoto.skillreport.service;

import com.google.common.base.Preconditions;
import com.sakamoto.skillreport.model.Skill;
import com.sakamoto.skillreport.model.StudentSkill;
import com.sakamoto.skillreport.model.StudentSkillId;
import com.sakamoto.skillreport.model.User;
import com.sakamoto.skillreport.repository.SkillRepository;
import com.sakamoto.skillreport.repository.StudentSkillRepository;
import com.sakamoto.skillreport.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentSkillService {
    private final StudentSkillRepository studentSkillRepository;
    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    @Autowired
    public StudentSkillService(StudentSkillRepository studentSkillRepository, SkillRepository skillRepository, UserRepository userRepository) {
        this.studentSkillRepository = studentSkillRepository;
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    public boolean addSkillToStudent(Long studentId, Long skillId) {
        try {
            User student = Preconditions.checkNotNull(userRepository.findById(studentId).orElse(null));
            Skill skill = Preconditions.checkNotNull(skillRepository.findById(skillId).orElse(null));

            StudentSkill studentSkill = new StudentSkill(student, skill, 1);
            return studentSkillRepository.save(studentSkill).getId() != null;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean upgradeStudentSkill(Long studentId, Long skillId) {
        return updateSkillLevel(studentId, skillId, 1);
    }

    public boolean downgradeStudentSkill(Long studentId, Long skillId) {
        return updateSkillLevel(studentId, skillId, -1);
    }

    private boolean updateSkillLevel(Long studentId, Long skillId, int level) {
        try {
            StudentSkill studentSkill = Preconditions.checkNotNull(studentSkillRepository
                    .findById(new StudentSkillId(studentId, skillId))
                    .orElse(null));

            studentSkill.setLevel(studentSkill.getLevel() + level);

            return studentSkillRepository.save(studentSkill).getId() != null;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
