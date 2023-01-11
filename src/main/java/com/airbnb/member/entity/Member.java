package com.airbnb.member.entity;

import com.airbnb.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String name;
    private LoginMethod loginMethod; // EMAIL, KAKAO, GITHUB
    private String profile;
    private LocalDate birthdate;
    private String phone;
    @Lob
    private String description;
    private LocalDateTime lastLoginDate;
    private Boolean superhost = false;
    private MemberStatus status; // ACTIVE, STOP
    private MemberGrade grade; // USER, HOST, ADMIN
}
