package com.gyawalirajiv.findmyhotel.service.impl;

import com.gyawalirajiv.findmyhotel.domain.Hotel;
import com.gyawalirajiv.findmyhotel.repository.HotelRepository;
import com.gyawalirajiv.findmyhotel.service.HotelService;
import com.gyawalirajiv.findmyhotel.web.rest.errors.BadRequestAlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @Override
    public Mono<Hotel> update(Hotel payload) {
        return hotelRepository
                .existsById(payload.getId())
                .flatMap(exists -> {
                    if(!exists) Mono.error(new BadRequestAlertException("No Such Item Found!"));
                    return hotelRepository.save(payload);
                });
    }

    @Override
    public Mono<Void> delete(String id) {
        return hotelRepository
                .existsById(id)
                .flatMap(exist -> {
                    if(!exist) return Mono.error(new BadRequestAlertException("No Such Item Found!"));
                    return hotelRepository.deleteById(id);
                });
    }
}
