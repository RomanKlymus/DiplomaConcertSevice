package com.rklymus.diplomaconcertservice.repository;

import com.rklymus.diplomaconcertservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
