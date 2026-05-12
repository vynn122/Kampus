package com.example.kampus.modules.school.entity;

import com.example.kampus.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "schools")
public class School extends BaseEntity {


    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String shortName;

    private String logoUrl;
}
