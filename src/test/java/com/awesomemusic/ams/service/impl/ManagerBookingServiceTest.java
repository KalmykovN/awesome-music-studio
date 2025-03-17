package com.awesomemusic.ams.service.impl;

import com.awesomemusic.ams.exceptions.BookingNotFoundException;
import com.awesomemusic.ams.model.Booking;
import com.awesomemusic.ams.model.enumerations.SlotName;
import com.awesomemusic.ams.model.enumerations.Status;
import com.awesomemusic.ams.model.Slot;
import com.awesomemusic.ams.model.dto.BookingDTO;
import com.awesomemusic.ams.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ManagerBookingServiceTest {

    @MockBean
    private BookingRepository bookingRepository;

    @InjectMocks
    private ManagerBookingService managerBookingService;

    @Test
    void testGetPendingBookings() {
        Booking booking1 = Booking.builder()
                .id(1L)
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(new Slot(SlotName.MORNING))
                .status(Status.PENDING)
                .code("ABC123")
                .build();
        Booking booking2 = Booking.builder()
                .id(2L)
                .customerName("Jane Smith")
                .email("jane.smith@example.com")
                .slot(new Slot(SlotName.AFTERNOON))
                .status(Status.PENDING)
                .code("DEF456")
                .build();

        List<Booking> pendingBookings = List.of(booking1, booking2);
        when(bookingRepository.findByStatus(Status.PENDING)).thenReturn(pendingBookings);

        List<BookingDTO> result = managerBookingService.getPendingBookings();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getCustomerName());
        assertEquals("Jane Smith", result.get(1).getCustomerName());
    }

    @Test
    void testUpdateBookingStatusSuccess() {
        Booking booking = Booking.builder()
                .id(1L)
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(new Slot(SlotName.MORNING))
                .status(Status.PENDING)
                .code("ABC123")
                .build();

        when(bookingRepository.findBookingByCode("ABC123")).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BookingDTO updatedDto = managerBookingService.updateBookingStatus("ABC123", Status.APPROVED);

        assertNotNull(updatedDto);
        assertEquals(Status.APPROVED, updatedDto.getStatus());
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void testUpdateBookingStatusNotFound() {
        when(bookingRepository.findBookingByCode("INVALID")).thenReturn(Optional.empty());

        assertThrows(BookingNotFoundException.class, () ->
                managerBookingService.updateBookingStatus("INVALID", Status.APPROVED));
    }
}
