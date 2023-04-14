package com.airbnb.room;

import com.airbnb.room.dto.RoomDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomRepositoryCustom {

    Page<RoomDto> roomList(Pageable pageable);
}
