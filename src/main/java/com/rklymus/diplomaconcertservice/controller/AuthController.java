package com.rklymus.diplomaconcertservice.controller;

import com.rklymus.diplomaconcertservice.dto.account.AuthRequest;
import com.rklymus.diplomaconcertservice.dto.account.RegistrationRequest;
import com.rklymus.diplomaconcertservice.service.AccountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "Authorization")
public class AuthController {
    private final AccountService accountService;

    @Autowired
    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid RegistrationRequest request) {
        accountService.createAccount(request);
    }

    @PostMapping("/signin")
    public String signin(@RequestBody AuthRequest request) {
        //todo: complete method
        return accountService.login(request);
    }
}