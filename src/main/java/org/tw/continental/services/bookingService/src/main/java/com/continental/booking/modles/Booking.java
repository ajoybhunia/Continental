package com.continental.booking.modles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookings")
public class Booking {
	@Id
	private final int bookingId;
	private final Integer hotelId;
	private final Integer rooms;
	private final Integer userId;

	public Booking(int bookingId, Integer hotelId, Integer rooms, Integer userId) {
		this.bookingId = bookingId;
		this.hotelId = hotelId;
		this.rooms = rooms;
		this.userId = userId;
	}
}
