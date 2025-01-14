package com.denys.travel_agency.repository;

import com.denys.travel_agency.model.TransferType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferTypeRepository extends JpaRepository<TransferType, UUID> {
    Page<TransferType> findAllByNameStartingWith(String name, Pageable pageable);
}
