package com.example.sparringday.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "sparring_match")
public class SparringMatch  extends EntityAuditor{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "is_finished")
	private boolean isFinished;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

}
