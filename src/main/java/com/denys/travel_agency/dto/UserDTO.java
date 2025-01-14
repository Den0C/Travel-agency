package com.denys.travel_agency.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import static com.denys.travel_agency.dto.utils.ValidationTextConstant.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String userId;
    @NotBlank(message = WRONG_USERNAME_MESSAGE)
    @Pattern(regexp = "^[a-zA-Z0-9]+", message = WRONG_USERNAME_MESSAGE)
    private String username;

    @NotBlank(message = WRONG_PASSWORD_MESSAGE)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{7,30}$", message = WRONG_PASSWORD_MESSAGE)
    private String password;
    private String role;

   // private List<VoucherDTO> vouchers;

    @Pattern(regexp = "\\d+", message = WRONG_PHONE_NUMBER_MESSAGE)
    private String phoneNumber;

    private Double balance;
    @Builder.Default
    private boolean accountStatus = true;
}
