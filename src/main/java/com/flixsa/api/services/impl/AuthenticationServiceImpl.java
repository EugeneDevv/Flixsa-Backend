package com.flixsa.api.services.impl;

import com.flixsa.api.dto.JwtAuthenticationResponse;
import com.flixsa.api.dto.RefreshTokenRequest;
import com.flixsa.api.dto.SignUpRequest;
import com.flixsa.api.dto.SigninRequest;
import com.flixsa.api.entities.Role;
import com.flixsa.api.entities.User;
import com.flixsa.api.exception.UserAlreadyExistsException;
import com.flixsa.api.exception.UserNotFoundException;
import com.flixsa.api.repository.UserRepository;
import com.flixsa.api.services.AuthenticationService;
import com.flixsa.api.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

import static com.flixsa.api.utils.StringConstants.INVALID_EMAIL_OR_PASSWORD;
import static com.flixsa.api.utils.StringConstants.userWithEmailExistsMessage;

/**
 * Created by Eugene Devv on 13 Feb, 2024
 */

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public User signUp(SignUpRequest signUpRequest) {
        Optional<User> user = userRepository.findByEmail(signUpRequest.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException(userWithEmailExistsMessage(signUpRequest.getEmail()));
        }
        User newUser = new User();
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setFirstName(signUpRequest.getFirstName());
        newUser.setLastName(signUpRequest.getLastName());
        newUser.setRole(Role.USER);
        newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(newUser);
    }

    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new UserNotFoundException(INVALID_EMAIL_OR_PASSWORD));

        if (!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),
                        signinRequest.getPassword()));

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;

    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getRefreshToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        if (jwtService.isTokenValid(refreshTokenRequest.getRefreshToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getRefreshToken());

            return jwtAuthenticationResponse;
        }

        return null;
    }
}
