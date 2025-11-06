package com.jeremiah.daapi.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeremiah.daapi.DeviceService;
import com.jeremiah.daapi.dto.DeviceResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/devices")
@RequiredArgsConstructor
public class DeviceController {

	private final DeviceService service;
	
	@GetMapping
	public List<DeviceResponseDTO> getAll() {
		return service.getAllDevices();
	}
}
