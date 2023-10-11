package is.hi.hbv501g1.hidden_pearls.location;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class LocationController {
	
	@Autowired
    private LocationService locationService;
	private String attributeName;

	@GetMapping("/")
    public String getHome(Model model){
		// temp info
		locationService.create("Geldingarnes", "Reykjavik", "An idilic island inside the city limits, good for a lovely sunset and some kayaking", LocationCategory.PEARL, null, null, null);
		locationService.create("Hallgrímskirkja", "Reykjavik", "This iconic church dominates over the city skyline, but it's also dominated by tourists", LocationCategory.TRAP, null, null, null);

		var pearls = locationService.searchByCategory(LocationCategory.PEARL);
		var traps = locationService.searchByCategory(LocationCategory.TRAP);

		model.addAttribute("pearls", pearls.subList(0, Math.min(5, pearls.size())));
		model.addAttribute("traps", traps.subList(0, Math.min(5, traps.size())));
        return "index";
    }
    
	@GetMapping("/location-list")
	public String getLocations(Model model){
        var locations = locationService.getAllLocations();
		model.addAttribute("locations", locations);
		if (locations == null)
			return "redirect:/error";
        return "location-list";
    }

    @GetMapping("/location/{id}")
    public String getLocation(@PathVariable String id, Model model){
		var location = locationService.getLocation(Long.parseLong(id));
		model.addAttribute("location", location);
		if (location == null)
			return "redirect:/error";
        return "location";
    }
}
