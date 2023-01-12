package com.airbnb.room.entity;

import com.airbnb.common.BaseEntity;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(length = 1000)
    private String title;

    @Lob
    private String description;
    @Lob
    private String thumbnail;
    private String country;
    private String city;

    @Column(length = 1000)
    private String address;
    @ColumnDefault("0")
    private int price;
    @ColumnDefault("1")
    private int guest;
    @ColumnDefault("1")
    private int bedroom;
    @ColumnDefault("0")
    private int bed;
    @ColumnDefault("0")
    private int bath;

    private LocalTime checkIn;
    private LocalTime checkOut;

    @Column(columnDefinition="tinyint(1) default 1")
    private Boolean instantBook;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20)
    private RoomType type; // PENSION, PRIVATE_ROOM, GUEST_HOUSE, HOTEL

    @Column(columnDefinition="tinyint(1) default 0")
    private Boolean deleteYn;
}
