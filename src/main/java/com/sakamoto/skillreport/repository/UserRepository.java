package com.sakamoto.skillreport.repository;

import com.sakamoto.skillreport.model.User;
import com.sakamoto.skillreport.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findAllByRole(UserRole role);
}