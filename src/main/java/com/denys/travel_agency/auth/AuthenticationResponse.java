package com.denys.travel_agency.auth;

import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String status;
    private String statusMessage;
    private String accessToken;
    private List<String> roles;
    public AuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
