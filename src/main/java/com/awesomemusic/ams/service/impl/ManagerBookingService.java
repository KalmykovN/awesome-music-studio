package com.awesomemusic.ams.service.impl;

import com.awesomemusic.ams.model.dto.BookingBuilder;
import com.awesomemusic.ams.model.dto.BookingDTO;
import com.awesomemusic.ams.exceptions.BookingNotFoundException;
import com.awesomemusic.ams.model.Booking;
import com.awesomemusic.ams.model.enumerations.Status;
import com.awesomemusic.ams.repository.BookingRepository;
import com.awesomemusic.ams.service.IManagerBookingService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerBookingService implements IManagerBookingService { private final BookingRepository bookingRepository;

    public ManagerBookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<BookingDTO> getPendingBookings() {
        List<Booking> bookings = bookingRepository.findByStatus(Status.PENDING);
        return bookings.stream()
                .map(BookingBuilder::toDto)
                .toList();
    }

    @Override
    @Transactional
    public BookingDTO updateBookingStatus(String code, Status newStatus) {
        Booking booking = bookingRepository.findBookingByCode(code)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with code: " + code));
        booking.setStatus(newStatus);
        bookingRepository.save(booking);
        return BookingBuilder.toDto(booking);
    }
}
