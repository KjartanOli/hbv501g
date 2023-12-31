package is.hi.hbv501g1.hidden_pearls.location;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;

	@GetMapping("/")
	public String getHome(Model model) {
		var pearls = locationService.searchByCategory(LocationCategory.PEARL);
		var traps = locationService.searchByCategory(LocationCategory.TRAP);

		model.addAttribute("pearls", pearls.subList(0, Math.min(5, pearls.size())));
		model.addAttribute("traps", traps.subList(0, Math.min(5, traps.size())));
		return "index";
	}

	@GetMapping("/locations")
	public String getLocations(Model model) {
		var locations = locationService.getAllLocations();
		model.addAttribute("locations", locations);
		if (locations == null)
			return "redirect:/error";
		return "location-list";
	}

	@GetMapping("/location/{id}")
	public String getLocation(@PathVariable String id, Model model) {
		var location = locationService.getLocation(Long.parseLong(id));
		model.addAttribute("location", location);
		if (location == null)
			return "redirect:/error";
		return "location";
	}

	@GetMapping("/search")
	public String searchLocation(@RequestParam("query") String query, Model model) {
		List<Location> locations = locationService.searchByName(query);
		model.addAttribute("locations", locations);
		return "location-list";
	}
}
