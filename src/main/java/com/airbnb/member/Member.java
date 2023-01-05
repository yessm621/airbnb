package com.airbnb.member;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String name;
    private LoginMethod loginMethod;
    private String profile;
    private LocalDate birthdate;
    private String phone;
    @Lob
    private String description;
    private LocalDateTime lastLoginDate;
    private Boolean superhost;
    private MemberStatus status;
    private MemberGrade grade;
}
