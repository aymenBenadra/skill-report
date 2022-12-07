package com.sakamoto.skillreport.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User {

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @ToString.Exclude
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @ToString.Exclude
    private List<StudentSkill> skills;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;
        return getId() != null && Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
