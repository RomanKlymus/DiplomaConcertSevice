package com.rklymus.diplomaconcertservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "tickets")
public class Ticket {
    @Id
    private String id;
    @OneToOne
    private Event event;
    @OneToOne
    private Account account;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}
