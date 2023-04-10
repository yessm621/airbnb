package com.airbnb.room.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomFacilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_facilities_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facilities")
    private Facilities facilities;

    public static RoomFacilities createRoomFacilities(Facilities facility) {
        RoomFacilities roomFacilities = new RoomFacilities();
        roomFacilities.setFacilities(facility);
        return roomFacilities;
    }
}
