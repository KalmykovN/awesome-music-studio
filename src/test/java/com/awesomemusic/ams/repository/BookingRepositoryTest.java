package com.awesomemusic.ams.repository;

import com.awesomemusic.ams.model.Booking;
import com.awesomemusic.ams.model.Slot;
import com.awesomemusic.ams.model.enumerations.SlotName;
import com.awesomemusic.ams.model.enumerations.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Test
    public void testFindBookingByCode() {
        // Create and persist a Slot first
        Slot slot = new Slot();
        slot.setName(SlotName.MORNING);
        slot = slotRepository.save(slot);

        // Create and persist a Booking
        Booking booking = Booking.builder()
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(slot)
                .status(Status.PENDING)
                .build();
        booking = bookingRepository.save(booking);

        // Use custom repository method to retrieve by generated code
        Optional<Booking> found = bookingRepository.findBookingByCode(booking.getCode());
        assertThat(found).isPresent();
        assertThat(found.get().getCustomerName()).isEqualTo("John Doe");
    }

    @Test
    public void testFindByStatus() {
        // Create and persist a Slot
        Slot slot = new Slot();
        slot.setName(SlotName.MORNING);
        slot = slotRepository.save(slot);

        // Create two bookings with different statuses
        Booking booking1 = Booking.builder()
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(slot)
                .status(Status.PENDING)
                .build();
        Booking booking2 = Booking.builder()
                .customerName("Jane Smith")
                .email("jane.smith@example.com")
                .slot(slot)
                .status(Status.APPROVED)
                .build();
        bookingRepository.save(booking1);
        bookingRepository.save(booking2);

        // Retrieve only pending bookings
        List<Booking> pendingBookings = bookingRepository.findByStatus(Status.PENDING);
        assertThat(pendingBookings).hasSize(1);
        assertThat(pendingBookings.get(0).getCustomerName()).isEqualTo("John Doe");
    }
}
