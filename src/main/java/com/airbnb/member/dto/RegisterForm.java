package com.airbnb.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterForm {

    private String email;
    private String password;
    private String passwordConfirm;
    private String name;
}
