package com.rklymus.diplomaconcertservice.service.impls;

import com.rklymus.diplomaconcertservice.entity.Place;
import com.rklymus.diplomaconcertservice.exception.NotFoundException;
import com.rklymus.diplomaconcertservice.repository.PlaceRepository;
import com.rklymus.diplomaconcertservice.service.PlaceService;
import com.rklymus.diplomaconcertservice.util.RepoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private RepoUtil repoUtil;

    @Override
    public Place getPlace(Long id) {
        return repoUtil.findById(Place.class, id);
    }

}
