package com.continental.booking.services;

import com.continental.booking.modles.Booking;
import com.continental.booking.repositories.BookingsRepository;
import com.continental.booking.views.BookingIdProjection;
import com.continental.booking.views.BookingResponse;
import com.continental.booking.views.BookingView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingsService {
	private final BookingsRepository bookingsRepository;

	public BookingsService(BookingsRepository bookingsRepository) {
		this.bookingsRepository = bookingsRepository;
	}

	public List<BookingView> getBookings(Integer userId) {
		return bookingsRepository.getBookingsByUserId(userId);
	}

	public BookingResponse bookHotel(Integer userId, Integer hotelId, Integer rooms) {
		int bookingId = bookingsRepository
						.findFirstByOrderByBookingIdDesc()
						.map(BookingIdProjection::getBookingId)
						.orElse(0);
		bookingsRepository.save(new Booking(bookingId, hotelId, rooms, userId));
		return new BookingResponse(bookingId, true);
	}
}
