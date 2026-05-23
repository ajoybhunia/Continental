package org.tw.continental.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.tw.continental.dto.HotelDto;
import org.tw.continental.service.SearchService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureRestTestClient
class SearchControllerTest {
    @Autowired
    private RestTestClient client;

    @MockitoBean
    private SearchService searchService;

    @Test
    void searchHotelsShouldReturnEmptyList() {
        List<HotelDto> hotelDtoList = new ArrayList<>();

        when(searchService.filterHotelsByCity("anyCity")).thenReturn(new ArrayList<>());
        List<HotelDto> responseBody = client.get()
                .uri(uriBuilder -> uriBuilder.path("/api/search/hotels").queryParam("city", "anyCity").build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<HotelDto>>() {
                })
                .returnResult()
                .getResponseBody();

        assertEquals(hotelDtoList, responseBody);
    }

    @Test
    void searchHotelsShouldReturnListOfHotels() {
        List<HotelDto> hotelDtoList = new ArrayList<>();

        hotelDtoList.add(new HotelDto(1, "City", "ande"));
        hotelDtoList.add(new HotelDto(3, "City", "funda"));

        when(searchService.filterHotelsByCity("City"))
                .thenReturn(List.of(new HotelDto(1, "City", "ande"), new HotelDto(3, "City", "funda")));

        List<HotelDto> responseBody = client.get()
                .uri(uriBuilder -> uriBuilder.path("/api/search/hotels").queryParam("city", "City").build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<HotelDto>>() {})
                .returnResult()
                .getResponseBody();

        assertEquals(hotelDtoList, responseBody);
    }
}