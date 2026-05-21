package org.tw.continental.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tw.continental.dto.BookingDetailsDto;
import org.tw.continental.dto.HotelDto;
import org.tw.continental.model.Booking;
import org.tw.continental.projection.BookingIdProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingsRepository extends MongoRepository<Booking, Integer> {
    Optional<BookingIdProjection> findFirstByOrderByBookingIdDesc();
    List<BookingDetailsDto> findBookingsByUserId(Integer id);
}
