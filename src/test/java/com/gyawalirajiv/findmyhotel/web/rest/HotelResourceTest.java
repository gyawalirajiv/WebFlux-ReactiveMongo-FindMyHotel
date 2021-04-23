package com.gyawalirajiv.findmyhotel.web.rest;

import com.gyawalirajiv.findmyhotel.domain.Hotel;
import com.gyawalirajiv.findmyhotel.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


class HotelResourceTest {


    WebTestClient webTestClient;
    HotelService hotelService;
    HotelResource hotelResource;

    public static final String BASE_URI = "/api/hotels";
    public static final String HOTEL_ID = "1";
    public static final String HOTEL_NAME = "GRAND HOTEL";

    @BeforeEach
    public void setUp(){
        hotelService = Mockito.mock(HotelService.class);
        hotelResource = new HotelResource(hotelService);
        webTestClient = WebTestClient.bindToController(hotelResource).build();
    }

    @Test
    public void findAll_withNoParams(){
        given(hotelService.findAll()).willReturn(
                Flux.fromArray(new Hotel[]{new Hotel(HOTEL_ID, HOTEL_NAME), new Hotel(HOTEL_ID + "2", HOTEL_NAME + "2")}));

        webTestClient.get()
                .uri(BASE_URI)
                .exchange()
                .expectStatus()
                .isOk();

        verify(hotelService).findAll();
    }

    @Test
    public void createHotel_withEmptyData(){
        given(hotelService.save(any(Hotel.class))).willReturn(Mono.just(new Hotel()));

        webTestClient.post()
                .uri(BASE_URI)
                .body(Mono.just(new Hotel()), Hotel.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void createHotel_withID(){
        given(hotelService.save(any(Hotel.class))).willReturn(Mono.just(new Hotel()));

        webTestClient.post()
                .uri(BASE_URI)
                .body(Mono.just(new Hotel(HOTEL_ID, HOTEL_NAME)), Hotel.class)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    public void updateHotel_withData(){
        given(hotelService.update(any(Hotel.class))).willReturn(Mono.just(new Hotel(HOTEL_ID, HOTEL_NAME)));

        webTestClient.put()
                .uri(BASE_URI + "/" + HOTEL_ID)
                .body(Mono.just(new Hotel(HOTEL_ID, HOTEL_NAME)), Hotel.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void updateHotel_withNoId(){
        given(hotelService.update(any(Hotel.class))).willReturn(Mono.just(new Hotel(HOTEL_ID, HOTEL_NAME)));

        webTestClient.put()
                .uri(BASE_URI)
                .body(Mono.just(new Hotel(HOTEL_ID, HOTEL_NAME)), Hotel.class)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    public void deleteHotel_withData(){
        given(hotelService.delete(anyString())).willReturn(Mono.empty());

        webTestClient.delete()
                .uri(BASE_URI + "/" + HOTEL_ID)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void deleteHotel_withNoId(){
        given(hotelService.delete(anyString())).willReturn(Mono.empty());

        webTestClient.delete()
                .uri(BASE_URI)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }
}