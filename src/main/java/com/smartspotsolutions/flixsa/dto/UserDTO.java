package com.smartspotsolutions.flixsa.dto;

import lombok.Data;

import java.awt.geom.Point2D;
import java.time.LocalDateTime;

/**
 * Created by Eugene Devv on 12 Jan, 2024
 */

@Data
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String profileImage;
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
