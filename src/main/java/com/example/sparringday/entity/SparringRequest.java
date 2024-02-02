package com.example.sparringday.entity;

import java.time.LocalDateTime;

import com.example.sparringday.util.code.SparringIntensity;
import com.example.sparringday.util.code.SparringPurpose;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "sparring_request")
public class SparringRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "requester_id", nullable = false)
	private Long requesterId;

	@Column(name = "target_user_id", nullable = false)
	private Long targetUserId;

	@Column(name = "location", nullable = false)
	private String location;

	@Column(name = "sparring_datetime", nullable = false)
	private LocalDateTime sparringDatetime;

	@Enumerated(EnumType.STRING)
	@Column(name = "sparring_purpose", nullable = false)
	private SparringPurpose sparringPurpose;

	@Enumerated(EnumType.STRING)
	@Column(name = "sparring_intensity", nullable = false)
	private SparringIntensity sparringIntensity;

	@Column(name = "is_accepted", nullable = false)
	private boolean isAccepted;

	@Column(name = "accepted_at")
	private LocalDateTime acceptedAt;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

}