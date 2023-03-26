package com.airbnb.mail.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MailSendDto {

    private String email;
    private String title;
    private String content;
}
