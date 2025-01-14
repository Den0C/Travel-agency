package com.denys.travel_agency.auth;

import com.denys.travel_agency.exeption.EntityNotFoundException;
import com.denys.travel_agency.model.User;
import com.denys.travel_agency.repository.UserRepository;
import com.denys.travel_agency.token.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws EntityNotFoundException {
        String username = authenticationRequest.getUsername();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, authenticationRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(token)
                .roles(user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .build();
    }

}
