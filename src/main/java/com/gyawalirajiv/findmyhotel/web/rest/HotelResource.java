package com.gyawalirajiv.findmyhotel.web.rest;

import com.gyawalirajiv.findmyhotel.domain.Hotel;
import com.gyawalirajiv.findmyhotel.service.HotelService;
import com.gyawalirajiv.findmyhotel.web.rest.errors.BadRequestAlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/hotels")
public class HotelResource {

    HotelService hotelService;

    @Autowired
    public HotelResource(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public Flux<Hotel> getAll(){
        return hotelService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Hotel> create(@RequestBody Hotel payload) throws URISyntaxException {
        if(payload.getId() != null) throw new BadRequestAlertException("Invalid Request! Contains Id!");

        return hotelService.save(payload);
    }

    @PutMapping("{id}")
    public Mono<Hotel> update(@PathVariable String id, @RequestBody Hotel payload) throws URISyntaxException {
        if(!payload.getId().equals(id)) throw new BadRequestAlertException("Invalid Request! No ID Found!");

        return hotelService.update(payload);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable String id){
        if(id.equals("") || id == null) throw new BadRequestAlertException("No ID Present!");

        return hotelService.delete(id);
    }

}
