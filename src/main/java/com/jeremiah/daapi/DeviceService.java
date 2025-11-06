package com.jeremiah.daapi;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeremiah.daapi.dto.DeviceResponseDTO;
import com.jeremiah.daapi.entity.Device;
import com.jeremiah.daapi.repository.DeviceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //??
public class DeviceService {

	private final DeviceRepository repo;
	
	public List<DeviceResponseDTO> getAllDevices() {
		return repo.findAll().stream().map(DeviceService::toDto).toList();
	}
	
    private static DeviceResponseDTO toDto(Device d) {
    	DeviceResponseDTO dto = new DeviceResponseDTO();
    	
    	dto.setDeviceId(d.getDeviceId());
    	dto.setAssetId(d.getAssetId());
    	dto.setMake(d.getMake());
    	dto.setModel(d.getModel());
    	dto.setReleaseYear(d.getReleaseYear());
    	dto.setOs(d.getOs());
    	dto.setOsVersion(d.getOsVersion());
    	dto.setCapabilities(d.getCapabilities());
    	dto.setWebdriverConfig(d.getWebdriverConfig());
    	dto.setRegion(d.getRegion());
    	dto.setStatus(d.getStatus());
    	dto.setRaveConfig(d.getRaveConfig());
    	
    	return dto;
     }
}
