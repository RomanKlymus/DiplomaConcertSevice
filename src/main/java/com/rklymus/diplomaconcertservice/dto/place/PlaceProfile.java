package com.rklymus.diplomaconcertservice.dto.place;

import com.rklymus.diplomaconcertservice.dto.event.EventPreview;
import lombok.Data;

import java.util.Set;

@Data
public class PlaceProfile {
    private String name;
    private String phoneNumber;
    private String website;
    private String email;
    private String city;
    private String address;
    private Set<EventPreview> events;
}
