package com.denys.travel_agency.model;

import com.denys.travel_agency.enums.HotelType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tourId;
    private String title;
    private String description;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId")
    private Location location;
    private String tourType;

    @ManyToOne
    @JoinColumn(name = "transferType", referencedColumnName = "transferTypeId")
    private TransferType transferType;

    @Enumerated(EnumType.STRING)
    private HotelType hotelType;
    private Integer availableSeats;
    private LocalDate startTime;
    private LocalDate endTime;
    private boolean isHot;
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Voucher> vouchers;
}
