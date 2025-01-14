package com.denys.travel_agency.service;

import com.denys.travel_agency.dto.TransferTypeDTO;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import com.denys.travel_agency.mapper.TransferTypeMapper;
import com.denys.travel_agency.repository.TransferTypeRepository;
import com.denys.travel_agency.service.serviceinterface.TransferTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferTypeServiceImpl implements TransferTypeService {
    private final TransferTypeMapper transferTypeMapper;
    private final TransferTypeRepository transferTypeRepository;

    @Override
    public TransferTypeDTO create(TransferTypeDTO transferTypeDTO) {
        try {
            return transferTypeMapper.toTransferTypeDTO(transferTypeRepository
                    .save(transferTypeMapper.toTransferType(transferTypeDTO)));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Entity is null!");
        }
    }

    @Override
    public TransferTypeDTO update(UUID transferTypeId, TransferTypeDTO transferTypeDTO) throws EntityNotFoundException {
        if (transferTypeRepository.existsById(transferTypeId)) {
            return transferTypeMapper.toTransferTypeDTO(
                    transferTypeRepository.save(transferTypeMapper.toTransferType(transferTypeDTO)));
        }
        throw new EntityNotFoundException("TransferType with current id is absent");
    }

    @Override
    public TransferTypeDTO deleteById(UUID transferTypeId) throws EntityNotFoundException {
        if (transferTypeRepository.existsById(transferTypeId)) {
            TransferTypeDTO deletedTransferTypeDTO = transferTypeMapper.toTransferTypeDTO(transferTypeRepository.findById(transferTypeId).get());
            transferTypeRepository.deleteById(transferTypeId);
            return deletedTransferTypeDTO;
        }
        throw new EntityNotFoundException("TransferType with current id is absent");
    }

    @Override
    public TransferTypeDTO findById(UUID transferTypeId) throws EntityNotFoundException {
        if (transferTypeRepository.existsById(transferTypeId)) {
            return transferTypeMapper.toTransferTypeDTO(transferTypeRepository.findById(transferTypeId).get());
        }
        throw new EntityNotFoundException("TransferType with current id is absent");
    }

    @Override
    public Page<TransferTypeDTO> getAllTransferTypes(Pageable pageable, String nameFilter) {
        try {
            return transferTypeRepository.findAllByNameStartingWith(nameFilter, pageable).map(transferTypeMapper::toTransferTypeDTO);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad parameters!");
        }
    }


}
