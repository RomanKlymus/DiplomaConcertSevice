package com.rklymus.diplomaconcertservice.repository;

import com.rklymus.diplomaconcertservice.entity.Role;
import com.rklymus.diplomaconcertservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
