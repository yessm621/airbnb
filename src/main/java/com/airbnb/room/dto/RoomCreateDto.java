package com.airbnb.room.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomCreateDto {

    private String title;
    private String description;

    private String country;
    private String city;

    private String zipcode;
    private String address1;
    private String address2;

    private int price;
    private int guest;
    private int bedroom;
    private int bed;
    private int bath;

    private LocalTime checkIn;
    private LocalTime checkOut;

    private Boolean instantBook;
    private String roomType;
}
