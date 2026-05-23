package com.continental.booking.repositories;

import com.continental.booking.modles.Booking;
import com.continental.booking.views.BookingIdProjection;
import com.continental.booking.views.BookingView;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingsRepository extends MongoRepository<Booking, Integer> {
	List<BookingView> getBookingsByUserId(Integer userId);

	Optional<BookingIdProjection> findFirstByOrderByBookingIdDesc();
}
