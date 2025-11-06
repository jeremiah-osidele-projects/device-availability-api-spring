package com.jeremiah.da_api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jeremiah.daapi.Service.DeviceService;
import com.jeremiah.daapi.dto.DeviceResponseDTO;
import com.jeremiah.daapi.entity.Device;
import com.jeremiah.daapi.entity.DeviceStatus;
import com.jeremiah.daapi.repository.DeviceRepository;

class DeviceServiceTest {

	// Tests are under the assumption that other components eg.g. db are working well
	
	// Create a 'fake' version of  - so no real DB access
	@Mock
	DeviceRepository repo;
	
	// Creal a real instance of DeviceService
	@InjectMocks
	DeviceService service;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); 
		// Mockito library used for mocking
		
		// Mocking - A fake object that imitates a real one (e.g., fake database) so we can control its behavior
		
		// Mockito can automatically create fake objects (mocks)for us when it sees the @Mock or @InjectMocks annotations
		// THE ANNOTATIONS DON'T CREATE ANYTHING ON THEIR OWN - THET NEED THE 'openMocks'
		
		// Usually, 'this' = “the current object instance” 
		//'this' is literally referring to the current test object instance that JUnit is running
		
		// When this line runs, Mockito does:
		// - Looks at the current test object (that’s what this refers to)
		// - Scans all fields inside it
		// - Finds @Mock and @InjectMocks annotations.
		// - Creates fake objects for fields with '@Mock' (e.g. repo) and injects them into the fields with '@InjectMocks' (e.g. service)
		
		// So, the test has:
		// - Initially 
		// 		- repo = null
		// 		- service = null
		// - After openMocks(this) runs
		//		- repo = mock(DeviceRepository)
		//		- service = new DeviceService()
		// 		- (service.repo = repo)  // injected moc
		// - Noe service is ready for testing
	}
	
	// helper method just creates a ready-to-use Device object for tests - Keeps it DRY
	private Device sampleDevice(Integer assetId, DeviceStatus status) {
		Device d = new Device();
		
		d.setDeviceId(UUID.randomUUID());
        d.setAssetId(assetId);
        d.setMake("Samsung");
        d.setModel("Q80T");
        d.setReleaseYear(2020);
        d.setOs("Tizen");
        d.setOsVersion("6.0");
        d.setCapabilities("{}");
        d.setWebdriverConfig("{}");
        d.setRegion("LON");
        d.setStatus(status);
        d.setLastPolled(Instant.now());
        d.setReservationToken(null);
        d.setRaveConfig(null);
        return d;
	}
	
	@Test
	void testThat_AllDevicesAreReturned_WhenGetAllIsCalled() {
		Device d1 = sampleDevice(1, DeviceStatus.AVAILABLE);
		Device d2 = sampleDevice(2, DeviceStatus.AVAILABLE);
		
		when(repo.findAll()).thenReturn(List.of(d1, d2));
		List<DeviceResponseDTO> dtos = service.getAllDevices();
		
		// Stubbing - A mock that is specifically used to return pre-defined answers when methods are called
		
		// when(...).thenReturn(...)
		// - A Mockito method 
		//		used to define how a mock object should behave when a method is called on it.
		//		i.e. “When someone calls this method on my fake object, don’t do the real thing
		//		instead, return this fake value I’m telling you.”
		// - Since our repo is 'fake' it's not connected to a DB
		// 		Calling repo.findAll() would return nothing.
		// - We have to tell Mockito:
		//		“If someone calls repo.findAll(), pretend that these 2 devices exist and return them” 
		// - i.e. when(repo.findAll()).thenReturn(List.of(d1, d2)); is saying:
		//		When we call findAll, Predtend that d1 and d2 are all that in the DB and return a list containing d1 and d2.
		//
		// 		When we call service.getAllDevices, Mockito knows to inject the 'fake' repo, then apply the stubbing we deined
		
		// So is is only applicable to how 'method calls' should behave?
		
        assertThat(dtos).hasSize(2);
        assertThat(dtos).extracting("assetId").containsExactlyInAnyOrder(1, 2);
        verify(repo).findAll();
        
        // Assertions
        //		Checks that something is true (e.g., value, size, status)
        // 		confirm the program did what is expected.
        
        // Verify
        //		verify() is different — it doesn’t test the result, it tests interaction.
        //		Used to check if certain mock methods were called, 
        //		(and optionally how many times or with what arguments).
        // - e.g. verify(repo).findAll();
        // 		“Make sure the service actually called repo.findAll() once.”
	}

}
