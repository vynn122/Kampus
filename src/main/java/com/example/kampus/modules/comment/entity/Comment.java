package com.example.kampus.modules.comment.entity;

import com.example.kampus.common.entity.BaseEntity;
import com.example.kampus.modules.post.entity.Post;
import com.example.kampus.modules.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {



    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private Boolean isAnonymous = false;

    private String anonymousAlias;

    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}