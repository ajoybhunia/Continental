package org.tw.continental.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tw.continental.dto.BookingDetailsDto;
import org.tw.continental.dto.BookingRequest;
import org.tw.continental.dto.BookingResponse;
import org.tw.continental.service.BookingsService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingsController {
    private final BookingsService bookingsService;

    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @PostMapping("/bookings")
    public BookingResponse bookHotel(@RequestAttribute Integer userId, @RequestBody BookingRequest bookingRequest) {
        int bookingId = bookingsService.bookHotel(userId, bookingRequest.hotel_id(), bookingRequest.rooms());
        return new BookingResponse(bookingId, true);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDetailsDto>> getBookings(@RequestAttribute Integer userId) {
        return ResponseEntity.ok(bookingsService.getBookings(userId));
    }
}
