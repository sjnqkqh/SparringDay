package com.example.sparringday.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sparringday.entity.SparringReview;

public interface SparringReviewRepository extends JpaRepository<SparringReview, Long> {
}
