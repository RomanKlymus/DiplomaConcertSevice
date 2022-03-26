package com.rklymus.diplomaconcertservice.dto.event;

import com.rklymus.diplomaconcertservice.dto.ticket.TicketTypeCreation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventCreationProfile {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private Long organizerId;
    @NotNull
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
    private Long placeId;
    @NotNull
    private List<TicketTypeCreation> tickets;
}
