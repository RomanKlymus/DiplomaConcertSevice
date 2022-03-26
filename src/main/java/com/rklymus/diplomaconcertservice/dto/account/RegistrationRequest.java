package com.rklymus.diplomaconcertservice.dto.account;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String email;
    private String password;
    private String passwordConfirm;
    private String phoneNumber;
}
