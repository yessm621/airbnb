package com.airbnb.room.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Facilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facilities_id")
    private Long id;
    private String name;
}
