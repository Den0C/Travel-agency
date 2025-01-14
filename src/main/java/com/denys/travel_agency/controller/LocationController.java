package com.denys.travel_agency.controller;

import com.denys.travel_agency.dto.LocationDTO;
import com.denys.travel_agency.dto.MyApiResponse;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import com.denys.travel_agency.service.serviceinterface.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/agency/location")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
    private final MessageSource messageSource;
    @Operation(
            summary = "Create a new location",
            description = "Allows admins to create a new location.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Location created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MyApiResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "403", description = "Access denied")
            }
    )
    @PreAuthorize("hasAuthority('ADMIN_CREATE')")
    @PostMapping("/create")
    public ResponseEntity<MyApiResponse<LocationDTO>> createLocation(@Valid @RequestBody LocationDTO locationDTO)
            throws IllegalArgumentException {
        return new ResponseEntity<>(MyApiResponse.<LocationDTO>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("Location is successfully created")
                .data(locationService.create(locationDTO))
                .build(), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update an existing location",
            description = "Allows admins to update the details of an existing location.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Location updated successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MyApiResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "404", description = "Location not found"),
                    @ApiResponse(responseCode = "403", description = "Access denied")
            }
    )
    @PreAuthorize("hasAuthority('ADMIN_UPDATE')")
    @PatchMapping("/update/{locationId}")
    public ResponseEntity<MyApiResponse<LocationDTO>> updateLocation(
            @Parameter(description = "ID of the location to update") @PathVariable String locationId,
            @Valid @RequestBody LocationDTO locationDTO) throws IllegalArgumentException, EntityNotFoundException {
        UUID uuid = UUID.fromString(locationId);
        return ResponseEntity.ok(
                MyApiResponse.<LocationDTO>builder()
                        .statusMessage("Location is successfully updated!")
                        .statusCode(HttpStatus.OK.name())
                        .data(locationService.update(uuid, locationDTO))
                        .build());
    }

    @Operation(
            summary = "Get location details",
            description = "Allows users or admins to fetch the details of a specific location by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Location found successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MyApiResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Location not found"),
                    @ApiResponse(responseCode = "403", description = "Access denied")
            }
    )
    @PreAuthorize("hasAnyAuthority('ADMIN_READ','USER_READ','MANAGER_READ')")
    @GetMapping("/get/{locationId}")
    public ResponseEntity<MyApiResponse<LocationDTO>> findById(
            @Parameter(description = "ID of the location to retrieve") @PathVariable String locationId)
            throws IllegalArgumentException, EntityNotFoundException {
        UUID uuid = UUID.fromString(locationId);
        return ResponseEntity.ok(
                MyApiResponse.<LocationDTO>builder()
                        .statusMessage("Location is successfully found!")
                        .statusCode(HttpStatus.OK.name())
                        .data(locationService.findById(uuid))
                        .build());
    }

    @Operation(
            summary = "Delete a location",
            description = "Allows admins to delete a specific location by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Location deleted successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MyApiResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Location not found"),
                    @ApiResponse(responseCode = "403", description = "Access denied")
            }
    )
    @PreAuthorize("hasAuthority('ADMIN_DELETE')")
    @DeleteMapping("/delete/{locationId}")
    public ResponseEntity<MyApiResponse<LocationDTO>> deleteById(
            @Parameter(description = "ID of the location to delete") @PathVariable String locationId
            //@RequestHeader(name = "Accept-Language", defaultValue = "en") String lang
    )
            throws EntityNotFoundException {

        UUID uuid = UUID.fromString(locationId);
        return ResponseEntity.ok(
                MyApiResponse.<LocationDTO>builder()
                        .statusMessage("location.deleted.success")
                        .statusCode(HttpStatus.OK.name())
                        .data(locationService.deleteById(uuid))
                        .build());
    }

    @PreAuthorize("hasAuthority('ADMIN_READ')")
    @GetMapping("/get-all")
    public ResponseEntity<MyApiResponse<Page<LocationDTO>>> getAllLocation(
            Pageable pageable,
            @RequestParam(defaultValue = "") String nameFilter) {

        return ResponseEntity.ok(MyApiResponse.<Page<LocationDTO>>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("Location Type was obtained successfully")
                .data(locationService.getAllLocation(pageable,nameFilter))
                .build());
    }
}
