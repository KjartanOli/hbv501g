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

	@GetMapping("/")
    public String getHome(Model model){
		locationService.create("test1", "Reykjavik", "lorem ipsum", LocationCategory.PEARL, null, null, null);
		locationService.create("test2", "Reykjavik", "dolor set", LocationCategory.TRAP, null, null, null);

		var pearls = locationService.searchByCategory(LocationCategory.PEARL);
		var traps = locationService.searchByCategory(LocationCategory.TRAP);

		model.addAttribute("pearls", pearls.subList(0, Math.min(5, pearls.size())));
		model.addAttribute("traps", traps.subList(0, Math.min(5, traps.size())));
        return "index";
    }
    public String getLocations(Model model){
        return "";
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
