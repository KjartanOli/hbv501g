package is.hi.hbv501g1.hidden_pearls.location;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class APIController{
	@Autowired
	private LocationService locationService;

	@GetMapping("/api/locations")
	public List<Location> getLocations(
		@RequestParam(required = false) String category,
		@RequestParam(required = false) Integer limit,
		@RequestParam (required = false) Integer[] ids
	) {
		List<Location> res;
		if (category != null) {
			var c = LocationCategory.valueOf(category);
			res = locationService.searchByCategory(c);
		}
		if (ids != null) {
			res = new ArrayList<Location>();
			for (var id : ids)
				res.add(locationService.getLocation(id));
		}
		else {
			res = locationService.getAllLocations();
		}

		if (limit != null)
			return res.subList(0, Math.min(limit, res.size()));
		return res;
	}

	@GetMapping("/api/locations/{id}")
	public Location getLocation(@PathVariable String id) {
		return locationService.getLocation(Long.parseLong(id));
	}

	@GetMapping("/api/locations/random")
	public Location randomLocation() {
		var locations = locationService.getAllLocations();
		Collections.shuffle(locations);
		return locations.get(0);
	}

	@GetMapping("/api/search/name/{name}")
	public List<Location> searchByName(@PathVariable String name) {
		var locations = locationService.searchByName(name);
		return locations;
	}

	@GetMapping("/api/search/position")
	public List<Location> searchByPosition(
		@RequestParam Double longitude,
		@RequestParam Double latitude,
		@RequestParam Double radius
	) {
		var pos = new GPSLocation(longitude, latitude);
		var locations = locationService.searchByPosition(pos, radius);
		return locations;
	}
}
