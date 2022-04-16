package com.rklymus.diplomaconcertservice.service;

import com.rklymus.diplomaconcertservice.dto.event.EventCreationProfile;
import com.rklymus.diplomaconcertservice.dto.event.EventEditingProfile;
import com.rklymus.diplomaconcertservice.dto.event.EventPreview;
import com.rklymus.diplomaconcertservice.dto.event.EventProfile;
import com.rklymus.diplomaconcertservice.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    Page<EventPreview> getAllEvents(Pageable pageable);

    void addEvent(EventCreationProfile event);

    void editEvent(Long id, EventEditingProfile event);

    Event getEvent(Long id);

    EventProfile getEventProfile(Long id);
}
