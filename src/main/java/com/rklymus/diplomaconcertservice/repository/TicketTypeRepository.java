package com.rklymus.diplomaconcertservice.repository;

import com.rklymus.diplomaconcertservice.entity.Event;
import com.rklymus.diplomaconcertservice.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    List<TicketType> getTicketTypeByEvent(Event event);
}