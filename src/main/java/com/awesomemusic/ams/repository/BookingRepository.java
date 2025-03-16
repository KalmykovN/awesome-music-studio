package com.awesomemusic.ams.repository;

import com.awesomemusic.ams.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findBookingByCode(String bookingCode);
}