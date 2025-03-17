package com.awesomemusic.ams.controller;

import com.awesomemusic.ams.model.dto.BookingDTO;
import com.awesomemusic.ams.model.enumerations.Status;
import com.awesomemusic.ams.service.IManagerBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manager/bookings")
public class ManagerBookingController {
    private final IManagerBookingService managerBookingService;

    public ManagerBookingController(IManagerBookingService managerBookingService) {
        this.managerBookingService = managerBookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getPendingBookings() {
        List<BookingDTO> pendingBookings = managerBookingService.getPendingBookings();
        return ResponseEntity.ok(pendingBookings);
    }

    @PatchMapping("/{code}/approve")
    public ResponseEntity<BookingDTO> approveBooking(@PathVariable String code) {
        BookingDTO updatedBooking = managerBookingService.updateBookingStatus(code, Status.APPROVED);
        return ResponseEntity.ok(updatedBooking);
    }

    @PatchMapping("/{code}/reject")
    public ResponseEntity<BookingDTO> rejectBooking(@PathVariable String code) {
        BookingDTO updatedBooking = managerBookingService.updateBookingStatus(code, Status.REJECTED);
        return ResponseEntity.ok(updatedBooking);
    }
}
