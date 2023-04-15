package com.airbnb.houseRule;

import com.airbnb.houseRule.dto.HouseRuleDto;
import com.airbnb.room.entity.RoomHouseRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HouseRuleService {

    private final EntityManager em;

    public List<HouseRuleDto> findHouseRules(Long roomId) {

        List<RoomHouseRule> roomHouseRules = em.createQuery(
                        "select rh from RoomHouseRule rh" +
                                " join fetch rh.houseRule h" +
                                " where rh.room.id=:roomId", RoomHouseRule.class)
                .setParameter("roomId", roomId)
                .getResultList();

        List<HouseRuleDto> houseRules = roomHouseRules
                .stream().map(h -> new HouseRuleDto(h.getHouseRule().getName()))
                .collect(Collectors.toList());

        return houseRules;
    }
}
