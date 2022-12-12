package com.sakamoto.skillreport.service;

import com.google.common.base.Preconditions;
import com.sakamoto.skillreport.model.*;
import com.sakamoto.skillreport.repository.SkillRepository;
import com.sakamoto.skillreport.repository.StudentSkillRepository;
import com.sakamoto.skillreport.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentService {
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final StudentSkillRepository studentSkillRepository;

    public StudentService(UserRepository userRepository, SkillRepository skillRepository, StudentSkillRepository studentSkillRepository) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.studentSkillRepository = studentSkillRepository;
    }

    public User getStudentById(Long studentId) {
        return userRepository.findById(studentId).orElse(null);
    }

    public boolean createStudent(String name, String email, String password, UserRole role) {
        User student = new User(name, email, password, role);
        return userRepository.save(student).getId() != null;
    }

    public boolean deleteUserById(Long studentId) {
        userRepository.deleteById(studentId);
        return true;
    }
}
