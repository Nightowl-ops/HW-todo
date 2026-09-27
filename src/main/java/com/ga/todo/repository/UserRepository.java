package com.ga.todo.repository;

import com.ga.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // for rgistration
    boolean existsByEmailAddress(String emailAddress);

    // for llogin
    User findUserByEmailAddress(String emailAddress);
}