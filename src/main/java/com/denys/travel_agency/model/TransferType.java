package com.denys.travel_agency.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transfer_type")
public class TransferType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID transferTypeId;
    private String name;

}
