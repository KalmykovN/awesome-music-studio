package com.awesomemusic.ams.controller;

import com.awesomemusic.ams.dto.BookingDTO;
import com.awesomemusic.ams.model.Booking;
import com.awesomemusic.ams.service.impl.BookingService;
import com.awesomemusic.ams.service.impl.ManagerBookingService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody @Valid BookingDTO request) {
        BookingDTO bookingDTO = bookingService.create(request);
        return ResponseEntity.ok(bookingDTO);
    }

    @GetMapping("/{code}/status")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable String code) {
        BookingDTO bookingDTO = bookingService.getByCode(code);
        return ResponseEntity.ok(bookingDTO);
    }
}
