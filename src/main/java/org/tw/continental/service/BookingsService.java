package org.tw.continental.service;

import org.springframework.stereotype.Service;
import org.tw.continental.model.Booking;
import org.tw.continental.repository.BookingsRepository;
import org.tw.continental.repository.HotelRepository;

@Service
public class BookingsService {
    private final BookingsRepository bookingsRepository;
    private final HotelRepository hotelRepository;
    private final MyIdGenerator idGenerator;

    public BookingsService(BookingsRepository bookingsRepository, HotelRepository hotelRepository, MyIdGenerator idGenerator) {
        this.bookingsRepository = bookingsRepository;
        this.hotelRepository = hotelRepository;
        this.idGenerator = idGenerator;
    }

    public int bookHotel(Integer userId, Integer hotelId, Integer rooms) {
        if (hotelRepository.getHotelById(hotelId) == null) {
            throw new RuntimeException();
        }

        int bookingId = idGenerator.next();
        bookingsRepository.insert(new Booking(bookingId, hotelId, rooms, userId));

        return bookingId;
    }
}
