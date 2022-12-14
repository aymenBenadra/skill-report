package com.sakamoto.skillreport.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSkillId implements Serializable {
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "skill_id")
    private Long skillId;
}
