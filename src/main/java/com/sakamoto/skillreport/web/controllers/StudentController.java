package com.sakamoto.skillreport.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakamoto.skillreport.services.StudentService;
import com.sakamoto.skillreport.web.dtos.CreateStudent;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@BasePathAwareController
public class StudentController {
    private final StudentService studentService;
    private final ObjectMapper objectMapper;

    public StudentController(StudentService studentService, ObjectMapper mapper) {
        this.studentService = studentService;
        this.objectMapper = mapper;
    }

    @PostMapping(value = "/students", consumes = "application/json", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<String> createStudent(@RequestBody CreateStudent createStudent) {
        try {
            return ResponseEntity.ok(objectMapper.writeValueAsString(
                    studentService.createStudent(createStudent)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
