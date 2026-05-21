package org.tw.continental.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tw.continental.dto.HotelDto;
import org.tw.continental.model.Hotel;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, Integer> {
    List<HotelDto> findHotelsByCity(String city);
}
