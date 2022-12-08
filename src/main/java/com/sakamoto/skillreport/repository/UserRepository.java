package com.sakamoto.skillreport.repository;

import com.sakamoto.skillreport.model.User;
import com.sakamoto.skillreport.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findAllByRole(UserRole role);
}