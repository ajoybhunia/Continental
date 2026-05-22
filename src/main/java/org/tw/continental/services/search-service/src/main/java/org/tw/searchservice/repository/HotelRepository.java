package org.tw.searchservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tw.searchservice.dto.HotelDto;
import org.tw.searchservice.model.Hotel;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, Integer> {
    List<HotelDto> findHotelsByCity(String city);
    HotelDto getHotelById(Integer id);
}
