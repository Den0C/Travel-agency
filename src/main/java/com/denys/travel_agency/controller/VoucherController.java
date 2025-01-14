package com.denys.travel_agency.controller;

import com.denys.travel_agency.dto.MyApiResponse;
import com.denys.travel_agency.dto.VoucherDTO;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import com.denys.travel_agency.service.serviceinterface.VoucherService;
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
@RequestMapping("/agency/voucher")
@RequiredArgsConstructor
public class VoucherController {
    private final VoucherService voucherService;

    @PreAuthorize("hasAuthority('USER_CREATE')")
    @PostMapping("/create")
    public ResponseEntity<MyApiResponse<VoucherDTO>> createVoucher(@Valid @RequestBody VoucherDTO voucherDTO) {

        return new ResponseEntity<>(MyApiResponse
                .<VoucherDTO>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("Voucher is successfully created")
                .data(voucherService.create(voucherDTO))
                .build(), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN_DELETE')")
    @DeleteMapping("/delete/{voucherId}")
    public ResponseEntity<MyApiResponse<VoucherDTO>> deleteVoucherById(@PathVariable String voucherId)
            throws IllegalArgumentException, EntityNotFoundException {

        return ResponseEntity.ok(MyApiResponse
                .<VoucherDTO>builder()
                .statusMessage("Voucher with Id " + voucherId + " has been deleted")
                .statusCode(HttpStatus.OK.name())
                .data(voucherService.deleteById(UUID.fromString(voucherId)))
                .build());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_UPDATE','MANAGER_UPDATE')")
    @PatchMapping("/change/{voucherId}")
    public ResponseEntity<MyApiResponse<VoucherDTO>> updateVoucher(@PathVariable String voucherId, @RequestBody VoucherDTO voucherDTO)
            throws IllegalArgumentException, EntityNotFoundException {

        return ResponseEntity.ok(MyApiResponse
                .<VoucherDTO>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("Voucher is successfully updated")
                .data(voucherService.update(UUID.fromString(voucherId), voucherDTO))
                .build());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_READ','USER_READ','MANAGER_READ')")
    @GetMapping("/find-all-by-user-id/{userId}")
    public ResponseEntity<MyApiResponse<Page<VoucherDTO>>> findAllByUserId(
            @PathVariable String userId,
            Pageable pageable)
            throws IllegalArgumentException {

        return ResponseEntity.ok(MyApiResponse
                .<Page<VoucherDTO>>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("all vouchers by userId")
                .data(voucherService.findAllByUserId(UUID.fromString(userId), pageable))
                .build());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_UPDATE','MANAGER_UPDATE')")
    @PatchMapping("/update-status/{voucherId}")
    public ResponseEntity<MyApiResponse<VoucherDTO>> changeVoucher(@PathVariable String voucherId, @RequestBody VoucherDTO voucherDTO)
            throws IllegalArgumentException, EntityNotFoundException {

        return ResponseEntity.ok(MyApiResponse
                .<VoucherDTO>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("Voucher status is successfully changed")
                .data(voucherService.changeHotStatus(UUID.fromString(voucherId), voucherDTO))
                .build());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_READ','MANAGER_READ')")
    @GetMapping("/get-all")
    public ResponseEntity<MyApiResponse<Page<VoucherDTO>>> findAll(
            Pageable pageable)
            throws IllegalArgumentException {

        return ResponseEntity.ok(MyApiResponse
                .<Page<VoucherDTO>>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("all vouchers")
                .data(voucherService.findAll(pageable))
                .build());

    }
}