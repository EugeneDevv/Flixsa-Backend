package com.smartspotsolutions.flixsa.enumeration;

/**
 * Created by Eugene Devv on 03 Jan, 2024
 */
public enum VerificationType {

    ACCOUNT("ACCOUNT"),
    STAFF("STAFF"),
    PASSWORD("PASSWORD"),
    ;

    private final String type;

    VerificationType(String type) { this.type = type;}

    public String getType(){
        return this.type.toLowerCase();
    }
}
