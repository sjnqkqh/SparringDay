package com.example.sparringday.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import com.example.sparringday.util.code.UserType;

@Entity
@Getter
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("is_deleted = false")
public class User extends EntityAuditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String encryptedPassword;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private Boolean isDeleted;

    private LocalDateTime deletedAt;
}
