package com.awesomemusic.ams.service.impl;

import com.awesomemusic.ams.exceptions.SlotNotFoundException;
import com.awesomemusic.ams.model.Booking;
import com.awesomemusic.ams.model.Slot;
import com.awesomemusic.ams.model.enumerations.SlotName;
import com.awesomemusic.ams.model.enumerations.Status;
import com.awesomemusic.ams.model.dto.BookingDTO;
import com.awesomemusic.ams.model.dto.SlotDTO;
import com.awesomemusic.ams.repository.BookingRepository;
import com.awesomemusic.ams.repository.SlotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private SlotRepository slotRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void testCreateBookingSuccess() throws SlotNotFoundException {
        BookingDTO requestDto = BookingDTO.builder()
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(SlotDTO.builder().id(1L).build())
                .build();

        Slot slot = Slot.builder()
                .id(1L)
                .name(SlotName.MORNING)
                .build();
        when(slotRepository.findById(1L)).thenReturn(Optional.of(slot));

        Booking savedBooking = Booking.builder()
                .id(1L)
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(slot)
                .status(Status.PENDING)
                .code("ABC123")
                .build();
        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);

        BookingDTO createdBooking = bookingService.create(requestDto);

        assertNotNull(createdBooking);
        assertEquals(1L, createdBooking.getId());
        assertEquals("John Doe", createdBooking.getCustomerName());
        assertEquals("john.doe@example.com", createdBooking.getEmail());
        assertNotNull(createdBooking.getSlot());
        assertEquals(1L, createdBooking.getSlot().getId());
        assertEquals(SlotName.MORNING, createdBooking.getSlot().getName());
        assertEquals(Status.PENDING, createdBooking.getStatus());
        assertEquals("ABC123", createdBooking.getCode());
    }

    @Test
    void testCreateBookingSlotNotFound() {
        BookingDTO requestDto = BookingDTO.builder()
                .customerName("Jane Doe")
                .email("jane.doe@example.com")
                .slot(SlotDTO.builder().id(99L).build())
                .build();

        when(slotRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(SlotNotFoundException.class, () -> bookingService.create(requestDto));
    }

    @Test
    void testGetByCodeSuccess() {
        Slot slot = Slot.builder()
                .id(1L)
                .name(SlotName.MORNING)
                .build();

        Booking booking = Booking.builder()
                .id(1L)
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(slot)
                .status(Status.PENDING)
                .code("ABC123")
                .build();

        when(bookingRepository.findBookingByCode("ABC123"))
                .thenReturn(Optional.of(booking));

        BookingDTO bookingDto = bookingService.getByCode("ABC123");

        assertNotNull(bookingDto);
        assertEquals(1L, bookingDto.getId());
        assertEquals("John Doe", bookingDto.getCustomerName());
        assertEquals("john.doe@example.com", bookingDto.getEmail());
        assertNotNull(bookingDto.getSlot());
        assertEquals(1L, bookingDto.getSlot().getId());
        assertEquals(SlotName.MORNING, bookingDto.getSlot().getName());
        assertEquals(Status.PENDING, bookingDto.getStatus());
        assertEquals("ABC123", bookingDto.getCode());
    }

    @Test
    void testGetByCodeNotFound() {
        when(bookingRepository.findBookingByCode("INVALID")).thenReturn(Optional.empty());
        assertThrows(com.awesomemusic.ams.exceptions.BookingNotFoundException.class, () ->
                bookingService.getByCode("INVALID"));
    }
}