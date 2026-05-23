package com.continental.booking.service;

import com.continental.booking.model.Booking;
import com.continental.booking.repository.BookingsRepository;
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
		int lastBookingId = bookingsRepository
						.findFirstByOrderByBookingIdDesc()
						.map(BookingIdProjection::getBookingId)
						.orElse(0);
		bookingsRepository.save(new Booking(lastBookingId + 1, hotelId, rooms, userId));
		return new BookingResponse(lastBookingId, true);
	}
}
