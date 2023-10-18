package is.hi.hbv501g1.hidden_pearls.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import is.hi.hbv501g1.hidden_pearls.location.Location;
import is.hi.hbv501g1.hidden_pearls.location.LocationCategory;
import is.hi.hbv501g1.hidden_pearls.location.LocationService;
import jakarta.servlet.http.HttpSession;

// skeleton of implementation
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private LocationService locationService;

    @GetMapping("/admin/locations/new")
    public String newLocation(HttpSession session, Model model){
		// patchworks fix for now
		model.addAttribute("location", new Location());
        return "location-crud";
    }

    @GetMapping("/admin/locations/edit/{id}")
    public String editLocation(@PathVariable String id ,HttpSession session, Model model){
        var location = locationService.getLocation(Long.parseLong(id));
		model.addAttribute("location", location);
        return "location-crud";
    }

    public String adminPage(HttpSession session, Model model){
        // Call a method in AdminService Class
        // Add data to the Model

        return "admin";
    }

    public String getAdmin(HttpSession session, Model model){
        return "";
    }

    public String getAdmins(HttpSession session, Model model){
        return "";
    }

    public String patchAdmin(HttpSession session, Model model){
        return "";
    }

    public String deleteAdmin(HttpSession session, Model model){
        return "";
    }

    public String patchLocation(HttpSession session, Model model){
        return "";
    }

	@PostMapping("/admin/locations/new")
	public String newLocation(@ModelAttribute Location location, Model model, HttpSession session){
		locationService.create(
			location.getName(),
			location.getLocation(),
			location.getDescription(),
			location.getCategory(),
			null,
			null,
			null);

		return "redirect:/admin";
	}

    @PostMapping("/admin/locations/edit/{id}")
	public String editLocation(@ModelAttribute Location location, Model model, HttpSession session){
		locationService.update(
            location.getId(),
			location.getName(),
			location.getLocation(),
			location.getDescription(),
			location.getCategory(),
			null,
			null,
			null);

		return "redirect:/admin";
	}

	@PostMapping("/admin/locations/edit/delete/{id}")
    public String deleteLocation(HttpSession session, Model model){

        Location locationToDelete = (Location) model.getAttribute("location");
		if (locationToDelete != null) {
			locationService.delete(locationToDelete.getId());
			return "redirect:/admin";
		}
		else return "index";
    }
}
