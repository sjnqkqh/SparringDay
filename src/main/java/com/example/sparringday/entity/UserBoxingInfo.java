package com.example.sparringday.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;

import com.example.sparringday.dto.boxinginfo.CreateBoxingInfoReqDto;
import com.example.sparringday.util.code.BoxingStyle;
import com.example.sparringday.util.code.FrontHand;
import com.example.sparringday.util.code.SparringIntensity;
import com.example.sparringday.util.code.SparringPurpose;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Table(name = "user_boxing_info")
public class UserBoxingInfo  extends EntityAuditor{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "year_of_experience")
	private Integer yearOfExperience;

	@Column(name = "numberOfSparring")
	private Integer numberOfSparring;

	@Enumerated(EnumType.STRING)
	@Column(name = "style")
	private BoxingStyle style;

	@Enumerated(EnumType.STRING)
	@Column(name = "front_hand")
	private FrontHand frontHand;

	@Enumerated(EnumType.STRING)
	@Column(name = "sparring_purpose")
	private SparringPurpose sparringPurpose;

	@Enumerated(EnumType.STRING)
	@Column(name = "sparring_intensity")
	private SparringIntensity sparringIntensity;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	public static UserBoxingInfo fromUserAndDto(User user, CreateBoxingInfoReqDto reqDto) {
		return UserBoxingInfo.builder()
			.userId(user.getId())
			.yearOfExperience(reqDto.yearOfExperience())
			.numberOfSparring(reqDto.numberOfSparring())
			.style(reqDto.style())
			.frontHand(reqDto.frontHand())
			.sparringPurpose(reqDto.sparringPurpose())
			.sparringIntensity(reqDto.sparringIntensity())
			.build();
	}

	public void updateFromDto(CreateBoxingInfoReqDto reqDto) {
		yearOfExperience = reqDto.yearOfExperience();
		numberOfSparring = reqDto.numberOfSparring();
		style = reqDto.style();
		frontHand = reqDto.frontHand();
		sparringPurpose = reqDto.sparringPurpose();
		sparringIntensity = reqDto.sparringIntensity();
	}
}
