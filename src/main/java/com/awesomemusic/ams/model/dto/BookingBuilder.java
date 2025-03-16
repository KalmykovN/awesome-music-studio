package com.awesomemusic.ams.model.dto;

import com.awesomemusic.ams.model.Booking;
import com.awesomemusic.ams.model.Slot;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Data
@Component
public class BookingBuilder {
    public static BookingDTO toDto(Booking booking) {
        if (booking == null) {
            return null;
        }

        SlotDTO slot = SlotDTO.builder()
                .id(booking.getSlot().getId())
                .name(booking.getSlot().getName())
                .build();

        return BookingDTO.builder()
                .id(booking.getId())
                .customerName(booking.getCustomerName())
                .email(booking.getEmail())
                .slot(slot)
                .status(booking.getStatus())
                .code(booking.getCode())
                .build();
    }

    public static Booking toEntity(BookingDTO bookingDTO) {
        if (bookingDTO == null) {
            return null;
        }

        Slot slot = Slot.builder()
                .id(bookingDTO.getSlot().getId())
                .name(bookingDTO.getSlot().getName())
                .build();

        return Booking.builder()
                .id(bookingDTO.getId())
                .customerName(bookingDTO.getCustomerName())
                .email(bookingDTO.getEmail())
                .slot(slot)
                .build();
    }
}