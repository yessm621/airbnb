package com.airbnb.room.entity;

import com.airbnb.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Facilities extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facilities_id")
    private Long id;
    private String name;
}
