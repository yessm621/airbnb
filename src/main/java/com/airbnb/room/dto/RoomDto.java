package com.airbnb.room.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class RoomDto {

    private Long roomId;
    private String title;
    private String country;
    private String city;
    private Boolean superHost;
    private String filePath;

    @QueryProjection
    public RoomDto(Long roomId, String title, String country, String city, Boolean superHost, String filePath) {
        this.roomId = roomId;
        this.title = title;
        this.country = country;
        this.city = city;
        this.superHost = superHost;
        this.filePath = filePath;
    }
}
