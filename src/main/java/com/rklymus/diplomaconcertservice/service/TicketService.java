package com.rklymus.diplomaconcertservice.service;

import com.rklymus.diplomaconcertservice.dto.ticket.TicketTypeCreation;
import com.rklymus.diplomaconcertservice.entity.Event;
import com.rklymus.diplomaconcertservice.entity.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> createTickets(Event event, List<TicketTypeCreation> typeCreationList);
}
