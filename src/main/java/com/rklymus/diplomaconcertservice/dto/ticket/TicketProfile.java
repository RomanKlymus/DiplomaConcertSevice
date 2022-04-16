package com.rklymus.diplomaconcertservice.dto.ticket;

import lombok.Data;

@Data
public class TicketProfile {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long amount;
}
