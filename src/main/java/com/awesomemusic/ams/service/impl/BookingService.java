package com.awesomemusic.ams.service.impl;

import com.awesomemusic.ams.dto.BookingBuilder;
import com.awesomemusic.ams.dto.BookingDTO;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingDTO create(BookingDTO bookingDTO) {
        LOGGER.info("Starting creation of booking");
        Booking booking = bookingRepository.save(BookingBuilder.toEntity(bookingDTO));
        BookingDTO createdBooking = BookingBuilder.toDto(booking);
        LOGGER.info("Booking created successfully with code: {}", createdBooking.getId());
        return createdBooking;
    }

    @Override
    public BookingDTO getByCode(String code) {
        LOGGER.info("Fetching booking with code: {}", code);
        Booking booking = bookingRepository.findBookingByCode(code)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with code: " + code));
        BookingDTO bookingDTO = BookingBuilder.toDto(booking);
        LOGGER.info("Booking fetched successfully with code: {}", code);
        return bookingDTO;
    }
}
