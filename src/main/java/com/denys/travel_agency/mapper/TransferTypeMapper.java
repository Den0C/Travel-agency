package com.denys.travel_agency.mapper;

import com.denys.travel_agency.dto.TransferTypeDTO;
import com.denys.travel_agency.model.TransferType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface TransferTypeMapper {
    TransferTypeDTO toTransferTypeDTO(TransferType any);

    TransferType toTransferType(TransferTypeDTO any);
}
