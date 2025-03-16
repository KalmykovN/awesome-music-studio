package com.awesomemusic.ams.service.impl;

import com.awesomemusic.ams.dto.BookingBuilder;
import com.awesomemusic.ams.dto.BookingDTO;
import com.awesomemusic.ams.exceptions.BookingNotFoundException;
import com.awesomemusic.ams.model.Booking;
import com.awesomemusic.ams.model.Status;
import com.awesomemusic.ams.repository.BookingRepository;
import com.awesomemusic.ams.service.IManagerBookingService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerBookingService implements IManagerBookingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerBookingService.class);
    private final BookingRepository bookingRepository;

    public ManagerBookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<BookingDTO> getPendingBookings() {
        LOGGER.info("Fetching pending bookings...");
        List<Booking> bookings = bookingRepository.findByStatus(Status.PENDING);
        List<BookingDTO> list = bookings.stream()
                .map(BookingBuilder::toDto)
                .toList();
        LOGGER.info("Fetched {} pending bookings", list.size());
        return list;
    }

    @Override
    @Transactional
    public BookingDTO updateBookingStatus(String code, Status newStatus) {
        LOGGER.info("Updating booking status for code: {} to new status: {}", code, newStatus);
        Booking booking = bookingRepository.findBookingByCode(code)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with code: " + code));
        booking.setStatus(newStatus);
        bookingRepository.save(booking);
        BookingDTO updatedBooking = BookingBuilder.toDto(booking);
        LOGGER.info("Booking status updated for code: {}. New status: {}", code, newStatus);
        return updatedBooking;
    }
}
