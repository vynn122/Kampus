package com.example.kampus.modules.post.entity;

import com.example.kampus.common.entity.BaseEntity;
import com.example.kampus.modules.school.entity.School;
import com.example.kampus.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post extends BaseEntity {



    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String imageUrl;

    private Boolean isAnonymous = false;

    private String anonymousAlias;

    private Integer upvoteCount = 0;

    private Integer commentCount = 0;

    private Boolean isDeleted = false;

    private Boolean isHidden = false;

    @Enumerated(EnumType.STRING)
    private PostCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;
}