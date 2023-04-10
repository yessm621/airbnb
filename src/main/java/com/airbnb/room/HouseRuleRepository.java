package com.airbnb.room;

import com.airbnb.room.entity.HouseRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseRuleRepository extends JpaRepository<HouseRule, Long> {


    @Query("select h from HouseRule h where h.id in :houseRule")
    List<HouseRule> findHouseRuleIn(@Param("houseRule") Long[] houseRule);
}
