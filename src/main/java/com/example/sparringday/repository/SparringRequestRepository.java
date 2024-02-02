package com.example.sparringday.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sparringday.entity.SparringRequest;

public interface SparringRequestRepository extends JpaRepository<SparringRequest, Long> {
}
