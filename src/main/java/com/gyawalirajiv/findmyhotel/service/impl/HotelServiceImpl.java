package com.gyawalirajiv.findmyhotel.service.impl;

import com.gyawalirajiv.findmyhotel.domain.Hotel;
import com.gyawalirajiv.findmyhotel.repository.HotelRepository;
import com.gyawalirajiv.findmyhotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Flux<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Mono<Hotel> save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
}
