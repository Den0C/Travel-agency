package com.denys.travel_agency.repository;

import com.denys.travel_agency.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TourRepository extends JpaRepository<Tour, UUID> {

    Page<Tour> findAllByTitleStartingWith(String title, Pageable pageable);

    Page<Tour> findAllByTitleStartingWithAndAvailableSeatsGreaterThan(String title, Pageable pageable, int availableSeats);

}
