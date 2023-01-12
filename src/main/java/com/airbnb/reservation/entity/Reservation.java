package com.airbnb.reservation.entity;

import com.airbnb.common.BaseEntity;
import com.airbnb.member.entity.Member;
import com.airbnb.room.entity.Room;
import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 10)
    private ReservationStatus status; // PENDING, PAID, CANCELED, CONFIRMED

    @ColumnDefault("0")
    private int price;
}
