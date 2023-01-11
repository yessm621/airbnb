package com.airbnb.room.entity;

import com.airbnb.common.BaseEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Facilities extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "facilities_id")
    private Long id;
    private String name;
}
