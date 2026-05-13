package com.example.kampus.modules.auth.entity;

import com.example.kampus.common.entity.BaseEntity;
import com.example.kampus.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_sessions")
public class UserSession extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String refreshTokenHash;

    private String deviceName;

    private String deviceOs;

    private String ipAddress;

    private Boolean isRevoked = false;

    private LocalDateTime expiresAt;

    private LocalDateTime lastUsedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}