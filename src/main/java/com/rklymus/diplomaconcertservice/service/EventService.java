package com.rklymus.diplomaconcertservice.service;

import com.rklymus.diplomaconcertservice.dto.event.EventCreationProfile;
import com.rklymus.diplomaconcertservice.dto.event.EventPreview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    Page<EventPreview> getAllEvents(Pageable pageable);
    void addEvent(EventCreationProfile event);
}
