package com.jeremiah.daapi.entity;

import jakarta.persistence.Entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "devices", schema = "devices_schema")
public class Device {
	@Id
	@GeneratedValue
	@Column(name = "device_id", columnDefinition = "uuid")
	private UUID deviceId;

	@Column(name = "asset_id", unique = true)
	private Integer assetId;

	private String make; // nullable = true

	private String model; // nullable = true

	@Column(name = "release_year")
	private Integer releaseYear;

	private String os; // nullable = true

	@Column(name = "os_version")
	private String osVersion; // nullable = true

	@Column(columnDefinition = "jsonb")
	private String capabilities; // store JSON string for simplicity for now. Alternatively use @Type for JSON mapping later

	@Column(name = "webdriver_config", columnDefinition = "jsonb")
	private String webdriverConfig;

	@Column(name = "region", columnDefinition = "varchar(50)")
	private String region = "LON";

	@Enumerated(EnumType.STRING)
	private DeviceStatus status;

	@Column(name = "last_polled")
	private Instant lastPolled;

	@Column(name = "reservation_token", columnDefinition = "uuid")
	private UUID reservationToken;

	@Column(name = "rave_config", columnDefinition = "jsonb")
	private String raveConfig;

	// No-arg constructor (required by JPA)
	public Device() {
	}

	// All-args constructor
	public Device(UUID deviceId, Integer assetId, String make, String model, Integer releaseYear, String os,
			String osVersion, String capabilities, String webdriverConfig, String region, DeviceStatus status,
			Instant lastPolled, UUID reservationToken, String raveConfig) {
		this.deviceId = deviceId;
		this.assetId = assetId;
		this.make = make;
		this.model = model;
		this.releaseYear = releaseYear;
		this.os = os;
		this.osVersion = osVersion;
		this.capabilities = capabilities;
		this.webdriverConfig = webdriverConfig;
		this.region = region;
		this.status = status;
		this.lastPolled = lastPolled;
		this.reservationToken = reservationToken;
		this.raveConfig = raveConfig;
	}

	// Getters
	public UUID getDeviceId() {
		return deviceId;
	}

	public Integer getAssetId() {
		return assetId;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public String getOs() {
		return os;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public String getCapabilities() {
		return capabilities;
	}

	public String getWebdriverConfig() {
		return webdriverConfig;
	}

	public String getRegion() {
		return region;
	}

	public DeviceStatus getStatus() {
		return status;
	}

	public Instant getLastPolled() {
		return lastPolled;
	}

	public UUID getReservationToken() {
		return reservationToken;
	}

	public String getRaveConfig() {
		return raveConfig;
	}
	
	@Override
    public String toString() {
        return "Device.DeviceBuilder(deviceId=" + this.deviceId + ", assetId=" + this.assetId + ", make=" + this.make
                + ", model=" + this.model + ", releaseYear=" + this.releaseYear + ", os=" + this.os
                + ", osVersion=" + this.osVersion + ", capabilities=" + this.capabilities
                + ", webdriverConfig=" + this.webdriverConfig + ", region=" + this.region
                + ", status=" + this.status + ", lastPolled=" + this.lastPolled
                + ", reservationToken=" + this.reservationToken + ", raveConfig=" + this.raveConfig + ")";
    }
}
