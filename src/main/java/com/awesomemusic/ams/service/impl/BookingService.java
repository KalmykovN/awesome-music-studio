package com.awesomemusic.ams.service.impl;

import com.awesomemusic.ams.dto.BookingDTO;
import com.awesomemusic.ams.model.Booking;
import com.awesomemusic.ams.service.IBookingService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingService {

    @Override
    public BookingDTO create(Booking booking) {
        return null;
    }

    @Override
    public BookingDTO getByCode(String code) {
        return null;
    }
}
