package com.awesomemusic.ams.service;

import com.awesomemusic.ams.exceptions.SlotNotFoundException;
import com.awesomemusic.ams.model.dto.BookingDTO;

public interface IBookingService {
    BookingDTO create(BookingDTO booking) throws SlotNotFoundException;

    BookingDTO getByCode(String code);
}
