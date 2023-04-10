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
public class RoomHouseRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_house_rule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_rule_id")
    private HouseRule houseRule;

    public static RoomHouseRule createRoomHouseRule(HouseRule houseRule) {
        RoomHouseRule roomHouseRule = new RoomHouseRule();
        roomHouseRule.setHouseRule(houseRule);
        return roomHouseRule;
    }
}
