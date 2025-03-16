package com.awesomemusic.ams.service;

import com.awesomemusic.ams.model.dto.BookingDTO;

public interface IBookingService {
    BookingDTO create(BookingDTO booking);

    BookingDTO getByCode(String code);
}
