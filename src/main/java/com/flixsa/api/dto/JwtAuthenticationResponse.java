package com.flixsa.api.dto;

import lombok.Data;

/**
 * Created by Eugene Devv on 13 Feb, 2024
 */

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
}
