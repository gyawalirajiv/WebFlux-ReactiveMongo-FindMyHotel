package com.gyawalirajiv.findmyhotel.web.rest;

import com.gyawalirajiv.findmyhotel.domain.Hotel;
import com.gyawalirajiv.findmyhotel.service.HotelService;
import com.gyawalirajiv.findmyhotel.web.rest.errors.BadRequestAlertException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


class HotelResourceTest {

    WebTestClient webTestClient;
    HotelService hotelService;
    HotelResource hotelResource;

    public static final String baseUri = "/api/hotels";

    @BeforeEach
    public void setUp(){
        hotelService = Mockito.mock(HotelService.class);
        hotelResource = new HotelResource(hotelService);
        webTestClient = WebTestClient.bindToController(hotelResource).build();
    }

    @Test
    public void findAll_withNoParams(){
        given(hotelService.findAll()).willReturn(
                Flux.fromArray(new Hotel[]{new Hotel("1", "Rajiv"), new Hotel("2", "Sanjiv")}));
        webTestClient.get()
                .uri(baseUri)
                .exchange()
                .expectStatus()
                .isOk();
        verify(hotelService).findAll();
    }

    @Test
    public void createHotel_withEmptyData(){
        given(hotelService.save(any(Hotel.class))).willReturn(Mono.just(new Hotel()));

        webTestClient.post()
                .uri(baseUri)
                .body(Mono.just(new Hotel()), Hotel.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void createHotel_withID(){
        given(hotelService.save(any(Hotel.class))).willReturn(Mono.just(new Hotel()));

        webTestClient.post()
                .uri(baseUri)
                .body(Mono.just(new Hotel("1", "GRAND HOTEL")), Hotel.class)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }
}