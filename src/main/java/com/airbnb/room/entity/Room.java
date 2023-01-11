package com.airbnb.room.entity;

import com.airbnb.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
public class Room extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private Long id;
    private String title;
    @Lob
    private String description;
    @Lob
    private String thumbnail;
    private String country;
    private String city;
    private String address;
    private int price;
    private int guest;
    private int bedroom;
    private int bed;
    private int bath;

    private LocalTime checkIn;
    private LocalTime checkOut;
    private Boolean instantBook = true;
    private RoomType type; // PENSION, PRIVATE_ROOM, GUEST_HOUSE, HOTEL
    private Boolean delete = false;
}
