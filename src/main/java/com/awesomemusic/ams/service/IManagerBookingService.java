package com.awesomemusic.ams.service;

import com.awesomemusic.ams.model.dto.BookingDTO;
import com.awesomemusic.ams.model.enumerations.Status;

import java.util.List;

public interface IManagerBookingService {
    List<BookingDTO> getPendingBookings();

    BookingDTO updateBookingStatus(String code, Status newStatus);
}
