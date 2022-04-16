package com.rklymus.diplomaconcertservice.controller;

import com.rklymus.diplomaconcertservice.dto.event.EventCreationProfile;
import com.rklymus.diplomaconcertservice.dto.event.EventEditingProfile;
import com.rklymus.diplomaconcertservice.dto.event.EventPreview;
import com.rklymus.diplomaconcertservice.dto.event.EventProfile;
import com.rklymus.diplomaconcertservice.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Api(tags = "Events")
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    @ApiOperation(value = "Get all events")
    public Page<EventPreview> getAllEvents(@PageableDefault Pageable pageable) {
        return eventService.getAllEvents(pageable);
    }

    @GetMapping("/{id}")
    public EventProfile getEvent(@PathVariable Long id) {
        return eventService.getEventProfile(id);
    }

    @PostMapping
    @ApiOperation(value = "Create new event")
    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    public void addEvent(@RequestBody EventCreationProfile event) {
        eventService.addEvent(event);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    public void editEvent(@PathVariable Long id, @RequestBody EventEditingProfile event) {
        eventService.editEvent(id, event);
    }

}
