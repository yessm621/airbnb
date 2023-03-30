package com.airbnb.profile.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDto {

    private String email;
    private String name;
    private String description;
    private String phone;
    private LocalDate birth;
    private String image;
}
