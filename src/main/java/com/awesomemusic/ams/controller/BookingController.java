package com.awesomemusic.ams.controller;

import com.awesomemusic.ams.model.dto.BookingDTO;
import com.awesomemusic.ams.service.IBookingService;
import com.awesomemusic.ams.service.impl.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final IBookingService bookingService;

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
