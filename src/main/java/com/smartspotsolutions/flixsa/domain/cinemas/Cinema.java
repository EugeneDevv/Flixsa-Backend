package com.smartspotsolutions.flixsa.domain.cinemas;

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
public class Cinema {
    private Long id;
    @NotEmpty(message="Name cannot be empty")
    private String name;
    @NotEmpty(message="Email cannot be empty")
    @Email(message="Invalid email. Please enter a valid email address")
    private String email;
    private String profileImage;
    private String phone;
    private String country;
    private String state;
    private String city;
    private String street;
    private Point2D location;
    private String postalCode;
    private String bio;
    private boolean enabled;
    private boolean isNotLocked;
    private boolean isUsingMFA;
    private LocalDateTime createdAt;
}
