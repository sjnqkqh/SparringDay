package com.example.sparringday.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "sparring_request")
public class SparringRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "requester_id")
	private Long requesterId;

	@Column(name = "target_user_id")
	private Long targetUserId;

	@Column(name = "location")
	private String location;

	@Column(name = "sparring_datetime")
	private LocalDateTime sparringDatetime;

	@Column(name = "is_accepted")
	private boolean isAccepted;

	@Column(name = "accepted_at")
	private LocalDateTime acceptedAt;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;
}
