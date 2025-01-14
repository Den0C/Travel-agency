package com.denys.travel_agency.service.serviceinterface;

import com.denys.travel_agency.dto.VoucherDTO;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VoucherService {
    VoucherDTO create(VoucherDTO voucherDTO) throws IllegalArgumentException;

    VoucherDTO update(UUID voucherId, VoucherDTO voucherDTO) throws EntityNotFoundException;

    VoucherDTO deleteById(UUID voucherId) throws EntityNotFoundException;

    VoucherDTO changeHotStatus(UUID voucherId, VoucherDTO voucherDTO) throws EntityNotFoundException;

    Page<VoucherDTO> findAllByUserId(UUID userId, Pageable pageable);

    Page<VoucherDTO> findAll(Pageable pageable);
}
