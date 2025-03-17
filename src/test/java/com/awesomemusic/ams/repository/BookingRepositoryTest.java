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
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Test
    void testFindBookingByCode() {
        Slot slot = new Slot();
        slot.setName(SlotName.MORNING);
        slot = slotRepository.save(slot);

        Booking booking = Booking.builder()
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(slot)
                .status(Status.PENDING)
                .build();
        booking = bookingRepository.save(booking);

        Optional<Booking> found = bookingRepository.findBookingByCode(booking.getCode());
        assertThat(found).isPresent();
        assertThat(found.get().getCustomerName()).isEqualTo("John Doe");
    }

    @Test
    void testFindByStatus() {
        Slot slot = new Slot();
        slot.setName(SlotName.MORNING);
        slot = slotRepository.save(slot);

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

        List<Booking> pendingBookings = bookingRepository.findByStatus(Status.PENDING);
        assertThat(pendingBookings).hasSize(1);
        assertThat(pendingBookings.get(0).getCustomerName()).isEqualTo("John Doe");
    }
}
