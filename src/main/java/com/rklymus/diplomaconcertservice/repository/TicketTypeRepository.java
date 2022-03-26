package com.rklymus.diplomaconcertservice.repository;

import com.rklymus.diplomaconcertservice.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
}