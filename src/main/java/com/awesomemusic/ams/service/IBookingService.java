package com.awesomemusic.ams.service;

import com.awesomemusic.ams.dto.BookingDTO;
import com.awesomemusic.ams.model.Booking;
import jakarta.validation.Valid;

public interface IBookingService {
    BookingDTO create(Booking booking);

    BookingDTO getByCode(String code);
}
