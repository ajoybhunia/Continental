package org.tw.continental.service;

import org.springframework.stereotype.Service;
import org.tw.continental.dto.BookingDetailsDto;
import org.tw.continental.model.Booking;
import org.tw.continental.projection.BookingIdProjection;
import org.tw.continental.repository.BookingsRepository;
import org.tw.continental.repository.HotelRepository;

import java.util.List;

@Service
public class BookingsService {
    private final BookingsRepository bookingsRepository;
    private final HotelRepository hotelRepository;

    public BookingsService(BookingsRepository bookingsRepository, HotelRepository hotelRepository) {
        this.bookingsRepository = bookingsRepository;
        this.hotelRepository = hotelRepository;
    }

    public int bookHotel(Integer userId, Integer hotelId, Integer rooms) {
        if (hotelRepository.getHotelById(hotelId) == null) {
            throw new RuntimeException();
        }

        int bookingId = bookingsRepository
                .findFirstByOrderByBookingIdDesc()
                .map(BookingIdProjection::getBookingId)
                .orElse(0);

        bookingsRepository.insert(new Booking(bookingId + 1, hotelId, rooms, userId));

        return bookingId;
    }

    public List<BookingDetailsDto> getBookings(Integer userId) {
        return bookingsRepository.findBookingsByUserId(userId);
    }
}
