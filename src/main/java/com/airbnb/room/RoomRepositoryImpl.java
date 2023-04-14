package com.airbnb.room;

import com.airbnb.room.dto.QRoomDto;
import com.airbnb.room.dto.RoomDto;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.airbnb.member.entity.QMember.member;
import static com.airbnb.room.entity.QRoom.room;


public class RoomRepositoryImpl implements RoomRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public RoomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<RoomDto> roomList(Pageable pageable) {
        List<RoomDto> rooms = queryFactory
                .select(new QRoomDto(
                        room.id,
                        room.title,
                        room.country,
                        room.city,
                        member.superHost,
                        room.thumbnail
                ))
                .from(room)
                .leftJoin(room.member, member)
                .where()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(room.count())
                .from(room)
                .leftJoin(room.member, member)
                .where();

        return PageableExecutionUtils.getPage(rooms, pageable, countQuery::fetchOne);
    }
}
