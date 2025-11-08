package com.jeremiah.daapi.dto;

import java.util.UUID;

import com.jeremiah.daapi.entity.DeviceStatus;

import lombok.Data;

// annotation from Lombok - shortcut that automatically generates a lot of boilerplate code for you at compile time e.g. getter/setter, toString, equals, hashCode
@Data
public class DeviceResponseDTO {
    private UUID deviceId;
    private Integer assetId;
    private String make;
    private String model;
    private Integer releaseYear;
    private String os;
    private String osVersion;
    private String capabilities;
    private String webdriverConfig;
    private String region;
    private DeviceStatus status;
    private String raveConfig;
}


