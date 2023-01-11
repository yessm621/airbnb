package com.airbnb.room.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class RoomHouseRule {

    @Id
    @GeneratedValue
    @Column(name = "room_house_rule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_rule_id")
    private HouseRule houseRule;
}
