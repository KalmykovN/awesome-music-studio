package com.awesomemusic.ams.controller;

import com.awesomemusic.ams.model.dto.BookingDTO;
import com.awesomemusic.ams.model.enumerations.Status;
import com.awesomemusic.ams.service.IManagerBookingService;
import com.awesomemusic.ams.service.impl.ManagerBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manager/bookings")
public class ManagerBookingController {

    private final IManagerBookingService managerBookingService;

    public ManagerBookingController(ManagerBookingService managerBookingService) {
        this.managerBookingService = managerBookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getPendingBookings() {
        List<BookingDTO> pendingBookings = managerBookingService.getPendingBookings();
        return ResponseEntity.ok(pendingBookings);
    }

    @PatchMapping("/{code}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable String code,
                                                          @RequestParam Status newStatus) {
        BookingDTO updatedBooking = managerBookingService.updateBookingStatus(code, newStatus);
        return ResponseEntity.ok(updatedBooking);
    }
}
