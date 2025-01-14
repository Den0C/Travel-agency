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
public class LocationDTO {

    private String locationId;

    @NotEmpty(message = "Country must not be empty or null")
    private String country;

    @NotEmpty(message = "City must not be empty or null")
    private String city;

    @NotEmpty(message = "Address must not be empty or null")
    @Size(min = 1, max = 255, message = "Address must be between 1 and 255 characters")
    private String address;
}
