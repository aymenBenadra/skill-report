package com.sakamoto.skillreport.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends User {
    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @ToString.Exclude
    private List<Classroom> classrooms;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Instructor that = (Instructor) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
