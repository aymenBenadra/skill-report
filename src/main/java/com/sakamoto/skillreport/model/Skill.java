package com.sakamoto.skillreport.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "skills")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "reference_id")
    private Reference reference;

    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    @ToString.Exclude
    private List<StudentSkill> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Skill skill = (Skill) o;
        return id != null && Objects.equals(id, skill.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
