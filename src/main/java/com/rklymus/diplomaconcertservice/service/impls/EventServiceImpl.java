package com.rklymus.diplomaconcertservice.service.impls;

import com.rklymus.diplomaconcertservice.dto.event.EventPreview;
import com.rklymus.diplomaconcertservice.repository.EventRepository;
import com.rklymus.diplomaconcertservice.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<EventPreview> getAllEvents(Pageable pageable) {
        List<EventPreview> list =
                eventRepository.findAll(pageable).stream()
                .map(event -> modelMapper.map(event, EventPreview.class))
                .collect(Collectors.toList());
        return new PageImpl<>(list, pageable, list.size());
    }
}
