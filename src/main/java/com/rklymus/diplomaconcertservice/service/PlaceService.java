package com.rklymus.diplomaconcertservice.service;

import com.rklymus.diplomaconcertservice.dto.place.PlaceCreationProfile;
import com.rklymus.diplomaconcertservice.dto.place.PlaceProfile;
import com.rklymus.diplomaconcertservice.entity.Place;

public interface PlaceService {
    Place getPlace(Long id);

    void createPlace(PlaceCreationProfile place);

    PlaceProfile getPlaceProfile(Long id);
}
