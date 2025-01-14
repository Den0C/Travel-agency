package com.denys.travel_agency.controller;

import com.denys.travel_agency.dto.MyApiResponse;
import com.denys.travel_agency.dto.TourDTO;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import com.denys.travel_agency.service.serviceinterface.TourService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/agency/tour")
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;
    @PreAuthorize("hasAuthority('ADMIN_CREATE')")
    @PostMapping("/create")
    public ResponseEntity<MyApiResponse<TourDTO>> createVoucher(@Valid @RequestBody TourDTO tourDTO) throws IllegalArgumentException {
        return new ResponseEntity<>(MyApiResponse.<TourDTO>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("Tour is successfully created!")
                .data(tourService.create(tourDTO))
                .build(), HttpStatus.CREATED);

    }
    @PreAuthorize("hasAnyAuthority('ADMIN_READ','USER_READ','MANAGER_READ')")
    @GetMapping("/get/{tourId}")
    public ResponseEntity<MyApiResponse<TourDTO>> findById(@PathVariable String tourId) throws IllegalArgumentException, EntityNotFoundException {
        UUID uuidTourID = UUID.fromString(tourId);
        return ResponseEntity.ok(
                MyApiResponse.<TourDTO>builder()
                        .statusMessage("Tour is successfully found!")
                        .statusCode(HttpStatus.OK.name())
                        .data(tourService.getById(uuidTourID))
                        .build());

    }
    @PreAuthorize("hasAuthority('ADMIN_DELETE')")
    @DeleteMapping("/delete/{tourId}")
    public ResponseEntity<MyApiResponse<TourDTO>> deleteById(@PathVariable String tourId) throws IllegalArgumentException, EntityNotFoundException {
        UUID uuidTourID = UUID.fromString(tourId);
        return ResponseEntity.ok(
                MyApiResponse.<TourDTO>builder()
                        .statusMessage("Tour is successfully deleted!")
                        .statusCode(HttpStatus.OK.name())
                        .data(tourService.deleteById(uuidTourID))
                        .build());

    }
    @PreAuthorize("hasAuthority('ADMIN_UPDATE')")
    @PatchMapping("/update/{tourId}")
    public ResponseEntity<MyApiResponse<TourDTO>> update(@PathVariable String tourId, @Valid @RequestBody TourDTO tourDTO) throws IllegalArgumentException, EntityNotFoundException {
        UUID uuidTourID = UUID.fromString(tourId);
        return ResponseEntity.ok(
                MyApiResponse.<TourDTO>builder()
                        .statusMessage("Tour is successfully updated!")
                        .statusCode(HttpStatus.OK.name())
                        .data(tourService.update(uuidTourID, tourDTO))
                        .build());


    }
    @PreAuthorize("hasAnyAuthority('ADMIN_READ','USER_READ','MANAGER_READ')")
    @GetMapping("/get-all")
    public ResponseEntity<MyApiResponse<Page<TourDTO>>> findAllSortedBy(
            Pageable pageable,
            @RequestParam(defaultValue = "") String nameFilter

    ) throws IllegalArgumentException {
        return ResponseEntity.ok(
                MyApiResponse.<Page<TourDTO>>builder()
                        .statusMessage("Tour is successfully updated!")
                        .statusCode(HttpStatus.OK.name())
                        .data(tourService.getAllTours(pageable, nameFilter))
                        .build());


    }

    @PreAuthorize("hasAnyAuthority('ADMIN_READ','USER_READ','MANAGER_READ')")
    @GetMapping("/get-all-available")
    public ResponseEntity<MyApiResponse<Page<TourDTO>>> findAllAvailableSortedBy(
            Pageable pageable,
            @RequestParam(defaultValue = "") String nameFilter

    ) throws IllegalArgumentException {
        return ResponseEntity.ok(
                MyApiResponse.<Page<TourDTO>>builder()
                        .statusMessage("Tour is successfully updated!")
                        .statusCode(HttpStatus.OK.name())
                        .data(tourService.getAllAvailableTours(pageable, nameFilter))
                        .build());


    }
}
