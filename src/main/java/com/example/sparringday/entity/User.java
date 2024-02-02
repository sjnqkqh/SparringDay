package com.example.sparringday.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private Boolean isDeleted;

    private LocalDateTime deletedAt;

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", loginId='" + loginId + '\'' +
            ", encryptedPassword='" + encryptedPassword + '\'' +
            ", isDeleted=" + isDeleted +
            ", deletedAt=" + deletedAt +
            '}';
    }
}
