package com.rklymus.diplomaconcertservice.dto.ticket;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class TicketTypeCreation {
    @NotBlank
    private String name;
    @Positive
    private Long price;
    private String description;
    @Positive
    private Long numberOfTickets;
}
