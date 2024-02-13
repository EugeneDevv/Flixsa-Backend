package com.flixsa.api.services;

import com.flixsa.api.dto.JwtAuthenticationResponse;
import com.flixsa.api.dto.RefreshTokenRequest;
import com.flixsa.api.dto.SignUpRequest;
import com.flixsa.api.dto.SigninRequest;
import com.flixsa.api.entities.User;

/**
 * Created by Eugene Devv on 13 Feb, 2024
 */
public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
