package com.denys.travel_agency.controller;

import com.denys.travel_agency.auth.AuthenticationRequest;
import com.denys.travel_agency.auth.AuthenticationResponse;
import com.denys.travel_agency.auth.AuthenticationService;
import com.denys.travel_agency.dto.MyApiResponse;
import com.denys.travel_agency.dto.UserDTO;
import com.denys.travel_agency.exeption.EntityAlreadyExistsException;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import com.denys.travel_agency.exeption.StatusCodes;
import com.denys.travel_agency.service.serviceinterface.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/agency/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final MessageSource messageSource;

    @PostMapping("/login")
    public ResponseEntity<MyApiResponse<Map<String, String>>> login(
            @Valid @RequestBody AuthenticationRequest request
            //@RequestHeader(name = "Accept-Language", defaultValue = "en") Locale locale
            )
            throws EntityNotFoundException {

        AuthenticationResponse authResponse = authenticationService.authenticate(request);
        return ResponseEntity.accepted().body(MyApiResponse
                .<Map<String, String>>builder()
                .statusCode(StatusCodes.OK.name())
                .statusMessage("authentication.register.success")
                .data((Map.of("access_token", authResponse.getAccessToken())))
                .build());
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
            throws EntityNotFoundException {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<MyApiResponse<UserDTO>> register(@Valid @RequestBody UserDTO userDTO)
            throws EntityAlreadyExistsException {
        UserDTO registeredUser = userService.register(userDTO);
        MyApiResponse<UserDTO> response = new MyApiResponse<>(
                HttpStatus.OK.name(), "User is successfully registered", registeredUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
