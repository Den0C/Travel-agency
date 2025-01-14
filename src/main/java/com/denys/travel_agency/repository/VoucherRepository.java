package com.denys.travel_agency.repository;

import com.denys.travel_agency.model.User;
import com.denys.travel_agency.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VoucherRepository extends JpaRepository<Voucher, UUID> {
    Page<Voucher> findAllByUser(User user, Pageable pageable);

}
