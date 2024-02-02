package com.example.sparringday.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sparringday.entity.SparringMatch;

public interface SparringMatchRepository extends JpaRepository<SparringMatch, Long> {
}
