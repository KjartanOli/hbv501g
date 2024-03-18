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
		@RequestParam (required = false) Integer[] ids,
		@RequestParam(required = false) String name,
		@RequestParam (required = false) String longitude,
		@RequestParam (required = false) String latitude,
		@RequestParam (required = false) String radius
	) {
		List<Location> res;
		if (category != null) {
			var c = LocationCategory.valueOf(category);
			res = locationService.searchByCategory(c);
		}
		else if (ids != null) {
			res = new ArrayList<Location>();
			for (var id : ids)
				res.add(locationService.getLocation(id));
		}
		else if (name != null) {
			res = locationService.searchByName(name);
		}
		else if (longitude != null && latitude != null && radius != null) {
			var pos = new GPSLocation(Double.parseDouble(longitude), Double.parseDouble(latitude));
			res = locationService.searchByPosition(pos, Double.parseDouble(radius));
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
}
