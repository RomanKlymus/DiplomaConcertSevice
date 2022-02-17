package com.rklymus.diplomaconcertservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @OneToMany(mappedBy = "place")
    private Set<Event> events;
}