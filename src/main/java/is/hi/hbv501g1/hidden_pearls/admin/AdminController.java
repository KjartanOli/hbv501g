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

	@GetMapping("/admin/admins/new")
    public String newAdmin(HttpSession session, Model model){
		// patchworks fix for now
		model.addAttribute("admin", new Admin());
        return "admin-crud";
    }

	@GetMapping("/admin/admins/edit/{id}")
    public String editAdmin(@PathVariable String id ,HttpSession session, Model model){
        var admin = adminService.getAdmin(Long.parseLong(id));
		model.addAttribute("admin", admin);
        return "admin-crud";
    }

    @GetMapping("/admin")
	public String adminPage(HttpSession session, Model model){
        // Call a method in AdminService Class
        // Add data to the Model
		// Check for login

        return "admin";
    }

    public String getAdmin(HttpSession session, Model model){
        return "";
    }

	@GetMapping("/admin/admins")
    public String getAdmins(HttpSession session, Model model){
        return "admin-list";
    }

    public String patchAdmin(HttpSession session, Model model){
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

	@PostMapping("/admin/locations/delete/{id}")
    public String deleteLocation(@PathVariable String id, HttpSession session, Model model){
		try {
			locationService.delete(Long.parseLong(id));
			return "redirect:/admin";
		}
		catch (Exception e) {
			return "redirect:/error";
		}
    }

	@PostMapping("/admin/admins/new")
	public String newAdmin(@ModelAttribute Admin admin, Model model, HttpSession session){
		adminService.create(
			admin.getUsername(),
			admin.getPassword()
			);

		return "redirect:/admin";
	}

	@PostMapping("/admin/admins/edit/{id}")
	public String editAdmin(@ModelAttribute Admin admin, Model model, HttpSession session){
		adminService.update(
            admin.getId(),
			admin.getUsername(),
			admin.getPassword()
			);

		return "redirect:/admin";
	}

	@PostMapping("/admin/admins/edit/delete/{id}")
    public String deleteAdmin(HttpSession session, Model model){
        Admin adminToDelete = (Admin) model.getAttribute("admin");
		if (adminToDelete != null) {
			adminService.delete(adminToDelete.getId());
			return "redirect:/admin";
		}
		else return "index";
    }
}
