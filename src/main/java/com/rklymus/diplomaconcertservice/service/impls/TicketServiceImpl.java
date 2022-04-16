package com.rklymus.diplomaconcertservice.service.impls;

import com.rklymus.diplomaconcertservice.dto.ticket.TicketTypeCreation;
import com.rklymus.diplomaconcertservice.entity.Event;
import com.rklymus.diplomaconcertservice.entity.Ticket;
import com.rklymus.diplomaconcertservice.entity.TicketStatus;
import com.rklymus.diplomaconcertservice.entity.TicketType;
import com.rklymus.diplomaconcertservice.repository.TicketRepository;
import com.rklymus.diplomaconcertservice.repository.TicketTypeRepository;
import com.rklymus.diplomaconcertservice.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final ModelMapper modelMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketTypeRepository ticketTypeRepository, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.modelMapper = modelMapper;
    }

    //todo: test this method
    @Override
    public List<Ticket> createTickets(Event event, List<TicketTypeCreation> typeCreationList) {
        Collection<Ticket> tickets = new LinkedList<>();
        List<TicketType> createdTypes = ticketTypeRepository.saveAll(
                typeCreationList.stream().map(type -> modelMapper.map(type, TicketType.class)).collect(Collectors.toList()));

        for (int i = 0; i < createdTypes.size(); i++) {
            TicketTypeCreation ticketType = typeCreationList.get(i);
            for (int j = 0; j < ticketType.getNumberOfTickets(); j++) {
                tickets.add(Ticket.builder().event(event).status(TicketStatus.NOT_BOUGHT).ticketType(createdTypes.get(i)).build());
            }
        }
        //todo: change return type
        return ticketRepository.saveAll(tickets);
    }

    @Override
    public List<TicketType> getTicketsByEvent(Event event) {
        return null;
    }

    @Override
    public long getAmountOfTickets(TicketType ticket) {
        return 0;
    }
}
