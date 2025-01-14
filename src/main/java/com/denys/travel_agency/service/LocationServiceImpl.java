package com.denys.travel_agency.service;

import com.denys.travel_agency.dto.LocationDTO;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import com.denys.travel_agency.mapper.LocationMapper;
import com.denys.travel_agency.repository.LocationRepository;
import com.denys.travel_agency.service.serviceinterface.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationMapper locationMapper;
    private final LocationRepository locationRepository;

    @Override
    public LocationDTO create(LocationDTO locationDTO) {
        try {
            return locationMapper.toLocationDTO(
                    locationRepository.save(locationMapper.toLocation(locationDTO)));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Entity is null!");
        }
    }

    @Override
    public LocationDTO update(UUID locationId, LocationDTO locationDTO) throws EntityNotFoundException {
        if (locationRepository.existsById(locationId)) {
            return locationMapper.toLocationDTO(locationRepository.save(locationMapper.toLocation(locationDTO)));
        }
        throw new EntityNotFoundException("Location with current id is absent");
    }

    @Override
    public LocationDTO deleteById(UUID locationId) throws EntityNotFoundException {
        if (locationRepository.existsById(locationId)) {
            LocationDTO deletedLocationDTO = locationMapper.toLocationDTO(locationRepository.findById(locationId).get());
            locationRepository.deleteById(locationId);
            return deletedLocationDTO;
        }
        throw new EntityNotFoundException("Location with current id is absent");
    }

    @Override
    public LocationDTO findById(UUID locationId) throws EntityNotFoundException {
        if (locationRepository.existsById(locationId)) {
            return locationMapper.toLocationDTO(locationRepository.findById(locationId).get());
        }
        throw new EntityNotFoundException("Location with current id is absent");

    }

    @Override
    public Page<LocationDTO> getAllLocation(Pageable pageable, String nameFilter) {
        try {
            return locationRepository.findAllByCountryStartingWith(nameFilter, pageable).map(locationMapper::toLocationDTO);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad parameters!");
        }
    }
}
