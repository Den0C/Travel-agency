package com.denys.travel_agency.service.serviceinterface;

import com.denys.travel_agency.dto.TransferTypeDTO;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TransferTypeService{
    TransferTypeDTO create(TransferTypeDTO transferTypeDTO) throws IllegalArgumentException;
    TransferTypeDTO update(UUID transferTypeId, TransferTypeDTO transferTypeDTO) throws EntityNotFoundException;
    TransferTypeDTO deleteById(UUID transferTypeId) throws EntityNotFoundException;
    TransferTypeDTO findById(UUID transferTypeId) throws EntityNotFoundException;
    Page<TransferTypeDTO> getAllTransferTypes(Pageable pageable, String nameFilter);

}
