package com.sakamoto.skillreport.services;

import com.sakamoto.skillreport.models.StudentSkill;
import com.sakamoto.skillreport.models.StudentSkillId;
import com.sakamoto.skillreport.repositories.StudentSkillRepository;
import com.sakamoto.skillreport.web.dtos.StudentSkillLevel;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class StudentSkillService {
    private final StudentSkillRepository studentSkillRepository;
    private final ModelMapper mapper;

    @Autowired
    public StudentSkillService(StudentSkillRepository studentSkillRepository, ModelMapper mapper) {
        this.studentSkillRepository = studentSkillRepository;
        this.mapper = mapper;
    }

    private final PropertyMap<StudentSkillLevel, StudentSkillId> StudentSkillLevelMapping = new PropertyMap<>() {
        @Override
        protected void configure() {
            map().setStudentId(source.studentId());
            map().setSkillId(source.skillId());
        }
    };

    @Transactional
    public void upgradeStudentSkillLevel(StudentSkillLevel studentSkillLevel) {
        mapper.addMappings(StudentSkillLevelMapping);
        StudentSkillId studentSkillId = mapper.map(studentSkillLevel, StudentSkillId.class);

        StudentSkill studentSkill = studentSkillRepository.findById(studentSkillId)
                .orElseThrow(() -> new ResourceNotFoundException("Student " + studentSkillLevel.studentId() + " not found"));

        log.info("Student skill before upgrade: {}", studentSkill);
        studentSkill.setLevel(studentSkill.getLevel() + 1);
        log.info("Student skill after upgrade: {}", studentSkill);
    }

    @Transactional
    public void downgradeStudentSkillLevel(StudentSkillLevel studentSkillLevel) {
        StudentSkill studentSkill = studentSkillRepository.findById(new StudentSkillId(studentSkillLevel.studentId(), studentSkillLevel.skillId()))
                .orElseThrow(() -> new ResourceNotFoundException("Student Skill " + studentSkillLevel.studentId() + "-" + studentSkillLevel.skillId() + " not found"));

        log.info("Student skill before downgrade: {}", studentSkill);
        studentSkill.setLevel(studentSkill.getLevel() - 1);
        log.info("Student skill after downgrade: {}", studentSkill);
    }
}
