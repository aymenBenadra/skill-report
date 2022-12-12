package com.sakamoto.skillreport.service;

import com.sakamoto.skillreport.model.Reference;
import com.sakamoto.skillreport.model.Skill;
import com.sakamoto.skillreport.repository.ReferenceRepository;
import com.sakamoto.skillreport.repository.SkillRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SkillService {
    private final SkillRepository skillRepository;
    private final ReferenceRepository referenceRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository, ReferenceRepository referenceRepository) {
        this.skillRepository = skillRepository;
        this.referenceRepository = referenceRepository;
    }

    public boolean createSkill(String name, String code, Long referenceId) {
        Reference reference = referenceRepository.findById(referenceId).orElse(null);
        if (reference == null) {
            return false;
        }
        Skill skill = new Skill(name, code, reference);
        return skillRepository.save(skill).getId() != null;
    }

    public boolean updateSkillById(Long skillId, String name, String code) {
        Skill skill = skillRepository.findById(skillId).orElse(null);
        if (skill == null) {
            return false;
        }
        skill.setName(name);
        skill.setCode(code);
        return skillRepository.save(skill).getId() != null;
    }

    public boolean deleteSkillById(Long skillId) {
        Skill skill = skillRepository.findById(skillId).orElse(null);
        if (skill == null) {
            return false;
        }
        skillRepository.delete(skill);
        return true;
    }
}
