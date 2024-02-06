package com.example.sparringday.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sparringday.entity.UserBoxingInfo;

public interface UserBoxingInfoRepository extends JpaRepository<UserBoxingInfo, Long> {
	boolean existsByUserId(Long userid);

	Optional<UserBoxingInfo> findByUserId(Long userId);
}
