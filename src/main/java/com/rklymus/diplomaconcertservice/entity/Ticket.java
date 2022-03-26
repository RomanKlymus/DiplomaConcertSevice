package com.rklymus.diplomaconcertservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "tickets")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private TicketType ticketType;
}
