package com.rklymus.diplomaconcertservice.repository;

import com.rklymus.diplomaconcertservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    boolean existsAccountByEmail(String email);
}
