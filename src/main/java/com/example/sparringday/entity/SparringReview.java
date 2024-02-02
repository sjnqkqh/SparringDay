package com.example.sparringday.entity;
import java.time.LocalDateTime;

import com.example.sparringday.util.code.EvaluateCode;

import jakarta.persistence.*;

@Entity
@Table(name = "sparring_review")
public class SparringReview  extends EntityAuditor{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "match_id")
	private Long matchId;

	@Column(name = "reviewer_id")
	private Long reviewerId;

	@Column(name = "review_target_id")
	private Long reviewTargetId;

	@Enumerated(EnumType.STRING)
	@Column(name = "sparring_manner")
	private EvaluateCode sparringManner;

	@Enumerated(EnumType.STRING)
	@Column(name = "time_manner")
	private EvaluateCode timeManner;

	@Enumerated(EnumType.STRING)
	@Column(name = "skill_level")
	private EvaluateCode skillLevel;

	@Column(name = "review_text", columnDefinition = "TEXT")
	private String reviewText;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

}
