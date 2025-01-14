package com.denys.travel_agency.controller;


import com.denys.travel_agency.dto.MyApiResponse;
import com.denys.travel_agency.dto.TransferTypeDTO;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import com.denys.travel_agency.service.serviceinterface.TransferTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/agency/transfer-type")
@RequiredArgsConstructor
public class TransferTypeController {
    private final TransferTypeService transferTypeService;
    @PreAuthorize("hasAuthority('ADMIN_CREATE')")
    @PostMapping("/create")
    public ResponseEntity<MyApiResponse<TransferTypeDTO>> createTransferType(@Valid @RequestBody TransferTypeDTO transferTypeDTO)
            throws IllegalArgumentException {
        return ResponseEntity.ok(MyApiResponse.<TransferTypeDTO>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("TransferType is successfully created")
                .data(transferTypeService.create(transferTypeDTO))
                .build());
    }
    @PreAuthorize("hasAuthority('ADMIN_UPDATE')")
    @PatchMapping("/update/{transferTypeId}")
    public ResponseEntity<MyApiResponse<TransferTypeDTO>> updateLocation(
            @PathVariable String transferTypeId,
            @Valid @RequestBody TransferTypeDTO transferTypeDTO) throws IllegalArgumentException, EntityNotFoundException {
        UUID uuid = UUID.fromString(transferTypeId);
        return ResponseEntity.ok(
                MyApiResponse.<TransferTypeDTO>builder()
                        .statusMessage("TransferType is successfully updated!")
                        .statusCode(HttpStatus.OK.name())
                        .data(transferTypeService.update(uuid, transferTypeDTO))
                        .build());

    }
    @PreAuthorize("hasAnyAuthority('ADMIN_READ','USER_READ','MANAGER_READ')")
    @GetMapping("/get/{transferTypeId}")
    public ResponseEntity<MyApiResponse<TransferTypeDTO>> findById(@PathVariable String transferTypeId)
            throws IllegalArgumentException, EntityNotFoundException {
        UUID uuid = UUID.fromString(transferTypeId);
        return ResponseEntity.ok(
                MyApiResponse.<TransferTypeDTO>builder()
                        .statusMessage("TransferType is successfully found!")
                        .statusCode(HttpStatus.OK.name())
                        .data(transferTypeService.findById(uuid))
                        .build());

    }
    @PreAuthorize("hasAuthority('ADMIN_DELETE')")
    @DeleteMapping("/delete/{transferTypeId}")
    public ResponseEntity<MyApiResponse<TransferTypeDTO>> deleteById(@PathVariable String transferTypeId)
            throws EntityNotFoundException{
        UUID uuid = UUID.fromString(transferTypeId);
        return ResponseEntity.ok(
                MyApiResponse.<TransferTypeDTO>builder()
                        .statusMessage("TransferType is successfully deleted!")
                        .statusCode(HttpStatus.OK.name())
                        .data(transferTypeService.deleteById(uuid))
                        .build());

    }

    @PreAuthorize("hasAuthority('ADMIN_READ')")
    @GetMapping("/get-all")
    public ResponseEntity<MyApiResponse<Page<TransferTypeDTO>>> getAllTransferType(
            Pageable pageable,
            @RequestParam(defaultValue = "") String nameFilter

    ) throws IllegalArgumentException {
        return ResponseEntity.ok(MyApiResponse.<Page<TransferTypeDTO>>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("Transfer Type was obtained successfully")
                .data(transferTypeService.getAllTransferTypes(pageable,nameFilter))
                .build());
    }
}

