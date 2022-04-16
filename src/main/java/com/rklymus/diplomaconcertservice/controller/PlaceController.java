package com.rklymus.diplomaconcertservice.controller;

import com.rklymus.diplomaconcertservice.dto.place.PlaceCreationProfile;
import com.rklymus.diplomaconcertservice.dto.place.PlaceProfile;
import com.rklymus.diplomaconcertservice.service.PlaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/place")
@Api(tags = "Places")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    @ApiOperation(value = "Create new place")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void createPlace(@RequestBody PlaceCreationProfile place) {
        placeService.createPlace(place);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get all information about place")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PlaceProfile getPlace(@PathVariable Long id) {
        return placeService.getPlaceProfile(id);
    }
}
