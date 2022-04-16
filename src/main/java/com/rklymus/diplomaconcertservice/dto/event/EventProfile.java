package com.rklymus.diplomaconcertservice.dto.event;

import com.rklymus.diplomaconcertservice.dto.account.OrganizerPreview;
import com.rklymus.diplomaconcertservice.dto.place.PlacePreview;
import com.rklymus.diplomaconcertservice.dto.ticket.TicketProfile;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EventProfile {
    private String title;
    private String description;
    private OrganizerPreview organizer;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
    private PlacePreview place;
    private Set<TicketProfile> tickets;
}
