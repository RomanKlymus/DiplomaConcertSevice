package com.rklymus.diplomaconcertservice.dto.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventEditingProfile {
    private String title;
    private String description;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
    private Long placeId;
}
