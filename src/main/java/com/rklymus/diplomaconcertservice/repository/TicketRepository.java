package com.rklymus.diplomaconcertservice.repository;

import com.rklymus.diplomaconcertservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}
