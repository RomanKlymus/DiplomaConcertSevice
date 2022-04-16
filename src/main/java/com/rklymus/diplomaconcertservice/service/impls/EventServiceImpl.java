package com.rklymus.diplomaconcertservice.service.impls;

import com.rklymus.diplomaconcertservice.dto.account.OrganizerPreview;
import com.rklymus.diplomaconcertservice.dto.event.EventCreationProfile;
import com.rklymus.diplomaconcertservice.dto.event.EventEditingProfile;
import com.rklymus.diplomaconcertservice.dto.event.EventPreview;
import com.rklymus.diplomaconcertservice.dto.event.EventProfile;
import com.rklymus.diplomaconcertservice.dto.place.PlacePreview;
import com.rklymus.diplomaconcertservice.dto.ticket.TicketProfile;
import com.rklymus.diplomaconcertservice.entity.Event;
import com.rklymus.diplomaconcertservice.entity.TicketType;
import com.rklymus.diplomaconcertservice.repository.EventRepository;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
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
    public Event getEvent(Long id) {
        return repoUtil.findById(Event.class, id);
    }

    @Override
    public EventProfile getEventProfile(Long id) {
        Event event = getEvent(id);
        EventProfile eventProfile = modelMapper.map(event, EventProfile.class);
        eventProfile.setOrganizer(modelMapper.map(event.getOrganizer(), OrganizerPreview.class));
        eventProfile.setPlace(modelMapper.map(event.getPlace(), PlacePreview.class));
        List<TicketType> tickets = ticketService.getTicketsByEvent(event);
        Set<TicketProfile> ticketProfiles = tickets.stream().map(ticket -> {
            TicketProfile profile = modelMapper.map(ticket, TicketProfile.class);
            profile.setAmount(ticketService.getAmountOfTickets(ticket));
            return profile;
        }).collect(Collectors.toSet());
        eventProfile.setTickets(ticketProfiles);
        return eventProfile;
    }

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
        //todo: complete method(?)
    }

    //todo: test
    @Override
    public void editEvent(Long id, EventEditingProfile event) {
        Event eventToEdit = getEvent(id);
        modelMapper.map(event, eventToEdit);
        eventToEdit.setPlace(placeService.getPlace(event.getPlaceId()));
        eventRepository.save(eventToEdit);
    }
}
