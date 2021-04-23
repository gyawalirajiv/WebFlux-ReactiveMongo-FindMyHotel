package com.gyawalirajiv.findmyhotel.repository;

import com.gyawalirajiv.findmyhotel.domain.Hotel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends ReactiveMongoRepository<Hotel, String> {
}
