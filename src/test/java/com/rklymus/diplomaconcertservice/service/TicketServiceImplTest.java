package com.rklymus.diplomaconcertservice.service;

import com.rklymus.diplomaconcertservice.repository.TicketRepository;
import com.rklymus.diplomaconcertservice.repository.TicketTypeRepository;
import com.rklymus.diplomaconcertservice.service.impls.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

public class TicketServiceImplTest {
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private TicketTypeRepository ticketTypeRepository;
    @InjectMocks
    private TicketServiceImpl ticketService;

    @Test
    private void shouldCreateTicketForEvent() {

    }
}
