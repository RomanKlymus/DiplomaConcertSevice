package com.rklymus.diplomaconcertservice.dto.account;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
