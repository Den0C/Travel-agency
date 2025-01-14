package com.denys.travel_agency.repository;

import com.denys.travel_agency.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
    Page<Location> findAllByCountryStartingWith(String country, Pageable pageable);
}
