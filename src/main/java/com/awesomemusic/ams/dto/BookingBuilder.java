package com.awesomemusic.ams.dto;

import com.awesomemusic.ams.model.Booking;

public class BookingBuilder {
    public static BookingDTO toDto(Booking booking) {
        if (booking == null) {
            return null;
        }
        return BookingDTO.builder()
                .id(booking.getId())
                .customerName(booking.getCustomerName())
                .email(booking.getEmail())
                .slot(booking.getSlot())
                .status(booking.getStatus())
                .build();
    }

    public static Booking toEntity(BookingDTO bookingDTO) {
        if (bookingDTO == null) {
            return null;
        }
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setCustomerName(bookingDTO.getCustomerName());
        booking.setEmail(bookingDTO.getEmail());
        booking.setSlot(bookingDTO.getSlot());
        booking.setStatus(bookingDTO.getStatus());
        return booking;
    }
}