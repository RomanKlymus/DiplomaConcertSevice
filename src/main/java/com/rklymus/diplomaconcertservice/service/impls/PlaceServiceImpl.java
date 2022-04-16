package com.rklymus.diplomaconcertservice.service.impls;

import com.rklymus.diplomaconcertservice.dto.event.EventPreview;
import com.rklymus.diplomaconcertservice.dto.place.PlaceCreationProfile;
import com.rklymus.diplomaconcertservice.dto.place.PlaceProfile;
import com.rklymus.diplomaconcertservice.entity.Place;
import com.rklymus.diplomaconcertservice.repository.PlaceRepository;
import com.rklymus.diplomaconcertservice.service.PlaceService;
import com.rklymus.diplomaconcertservice.util.RepoUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;
    private final RepoUtil repoUtil;
    private final ModelMapper modelMapper;

    public PlaceServiceImpl(PlaceRepository placeRepository, RepoUtil repoUtil, ModelMapper modelMapper) {
        this.placeRepository = placeRepository;
        this.repoUtil = repoUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Place getPlace(Long id) {
        return repoUtil.findById(Place.class, id);
    }

    @Override
    public void createPlace(PlaceCreationProfile place) {
        placeRepository.save(modelMapper.map(place, Place.class));
    }

    @Override
    public PlaceProfile getPlaceProfile(Long id) {
        Place place = getPlace(id);
        PlaceProfile mappedPlace = modelMapper.map(place, PlaceProfile.class);
        Set<EventPreview> eventPreviews = place.getEvents()
                .stream()
                .map(event -> modelMapper.map(event, EventPreview.class))
                .collect(Collectors.toSet());

        mappedPlace.setEvents(eventPreviews);

        return mappedPlace;
    }
}
