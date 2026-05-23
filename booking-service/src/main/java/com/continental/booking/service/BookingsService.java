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
	private final QueueService queueService;

	public BookingsService(BookingsRepository bookingsRepository, QueueService queueService) {
		this.bookingsRepository = bookingsRepository;
		this.queueService = queueService;
	}

	public List<BookingView> getBookings(Integer userId) {
		return bookingsRepository.getBookingsByUserId(userId);
	}

	public BookingResponse bookHotel(Integer userId, Integer hotelId, Integer rooms) {
		int lastBookingId = bookingsRepository
						.findFirstByOrderByBookingIdDesc()
						.map(BookingIdProjection::getBookingId)
						.orElse(0);
		Booking booking = new Booking(lastBookingId + 1, hotelId, rooms, userId);
		bookingsRepository.save(booking);
		queueService.addJob(booking);
		return new BookingResponse(lastBookingId, true);
	}
}
