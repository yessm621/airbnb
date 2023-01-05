package com.airbnb.room;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Facilities {

    @Id
    @GeneratedValue
    @Column(name = "facilities_id")
    private Long id;
    private String name;
}
