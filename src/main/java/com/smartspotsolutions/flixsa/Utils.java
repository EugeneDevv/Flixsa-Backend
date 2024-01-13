package com.smartspotsolutions.flixsa;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by Eugene Devv on 03 Jan, 2024
 */
public class Utils {
    public static String getVerificationURL(String key, String type){



        return ServletUriComponentsBuilder.fromCurrentContextPath().path("user/verify"+ type + "/"+key).toUriString();
    }
}
