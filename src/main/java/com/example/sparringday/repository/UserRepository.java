package com.example.sparringday.repository;


import com.example.sparringday.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLoginIdAndIsDeleted(String loginId, Boolean isDeleted);

    boolean existsByLoginIdAndIsDeleted(String loginId, Boolean isDeleted);
}
