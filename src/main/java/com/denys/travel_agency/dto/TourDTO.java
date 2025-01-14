package com.denys.travel_agency.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourDTO {

    private String tourId;

    @NotEmpty(message = "Title must not be empty or null")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;

    @NotEmpty(message = "Description must not be empty or null")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotEmpty(message = "Location must not be empty or null")
    private String location;

    @NotEmpty(message = "Tour type must not be empty or null")
    private String tourType;

    @NotEmpty(message = "Transfer type must not be empty or null")
    private String transferType;

    @NotEmpty(message = "Hotel type must not be empty or null")
    private String hotelType;

    @NotNull(message = "Available seats must not be null")
    @Min(value = 0, message = "Available seats must be at least 0")
    private Integer availableSeats;

    @NotNull(message = "Start time must not be null")
    @FutureOrPresent(message = "Start time must be in the present or future")
    private LocalDate startTime;

    @NotNull(message = "End time must not be null")
    @FutureOrPresent(message = "End time must be in the present or future")
    private LocalDate endTime;

    private boolean isHot;
}
