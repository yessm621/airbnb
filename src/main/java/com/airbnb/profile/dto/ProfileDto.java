package com.airbnb.profile.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProfileDto {
    private String name;
    private String description;
    private String profile;
}
