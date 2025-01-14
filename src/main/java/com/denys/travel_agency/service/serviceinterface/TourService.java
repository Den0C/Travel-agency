package com.denys.travel_agency.service.serviceinterface;

import com.denys.travel_agency.dto.TourDTO;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TourService {
    TourDTO create(TourDTO tourDTO) throws IllegalArgumentException;

    TourDTO deleteById(UUID tourId) throws EntityNotFoundException;

    TourDTO update(UUID tourId, TourDTO tourDTO) throws EntityNotFoundException;

    TourDTO getById(UUID tourId) throws EntityNotFoundException;

    Page<TourDTO> getAllTours(Pageable pageable, String nameFilter) throws IllegalArgumentException;

    void decrementSeat(UUID tourId);

    Page<TourDTO> getAllAvailableTours(Pageable pageable, String nameFilter);
}
