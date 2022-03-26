package com.rklymus.diplomaconcertservice.controller;

import com.rklymus.diplomaconcertservice.dto.event.EventCreationProfile;
import com.rklymus.diplomaconcertservice.dto.event.EventPreview;
import com.rklymus.diplomaconcertservice.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Api( tags = "Events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/events")
    @ApiOperation(value = "Get all events")
    public Page<EventPreview> getAllEvents(@PageableDefault Pageable pageable) {
        return eventService.getAllEvents(pageable);
    }

    @PostMapping("/event")
    @ApiOperation(value = "Create new event")
    public void addEvent(@RequestBody EventCreationProfile event) {
        eventService.addEvent(event);
    }
}
