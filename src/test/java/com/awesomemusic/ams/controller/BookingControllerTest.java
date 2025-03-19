package com.awesomemusic.ams.controller;

import com.awesomemusic.ams.config.InitializeDatabase;
import com.awesomemusic.ams.model.dto.BookingDTO;
import com.awesomemusic.ams.model.dto.SlotDTO;
import com.awesomemusic.ams.model.enumerations.SlotName;
import com.awesomemusic.ams.model.enumerations.Status;
import com.awesomemusic.ams.service.IBookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InitializeDatabase conf;

    @MockBean
    private IBookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testCreateBooking() throws Exception {
        BookingDTO requestDto = BookingDTO.builder()
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(SlotDTO.builder().id(1L).name(SlotName.MORNING).build())
                .build();

        BookingDTO responseDto = BookingDTO.builder()
                .id(1L)
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(SlotDTO.builder().id(1L).name(SlotName.MORNING).build())
                .status(Status.PENDING)
                .code("ABC123")
                .build();

        Mockito.when(bookingService.create(any(BookingDTO.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.slot.id").value(1))
                .andExpect(jsonPath("$.slot.name").value("MORNING"))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.code").value("ABC123"));
    }

    @Test
    void testGetBooking() throws Exception {
        BookingDTO responseDto = BookingDTO.builder()
                .id(1L)
                .customerName("John Doe")
                .email("john.doe@example.com")
                .slot(SlotDTO.builder().id(1L).name(SlotName.MORNING).build())
                .status(Status.PENDING)
                .code("ABC123")
                .build();

        Mockito.when(bookingService.getByCode("ABC123")).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/bookings/ABC123/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.slot.id").value(1))
                .andExpect(jsonPath("$.slot.name").value("MORNING"))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.code").value("ABC123"));
    }
}