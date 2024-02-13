package com.flixsa.api.dto;

import lombok.Data;

/**
 * Created by Eugene Devv on 13 Feb, 2024
 */

@Data
public class SignUpRequest {
    private String firstName;
    private String password;
    private String email;
    private String lastName;
}
