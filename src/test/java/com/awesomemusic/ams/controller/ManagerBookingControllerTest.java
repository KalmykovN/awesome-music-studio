package com.awesomemusic.ams.controller;


import com.awesomemusic.ams.model.dto.BookingDTO;
import com.awesomemusic.ams.model.dto.SlotDTO;
import com.awesomemusic.ams.model.enumerations.SlotName;
import com.awesomemusic.ams.model.enumerations.Status;
import com.awesomemusic.ams.service.IManagerBookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ManagerBookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IManagerBookingService managerBookingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetPendingBookings() throws Exception {
        // Create two sample BookingDTO objects for pending bookings.
        BookingDTO booking1 = BookingDTO.builder()
                .id(1L)
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(SlotDTO.builder().id(1L).name(SlotName.MORNING).build())
                .status(Status.PENDING)
                .code("ABC123")
                .build();
        BookingDTO booking2 = BookingDTO.builder()
                .id(2L)
                .customerName("Jane Smith")
                .email("jane.smith@example.com")
                .slot(SlotDTO.builder().id(2L).name(SlotName.AFTERNOON).build())
                .status(Status.PENDING)
                .code("DEF456")
                .build();

        List<BookingDTO> pendingBookings = List.of(booking1, booking2);
        Mockito.when(managerBookingService.getPendingBookings()).thenReturn(pendingBookings);

        // Perform GET request and verify JSON response.
        mockMvc.perform(get("/api/v1/manager/bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].customerName").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[0].slot.id").value(1))
                .andExpect(jsonPath("$[0].slot.name").value("MORNING"))
                .andExpect(jsonPath("$[0].status").value("PENDING"))
                .andExpect(jsonPath("$[0].code").value("ABC123"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].customerName").value("Jane Smith"))
                .andExpect(jsonPath("$[1].email").value("jane.smith@example.com"))
                .andExpect(jsonPath("$[1].slot.id").value(2))
                .andExpect(jsonPath("$[1].slot.name").value("AFTERNOON"))
                .andExpect(jsonPath("$[1].status").value("PENDING"))
                .andExpect(jsonPath("$[1].code").value("DEF456"));
    }

    @Test
    public void testApproveBooking() throws Exception {
        // Create a sample BookingDTO representing an approved booking.
        BookingDTO updatedBooking = BookingDTO.builder()
                .id(1L)
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(SlotDTO.builder().id(1L).name(SlotName.MORNING).build())
                .status(Status.APPROVED)
                .code("ABC123")
                .build();

        Mockito.when(managerBookingService.updateBookingStatus(eq("ABC123"), eq(Status.APPROVED)))
                .thenReturn(updatedBooking);

        // Perform PATCH request to approve booking and verify response.
        mockMvc.perform(patch("/api/v1/manager/bookings/ABC123/approve")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("APPROVED"));
    }

    @Test
    public void testRejectBooking() throws Exception {
        // Create a sample BookingDTO representing a rejected booking.
        BookingDTO updatedBooking = BookingDTO.builder()
                .id(1L)
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(SlotDTO.builder().id(1L).name(SlotName.MORNING).build())
                .status(Status.REJECTED)
                .code("ABC123")
                .build();

        Mockito.when(managerBookingService.updateBookingStatus(eq("ABC123"), eq(Status.REJECTED)))
                .thenReturn(updatedBooking);

        // Perform PATCH request to reject booking and verify response.
        mockMvc.perform(patch("/api/v1/manager/bookings/ABC123/reject")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("REJECTED"));
    }
}
