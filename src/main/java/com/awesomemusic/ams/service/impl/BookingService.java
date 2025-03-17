package com.awesomemusic.ams.service.impl;

import com.awesomemusic.ams.exceptions.SlotNotFoundException;
import com.awesomemusic.ams.model.Slot;
import com.awesomemusic.ams.model.dto.BookingBuilder;
import com.awesomemusic.ams.model.dto.BookingDTO;
import com.awesomemusic.ams.exceptions.BookingNotFoundException;
import com.awesomemusic.ams.model.Booking;
import com.awesomemusic.ams.model.dto.SlotDTO;
import com.awesomemusic.ams.repository.BookingRepository;
import com.awesomemusic.ams.repository.SlotRepository;
import com.awesomemusic.ams.service.IBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, SlotRepository slotRepository) {
        this.bookingRepository = bookingRepository;
        this.slotRepository = slotRepository;
    }

    @Override
    public BookingDTO create(BookingDTO bookingDTO) throws SlotNotFoundException {
        Slot slot = slotRepository.findById(bookingDTO.getSlot().getId())
                .orElseThrow(() -> new SlotNotFoundException("Booking not found with code: " + bookingDTO.getSlot().getId()));

        Booking createdBooking = bookingRepository
                .save(BookingBuilder.toEntity(bookingDTO,
                        SlotDTO.builder()
                                .id(slot.getId())
                                .name(slot.getName())
                                .build()));

        return BookingBuilder.toDto(createdBooking);
    }

    @Override
    public BookingDTO getByCode(String code) {
        Booking booking = bookingRepository.findBookingByCode(code)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with code: " + code));

        return BookingBuilder.toDto(booking);
    }
}
