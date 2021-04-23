package com.gyawalirajiv.findmyhotel.service.impl;

import com.gyawalirajiv.findmyhotel.domain.Hotel;
import com.gyawalirajiv.findmyhotel.repository.HotelRepository;
import com.gyawalirajiv.findmyhotel.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {

    @InjectMocks
    HotelServiceImpl hotelService;

    @Mock
    HotelRepository hotelRepository;

    public static final String HOTEL_ID = "1";
    public static final String HOTEL_NAME = "GRAND HOTEL";

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll_withData() {
        when(hotelRepository.findAll())
                .thenReturn(Flux.just(new Hotel(HOTEL_ID, HOTEL_NAME), new Hotel(HOTEL_ID + "2", HOTEL_NAME + "2")));

        Flux<Hotel> expected = Flux.just(new Hotel(HOTEL_ID, HOTEL_NAME), new Hotel(HOTEL_ID + "2", HOTEL_NAME + "2"));
        Flux<Hotel> actual = hotelService.findAll();

        assertEquals(expected.collectList().block(), actual.collectList().block());
        verify(hotelRepository).findAll();
    }

    @Test
    void save_withData() {
        when(hotelRepository.save(any(Hotel.class))).thenReturn(Mono.just(new Hotel(HOTEL_ID, HOTEL_NAME)));

        Mono<Hotel> actual = hotelService.save(new Hotel(HOTEL_ID, HOTEL_NAME));
        Mono<Hotel> expected = Mono.just(new Hotel(HOTEL_ID, HOTEL_NAME));

        assertEquals(actual.block(), expected.block());
        verify(hotelRepository).save(any(Hotel.class));
    }

    @Test
    void update_withData() {
        when(hotelRepository.save(any(Hotel.class))).thenReturn(Mono.just(new Hotel(HOTEL_ID, HOTEL_NAME)));

        Mono<Hotel> actual = hotelService.save(new Hotel(HOTEL_ID, HOTEL_NAME));
        Mono<Hotel> expected = Mono.just(new Hotel(HOTEL_ID, HOTEL_NAME));

        assertEquals(actual.block(), expected.block());
        verify(hotelRepository).save(any(Hotel.class));
    }

    @Test
    void delete_withId() {
        when(hotelRepository.existsById(any(String.class))).thenReturn(Mono.just(true));
//        when(hotelRepository.deleteById(any(String.class))).thenReturn(Mono.empty());

        hotelService.delete(HOTEL_ID);

        verify(hotelRepository).existsById(any(String.class));
//        verify(hotelRepository).deleteById(any(String.class));
    }
}