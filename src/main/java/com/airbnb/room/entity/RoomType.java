package com.airbnb.room.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoomType {
    PENSION("펜션"), PRIVATE_ROOM("독채"), GUEST_HOUSE("게스트하우스"), HOTEL("호텔");

    private final String name;
}
