package com.sakamoto.skillreport.services;

import com.sakamoto.skillreport.models.*;
import com.sakamoto.skillreport.repositories.ClassroomRepository;
import com.sakamoto.skillreport.repositories.StudentSkillRepository;
import com.sakamoto.skillreport.repositories.UserRepository;
import com.sakamoto.skillreport.web.dtos.CreateStudent;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentService {
    private final ModelMapper mapper;
    private final UserRepository studentRepository;
    private final ClassroomRepository classroomRepository;
    private final StudentSkillRepository studentSkillRepository;

    @Autowired
    public StudentService(UserRepository studentRepository, ClassroomRepository classroomRepository, StudentSkillRepository studentSkillRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
        this.studentSkillRepository = studentSkillRepository;
        this.mapper = mapper;
    }


    private final PropertyMap<CreateStudent, User> CreateStudentMapping = new PropertyMap<>() {
        @Override
        protected void configure() {
            map().setClassroom(classroomRepository
                    .findById(source.classroomId())
                    .orElseThrow(() -> new ResourceNotFoundException("Classroom " + source.classroomId() + " not found")));
            map().setRole(UserRole.ROLE_STUDENT);
        }
    };

    @Transactional
    public User createStudent(CreateStudent createStudent) {
        mapper.addMappings(CreateStudentMapping);

        User student = mapper.map(createStudent, User.class);
        log.info("Student created: {}", student);

        // Associate student with classroom reference skills and set level to 1 for each skill
        List<StudentSkill> studentSkills = new ArrayList<>();
        for (Reference reference : student.getClassroom().getReferences()) {
            studentSkills.addAll(reference.getSkills().stream()
                    .map(skill -> studentSkillRepository.save(new StudentSkill(student, skill, 1)))
                    .toList());
        }

        log.info("Student skills created: {}", studentSkills);
        student.setSkills(studentSkills);
        log.info("Student created with skills: {}", student);

        // Save student
        return studentRepository.save(student);
    }
}