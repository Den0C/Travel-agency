package com.denys.travel_agency.dto;

//import com.epam.finaltask.dto.group.OnChange;
//import com.epam.finaltask.dto.group.OnCreate;
//import com.epam.finaltask.dto.group.OnUpdate;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDTO {

    private String voucherId;
    private String status;
    private LocalDate purchaseDate;
    @NotBlank(message = "Id is incorrect")
    private String user;
    @NotBlank(message = "Id is incorrect")
    private String  tour;
}
