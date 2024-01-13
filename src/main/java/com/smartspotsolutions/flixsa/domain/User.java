package com.smartspotsolutions.flixsa.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.awt.geom.Point2D;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

/**
 * Created by Eugene Devv on 20 Dec, 2023
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
public class User {
    private Long id;
    @NotEmpty(message="First name cannot be empty")
    private String firstName;
    @NotEmpty(message="Last name cannot be empty")
    private String lastName;
    @NotEmpty(message="Email cannot be empty")
    @Email(message="Invalid email. Please enter a valid email address")
    private String email;
    private String profileImage;
    @NotEmpty(message="Password cannot be empty")
    private String password;
    private String phone;
    private String country;
    private String state;
    private String city;
    private String street;
    private Point2D location;
    private boolean enabled;
    private boolean isNotLocked;
    private boolean isUsingMFA;
    private LocalDateTime createdAt;
}
