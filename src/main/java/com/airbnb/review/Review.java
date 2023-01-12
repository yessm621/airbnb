package com.airbnb.review;

import com.airbnb.common.BaseEntity;
import com.airbnb.member.entity.Member;
import com.airbnb.room.entity.Room;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Lob
    private String content;
    @ColumnDefault("0")
    private int accuracy;
    @ColumnDefault("0")
    private int communication;
    @ColumnDefault("0")
    private int cleanliness;
    @ColumnDefault("0")
    private int location;
}
