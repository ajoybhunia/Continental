package org.tw.continental.service;

import org.springframework.stereotype.Service;
import org.tw.continental.dto.HotelDto;
import org.tw.continental.repository.HotelRepository;

import java.util.List;

@Service
public class SearchService {
    private final HotelRepository hotelRepository;

    public SearchService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<HotelDto> filterHotelsByCity(String city) {
        return hotelRepository.findHotelsByCity(city);
    }
}
