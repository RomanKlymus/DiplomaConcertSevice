package com.rklymus.diplomaconcertservice.security;

import com.rklymus.diplomaconcertservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {


    private AccountService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return CustomUserDetails.create(userService.getAccountByEmail(email));
    }

    @Autowired
    public void setUserService(AccountService userService) {
        this.userService = userService;
    }
}