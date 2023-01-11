package com.airbnb.room.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class RoomFacilities {

    @Id
    @GeneratedValue
    @Column(name = "room_facilities_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facilities")
    private Facilities facilities;
}
