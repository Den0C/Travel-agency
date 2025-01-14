package com.denys.travel_agency.service;

import com.denys.travel_agency.dto.VoucherDTO;
import com.denys.travel_agency.enums.VoucherStatus;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import com.denys.travel_agency.mapper.VoucherMapper;
import com.denys.travel_agency.model.User;
import com.denys.travel_agency.repository.VoucherRepository;
import com.denys.travel_agency.service.serviceinterface.TourService;
import com.denys.travel_agency.service.serviceinterface.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {
    private final VoucherRepository voucherRepository;
    private final VoucherMapper voucherMapper;
    private final TourService tourService;

    public VoucherDTO create(VoucherDTO voucherDTO) throws IllegalArgumentException {
        try {
            voucherDTO.setStatus(VoucherStatus.REGISTERED.name());
            voucherDTO.setPurchaseDate(LocalDate.now());
            tourService.decrementSeat(UUID.fromString(voucherDTO.getTour()));

            return voucherMapper.toVoucherDTO(voucherRepository.save(voucherMapper.toVoucher(voucherDTO)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Entity is null!");
        }
    }

    @Override
    public VoucherDTO update(UUID voucherId, VoucherDTO voucherDTO) throws EntityNotFoundException {
        if (voucherRepository.existsById(voucherId)) {
            return voucherMapper.toVoucherDTO(voucherRepository.save(voucherMapper.toVoucher(voucherDTO)));
        }
        throw new EntityNotFoundException("Voucher with current id is absent");
    }

    @Override
    public VoucherDTO deleteById(UUID voucherId) throws EntityNotFoundException {
        if (voucherRepository.existsById(voucherId)) {
            VoucherDTO deletedVoucherDTO = voucherMapper.toVoucherDTO(voucherRepository.findById(voucherId).get());
            voucherRepository.deleteById(voucherId);
            return deletedVoucherDTO;
        }
        throw new EntityNotFoundException("Voucher with current id is absent");

    }

    @Override
    public VoucherDTO changeHotStatus(UUID voucherId, VoucherDTO voucherDTO) throws EntityNotFoundException {
        if (voucherRepository.existsById(voucherId)) {
            return voucherMapper.toVoucherDTO(voucherRepository.save(voucherMapper.toVoucher(voucherDTO)));
        }
        throw new EntityNotFoundException("Voucher with current id is absent");

    }

    @Override
    public Page<VoucherDTO> findAllByUserId(UUID user, Pageable pageable)
            throws IllegalArgumentException {
        try {
            return voucherRepository.findAllByUser(User.builder().userId(user).build(), pageable)
                    .map(voucherMapper::toVoucherDTO);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad parameters: " + e.getMessage(), e);
        }
    }

    @Override
    public Page<VoucherDTO> findAll(Pageable pageable) throws IllegalArgumentException {
        try {
            return voucherRepository.findAll(pageable).map(voucherMapper::toVoucherDTO);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad parameters!");
        }
    }


}
