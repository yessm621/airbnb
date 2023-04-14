package com.airbnb.room.entity;

import com.airbnb.common.BaseEntity;
import com.airbnb.member.entity.Member;
import com.airbnb.room.dto.RoomCreateRequestDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 1000)
    private String title;

    @Lob
    private String description;
    @Lob
    private String thumbnail;
    private String country;
    private String city;

    @Embedded
    private Address address;
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

    @Column(columnDefinition = "tinyint(1) default 1")
    private Boolean instantBook;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20)
    private RoomType type; // PENSION, PRIVATE_ROOM, GUEST_HOUSE, HOTEL

    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean deleteYn;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomAmenities> roomAmenities = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomFacilities> roomFacilities = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomHouseRule> roomHouseRules = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    public void addRoomAmenities(RoomAmenities roomAmenity) {
        roomAmenities.add(roomAmenity);
        roomAmenity.setRoom(this);
    }

    public void addRoomFacilities(RoomFacilities roomFacility) {
        roomFacilities.add(roomFacility);
        roomFacility.setRoom(this);
    }

    public void addRoomHouseRules(RoomHouseRule roomHouseRule) {
        roomHouseRules.add(roomHouseRule);
        roomHouseRule.setRoom(this);
    }

    public static Room createRoom(Address address, Member member, RoomCreateRequestDto dto,
                                  List<RoomAmenities> roomAmenities, List<RoomFacilities> roomFacilities, List<RoomHouseRule> houseRules) {
        Room room = new Room();
        room.setMember(member);
        room.setTitle(dto.getTitle());
        room.setDescription(dto.getDescription());
        room.setCountry(dto.getCountry());
        room.setCity(dto.getCity());
        room.setAddress(address);
        room.setPrice(dto.getPrice());
        room.setGuest(dto.getGuest());
        room.setBedroom(dto.getBedroom());
        room.setBed(dto.getBed());
        room.setBath(dto.getBath());
        room.setCheckIn(dto.getCheckIn());
        room.setCheckOut(dto.getCheckOut());
        room.setInstantBook(dto.getInstantBook());
        room.setType(RoomType.valueOf(dto.getRoomType()));
        for (RoomAmenities roomAmenity : roomAmenities) {
            room.addRoomAmenities(roomAmenity);
        }
        for (RoomFacilities roomFacility : roomFacilities) {
            room.addRoomFacilities(roomFacility);
        }
        for (RoomHouseRule houseRule : houseRules) {
            room.addRoomHouseRules(houseRule);
        }
        return room;
    }
}
