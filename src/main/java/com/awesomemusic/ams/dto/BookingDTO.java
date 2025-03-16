package com.awesomemusic.ams.dto;

import com.awesomemusic.ams.model.Slot;
import com.awesomemusic.ams.model.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookingDTO {
    private Long id;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Time slot is required")
    private Slot slot;

    private Status status;

    private BookingDTO(Builder builder) {
        this.id = builder.id;
        this.customerName = builder.customerName;
        this.email = builder.email;
        this.slot = builder.slot;
        this.status = builder.status;
    }

    public BookingDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String customerName;
        private String email;
        private Slot slot;
        private Status status;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder slot(Slot slot) {
            this.slot = slot;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public BookingDTO build() {
            return new BookingDTO(this);
        }
    }
}
