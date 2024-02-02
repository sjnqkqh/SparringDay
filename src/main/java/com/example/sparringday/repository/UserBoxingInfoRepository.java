package com.example.sparringday.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sparringday.entity.UserBoxingInfo;

public interface UserBoxingInfoRepository extends JpaRepository<UserBoxingInfo, Long> {
}
