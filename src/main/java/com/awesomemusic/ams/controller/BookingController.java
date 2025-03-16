package com.awesomemusic.ams.controller;

import com.awesomemusic.ams.dto.BookingDTO;
import com.awesomemusic.ams.model.Booking;
import com.awesomemusic.ams.service.impl.BookingService;
import jakarta.validation.Valid;
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
    public ResponseEntity<BookingDTO> createBooking(@RequestBody @Valid Booking booking) {
        BookingDTO bookingDTO = bookingService.create(booking);
        return ResponseEntity.ok(bookingDTO);
    }

    @GetMapping("/{code}/status")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable String code) {
        BookingDTO bookingDTO = bookingService.getByCode(code);
        return ResponseEntity.ok(bookingDTO);
    }
}
