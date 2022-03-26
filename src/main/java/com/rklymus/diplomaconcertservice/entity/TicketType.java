package com.rklymus.diplomaconcertservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "tickets_types")
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private Long price;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TicketType ticketType = (TicketType) o;
        return id != null && Objects.equals(id, ticketType.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
