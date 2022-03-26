package com.rklymus.diplomaconcertservice.service;

import com.rklymus.diplomaconcertservice.dto.account.AuthRequest;
import com.rklymus.diplomaconcertservice.dto.account.RegistrationRequest;
import com.rklymus.diplomaconcertservice.entity.Account;

public interface AccountService {
    Account getAccountById(Long id);

    Account getAccountByEmail(String email);

    void createAccount(RegistrationRequest request);

    String login(AuthRequest request);
}
