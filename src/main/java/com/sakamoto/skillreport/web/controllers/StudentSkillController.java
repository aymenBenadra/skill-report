package com.sakamoto.skillreport.web.controllers;

import com.sakamoto.skillreport.services.StudentSkillService;
import com.sakamoto.skillreport.web.dtos.StudentSkillLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RepositoryRestController
public class StudentSkillController {

    private final StudentSkillService studentSkillService;

    @Autowired
    public StudentSkillController(StudentSkillService studentSkillService) {
        this.studentSkillService = studentSkillService;
    }

    @GetMapping("/users/{studentId}/upgrade-skill/{skillId}")
    public ResponseEntity<String> upgradeStudentSkillLevel(@PathVariable Long studentId, @PathVariable Long skillId) {
        // TODO: Get student from Authentication object
        studentSkillService.upgradeStudentSkillLevel(new StudentSkillLevel(studentId, skillId));
        return ResponseEntity.ok("Student skill level upgraded!");
    }

    @GetMapping("/users/{studentId}/downgrade-skill/{skillId}")
    public ResponseEntity<String> downgradeStudentSkillLevel(@PathVariable Long studentId, @PathVariable Long skillId) {
        // TODO: Get student from Authentication object
        studentSkillService.downgradeStudentSkillLevel(new StudentSkillLevel(studentId, skillId));
        return ResponseEntity.ok("Student skill level downgraded!");
    }
}
