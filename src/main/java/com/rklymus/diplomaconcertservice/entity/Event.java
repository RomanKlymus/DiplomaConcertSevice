package com.rklymus.diplomaconcertservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private Account organizer;
    @Column(nullable = false)
    private Long numberOfTickets;
    @Column(nullable = false)
    private LocalDateTime startDateAndTime;
    @Column
    private LocalDateTime endDateAndTime;
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
    @Column(nullable = false)
    private Long price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;
        return id != null && Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
