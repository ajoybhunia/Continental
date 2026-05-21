package org.tw.continental.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tw.continental.BookingIdProjection;
import org.tw.continental.model.Booking;

import java.util.Optional;

@Repository
public interface BookingsRepository extends MongoRepository<Booking, Integer> {
    Optional<BookingIdProjection> findFirstByOrderByBookingIdDesc();
}
