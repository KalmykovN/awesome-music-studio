package com.awesomemusic.ams.service.impl;

import com.awesomemusic.ams.model.dto.BookingBuilder;
import com.awesomemusic.ams.model.dto.BookingDTO;
import com.awesomemusic.ams.exceptions.BookingNotFoundException;
import com.awesomemusic.ams.model.Booking;
import com.awesomemusic.ams.repository.BookingRepository;
import com.awesomemusic.ams.service.IBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingDTO create(BookingDTO bookingDTO) {
        Booking booking = bookingRepository.save(BookingBuilder.toEntity(bookingDTO));
        return BookingBuilder.toDto(booking);
    }

    @Override
    public BookingDTO getByCode(String code) {
        Booking booking = bookingRepository.findBookingByCode(code)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with code: " + code));
        return BookingBuilder.toDto(booking);
    }
}
