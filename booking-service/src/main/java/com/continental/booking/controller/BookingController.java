package com.continental.booking.controller;

import com.continental.booking.service.BookingsService;
import com.continental.booking.views.BookingRequest;
import com.continental.booking.views.BookingResponse;
import com.continental.booking.views.BookingView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {
	private final BookingsService bookingsService;

	public BookingController(BookingsService bookingsService) {
		this.bookingsService = bookingsService;
	}

	@GetMapping(value = "/bookings")
	public ResponseEntity<List<BookingView>> getBookings(@RequestAttribute Integer userId) {
		return ResponseEntity.ok(bookingsService.getBookings(userId));
	}

	@PostMapping(value = "/bookings")
	public ResponseEntity<BookingResponse> bookHotel(@RequestAttribute Integer userId, @RequestBody BookingRequest bookingRequest) {
		BookingResponse bookingResponse = bookingsService.bookHotel(userId, bookingRequest.hotel_id(), bookingRequest.rooms());
		return ResponseEntity.ok(bookingResponse);
	}
}
