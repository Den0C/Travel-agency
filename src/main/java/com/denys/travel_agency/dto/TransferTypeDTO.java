package com.denys.travel_agency.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferTypeDTO {
    private String transferTypeId;

    @NotEmpty(message = "name must not be empty or null")
    @Size(min = 1, max = 255, message = "name must be between 1 and 255 characters")
    private String name;
}
