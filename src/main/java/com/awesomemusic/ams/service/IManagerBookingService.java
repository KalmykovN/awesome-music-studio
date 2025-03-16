package com.awesomemusic.ams.service;

import com.awesomemusic.ams.dto.BookingDTO;
import com.awesomemusic.ams.model.Status;

import java.util.List;

public interface IManagerBookingService {
    List<BookingDTO> getPendingBookings();

    BookingDTO updateBookingStatus(String code, Status newStatus);
}
