package com.airbnb.room;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
public class Room {
    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private Long id;
    private String title;
    @Lob
    private String description;
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
    private Boolean instantBook;
    private RoomType type;
    private Boolean delete;
}
