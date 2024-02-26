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
	public List<Location> getLocations(
		@RequestParam(required = false) Integer category,
		@RequestParam(required = false) Integer limit
	) {
		System.err.println(category);
		System.err.println(limit);

		List<Location> res;
		if (category != null) {
			var c = LocationCategory.values()[category];
			res = locationService.searchByCategory(c);
		}
		else {
			res = locationService.getAllLocations();
		}

		if (limit != null)
			return res.subList(0, Math.min(limit, res.size()));
		return res;
	}

	@GetMapping("/api/location/{id}")
	public Location getLocation(@PathVariable String id) {
		return locationService.getLocation(Long.parseLong(id));
	}
}
