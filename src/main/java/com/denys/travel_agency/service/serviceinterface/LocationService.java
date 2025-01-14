package com.denys.travel_agency.service.serviceinterface;

import com.denys.travel_agency.dto.LocationDTO;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface LocationService {
    LocationDTO create(LocationDTO locationDTO) throws IllegalArgumentException;
    LocationDTO update(UUID locationId, LocationDTO locationDTO) throws EntityNotFoundException;
    LocationDTO deleteById(UUID locationId) throws EntityNotFoundException;
    LocationDTO findById(UUID locationId) throws EntityNotFoundException;
    Page<LocationDTO> getAllLocation(Pageable pageable, String nameFilter);
}
