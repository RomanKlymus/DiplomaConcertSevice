package com.rklymus.diplomaconcertservice.service.impls;

import com.rklymus.diplomaconcertservice.dto.event.EventCreationProfile;
import com.rklymus.diplomaconcertservice.dto.event.EventPreview;
import com.rklymus.diplomaconcertservice.entity.Account;
import com.rklymus.diplomaconcertservice.entity.Event;
import com.rklymus.diplomaconcertservice.repository.EventRepository;
import com.rklymus.diplomaconcertservice.security.CustomUserDetails;
import com.rklymus.diplomaconcertservice.service.AccountService;
import com.rklymus.diplomaconcertservice.service.EventService;
import com.rklymus.diplomaconcertservice.service.PlaceService;
import com.rklymus.diplomaconcertservice.service.TicketService;
import com.rklymus.diplomaconcertservice.util.RepoUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private RepoUtil repoUtil;

    @Override
    public Page<EventPreview> getAllEvents(Pageable pageable) {
        List<EventPreview> list =
                eventRepository.findAll(pageable).stream()
                        .map(event -> modelMapper.map(event, EventPreview.class))
                        .collect(Collectors.toList());
        return new PageImpl<>(list, pageable, list.size());
    }

    @Override
    public void addEvent(EventCreationProfile eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        event.setOrganizer(accountService.getCurrentAccount());
        event.setPlace(placeService.getPlace(eventDto.getPlaceId()));
        Event savedEvent = eventRepository.save(event);
        ticketService.createTickets(savedEvent, eventDto.getTickets());
        //todo: complete method
    }
}
