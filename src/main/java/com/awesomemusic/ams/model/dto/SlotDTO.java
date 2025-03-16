package com.awesomemusic.ams.model.dto;

import com.awesomemusic.ams.model.enumerations.SlotName;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlotDTO {
    private Long id;

    @NotNull(message = "Slot name is required")
    private SlotName name;
}
