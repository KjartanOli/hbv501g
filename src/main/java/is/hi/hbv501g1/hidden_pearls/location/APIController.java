package is.hi.hbv501g1.hidden_pearls.location;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class APIController{
	@Autowired
	private LocationService locationService;

	@GetMapping("/api/locations")
	public List<Location> getLocations() {
		return locationService.getAllLocations();
	}

	@GetMapping("/api/location/{id}")
	public Location getLocation(@PathVariable String id) {
		return locationService.getLocation(Long.parseLong(id));
	}
}
