package com.example.kampus.modules.auth.entity;

import com.example.kampus.common.entity.BaseEntity;
import com.example.kampus.modules.auth.enums.AuthProviderType;
import com.example.kampus.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "auth_credentials")
public class AuthCredential extends BaseEntity {


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProviderType providerType;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String passwordHash;

    @Column(nullable = false, unique = true)
    private String providerId;

    private Boolean isPrimary = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}