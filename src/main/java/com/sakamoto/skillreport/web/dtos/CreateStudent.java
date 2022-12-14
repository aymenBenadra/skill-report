package com.sakamoto.skillreport.web.dtos;

public record CreateStudent(String name, String email, String password, Long classroomId) {
}
