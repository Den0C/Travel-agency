package com.denys.travel_agency.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.denys.travel_agency.dto.utils.ValidationTextConstant.WRONG_PASSWORD_MESSAGE;
import static com.denys.travel_agency.dto.utils.ValidationTextConstant.WRONG_USERNAME_MESSAGE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
   @NotBlank(message = WRONG_USERNAME_MESSAGE)
   @Pattern(regexp = "^[a-zA-Z0-9]+", message = WRONG_USERNAME_MESSAGE)
   private String username;

   @NotBlank(message = WRONG_PASSWORD_MESSAGE)
   @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{7,30}$", message = WRONG_PASSWORD_MESSAGE)
   private String password;
}
