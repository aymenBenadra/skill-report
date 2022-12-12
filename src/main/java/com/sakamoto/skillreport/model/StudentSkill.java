package com.sakamoto.skillreport.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "student_skills")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class StudentSkill {
    @EmbeddedId
    private StudentSkillId id;

    @NonNull
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private User student;

    @NonNull
    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @NonNull
    @Min(value = 1, message = "The value must be between 1 and 3")
    @Max(value = 3, message = "The value must be between 1 and 3")
    private Integer level;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentSkill that = (StudentSkill) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
