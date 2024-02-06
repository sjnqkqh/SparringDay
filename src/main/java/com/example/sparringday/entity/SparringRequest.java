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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sparring_request")
public class SparringRequest extends EntityAuditor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User requester;

	@ManyToOne
	private User targetUser;

	@Column(name = "location")
	private String location;

	@Column(name = "sparring_datetime")
	private LocalDateTime sparringDatetime;

	@Enumerated(EnumType.STRING)
	@Column(name = "sparring_purpose")
	private SparringPurpose sparringPurpose;

	@Enumerated(EnumType.STRING)
	@Column(name = "sparring_intensity")
	private SparringIntensity sparringIntensity;

	@Column(name = "is_accepted")
	private boolean isAccepted;

	@Column(name = "accepted_at")
	private LocalDateTime acceptedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

}