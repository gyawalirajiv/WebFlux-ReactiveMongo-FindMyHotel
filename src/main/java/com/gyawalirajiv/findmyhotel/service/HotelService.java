package com.gyawalirajiv.findmyhotel.service;

import com.gyawalirajiv.findmyhotel.domain.Hotel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface HotelService {

    Flux<Hotel> findAll();

    Mono<Hotel> save(Hotel hotel);

    Mono<Hotel> update(Hotel payload);

    Mono<Void> delete(String id);
}
