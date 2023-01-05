package com.airbnb.room;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class RoomAmenities {

    @Id
    @GeneratedValue
    @Column(name = "room_amenities_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenities_id")
    private Amenities amenities;
}
