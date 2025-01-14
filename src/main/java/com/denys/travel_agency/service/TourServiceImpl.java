package com.denys.travel_agency.service;

import com.denys.travel_agency.dto.TourDTO;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import com.denys.travel_agency.mapper.TourMapper;
import com.denys.travel_agency.model.Tour;
import com.denys.travel_agency.repository.TourRepository;
import com.denys.travel_agency.service.serviceinterface.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final TourMapper tourMapper;

    @Override
    public TourDTO create(TourDTO tourDTO) {
        try {
            return tourMapper.toTourDTO(tourRepository.save(tourMapper.toTour(tourDTO)));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Entity is null!");
        }
    }

    @Override
    public TourDTO deleteById(UUID tourId) throws EntityNotFoundException {
        if (tourRepository.existsById(tourId)) {
            TourDTO deletedTourDTO = tourMapper.toTourDTO(tourRepository.findById(tourId).get());
            tourRepository.deleteById(tourId);
            return deletedTourDTO;
        }
        throw new EntityNotFoundException("Tour with current id is absent");

    }

    @Override
    public TourDTO update(UUID tourId, TourDTO tourDTO) throws EntityNotFoundException {
        if (tourRepository.existsById(tourId)) {
            Tour tour =  tourMapper.toTour(tourDTO);
            tour.setVouchers(tourRepository.findById(tourId).get().getVouchers());
            return tourMapper.toTourDTO(tourRepository.save(tour));
        }
        throw new EntityNotFoundException("Tour with current id is absent");
    }

    @Override
    public TourDTO getById(UUID tourId) throws EntityNotFoundException {
        if (tourRepository.existsById(tourId)) {
            return tourMapper.toTourDTO(tourRepository.findById(tourId).get());
        }
        throw new EntityNotFoundException("Tour with current id is absent");
    }

    @Override
    public Page<TourDTO> getAllTours(Pageable pageable, String nameFilter) {
        try {
            return tourRepository.findAllByTitleStartingWith(nameFilter,pageable).map(tourMapper::toTourDTO);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad parameters!");
        }
    }
    @Override
    public Page<TourDTO> getAllAvailableTours(Pageable pageable, String nameFilter) {
        try {
            return tourRepository.findAllByTitleStartingWithAndAvailableSeatsGreaterThan(nameFilter,pageable,0)
                    .map(tourMapper::toTourDTO);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad parameters!");
        }
    }
    @Override
    public void decrementSeat(UUID tourId) {
        Tour tour = tourRepository.findById(tourId).get();
        tour.setAvailableSeats(tour.getAvailableSeats() - 1);
        tourRepository.save(tour);
    }

}
