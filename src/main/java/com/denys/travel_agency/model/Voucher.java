package com.denys.travel_agency.model;

import com.denys.travel_agency.enums.VoucherStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID voucherId;

    @Enumerated(EnumType.STRING)
    private VoucherStatus status;
    private LocalDate purchaseDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tourId", referencedColumnName = "tourId")
    private Tour tour;

}
