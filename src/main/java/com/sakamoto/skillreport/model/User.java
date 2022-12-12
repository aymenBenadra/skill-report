package com.sakamoto.skillreport.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Column(unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @NonNull
    private String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    @ToString.Exclude
    private List<StudentSkill> skills;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    @ToString.Exclude
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
