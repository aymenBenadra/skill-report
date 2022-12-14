package com.sakamoto.skillreport.repositories;

import com.sakamoto.skillreport.models.User;
import com.sakamoto.skillreport.models.UserRole;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    List<User> findAllByRole(UserRole role);

    User save(User user);

    boolean existsByEmail(String email);

    Long count();
}