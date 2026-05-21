package org.tw.continental.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tw.continental.dto.HotelDto;
import org.tw.continental.model.Hotel;
import org.tw.continental.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelDto>> searchHotels(@RequestParam String city) {
        return ResponseEntity.ok(searchService.filterHotelsByCity(city));
    }
}
