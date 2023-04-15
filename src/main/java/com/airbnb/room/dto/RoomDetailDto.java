package com.airbnb.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
@Builder
public class RoomDetailDto {
    private Long roomId;
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
    private String roomType;
    private Boolean instantBook;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkIn;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkOut;

    private String hostProfile;
    private String hostname;
}
