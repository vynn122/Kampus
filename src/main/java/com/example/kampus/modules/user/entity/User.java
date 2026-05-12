package com.example.kampus.modules.user.entity;

import com.example.kampus.common.entity.BaseEntity;
import com.example.kampus.modules.auth.enums.UserRole;
import com.example.kampus.modules.school.entity.Faculty;
import com.example.kampus.modules.school.entity.School;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {


    @Column(nullable = false, unique = true, length = 50)
    private String username;

    private String avatarUrl;

    private Integer yearLevel;

    private Integer reputationScore = 0;

    private Boolean isVerified = false;

    private Boolean isBanned = false;

    private Boolean isDeleted = false;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}