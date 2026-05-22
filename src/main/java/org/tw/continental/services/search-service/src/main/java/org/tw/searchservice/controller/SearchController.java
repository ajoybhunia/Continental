package org.tw.searchservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tw.searchservice.dto.HotelDto;
import org.tw.searchservice.service.SearchService;

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
