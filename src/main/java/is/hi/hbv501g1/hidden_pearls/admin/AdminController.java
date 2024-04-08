package is.hi.hbv501g1.hidden_pearls.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

import is.hi.hbv501g1.hidden_pearls.location.Location;
import is.hi.hbv501g1.hidden_pearls.location.LocationService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private LocationService locationService;

	// location management

	// get methods
	@GetMapping("/admin/locations")
	public String getAdminLocations(HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}

		var locations = locationService.getAllLocations();
		model.addAttribute("locations", locations);
		if (locations == null)
			return "redirect:/error";
		return "location-list-admin";
	}

	@GetMapping("/admin/locations/new")
	public String newLocation(HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}

		model.addAttribute("location", new Location());
		return "location-crud";
	}

	@GetMapping("/admin/locations/edit/{id}")
	public String editLocation(@PathVariable String id, HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}

		var location = locationService.getLocation(Long.parseLong(id));
		model.addAttribute("location", location);
		return "location-crud";
	}

	// post methods
	@PostMapping("/admin/locations/new")
	public String newLocation(@ModelAttribute Location location, Model model, HttpSession session) {
		locationService.create(
				location.getName(),
				location.getLoc(),
				location.getDescription(),
				location.getCategory(),
				0);

		return "redirect:/admin/locations";
	}

	@PostMapping("/admin/locations/edit/{id}")
	public String editLocation(@ModelAttribute Location location, Model model, HttpSession session) {
		locationService.update(
				location.getId(),
				location.getName(),
				location.getLoc(),
				location.getDescription(),
				location.getCategory(),
				0);

		return "redirect:/admin/locations";
	}

	@PostMapping("/admin/locations/edit/delete/{id}")
	public String deleteLocation(@PathVariable String id, HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}

		try {
			locationService.delete(Long.parseLong(id));
			return "redirect:/admin/locations";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/admin/locations/delete/{id}")
	public String deleteLocationFromList(@PathVariable Long id, HttpSession session) {
		// Check for login
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}

		locationService.delete(id);
		return "redirect:/admin/locations";
	}

	// admin management

	// get methods
	@GetMapping("/admin/login")
	public String getLogin(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		if (session.getAttribute("admin") != null) {
			redirectAttributes.addFlashAttribute("error", "Admin is already logged in");
			return "redirect:/admin";
		}

		model.addAttribute("admin", new Admin());
		return "admin-login";
	}

	@GetMapping("/admin/admins/new")
	public String newAdmin(HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}

		model.addAttribute("admin", new Admin());

		return "admin-crud";
	}

	@GetMapping("/admin/admins/edit/{id}")
	public String editAdmin(@PathVariable String id, HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}

		var admin = adminService.getAdmin(Long.parseLong(id));
		model.addAttribute("admin", admin);

		return "admin-crud";
	}

	@GetMapping("/admin")
	public String adminPage(HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}

		return "admin";
	}

	@GetMapping("/admin/admins")
	public String getAdmins(HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}

		List<Admin> admins = adminService.getAllAdmins();
		model.addAttribute("admins", admins);

		return "admin-list";
	}

	@GetMapping("/admin/admins/search")
	public String searchAdmins(@RequestParam("query") String query, Model model, HttpSession session) {
		// Check for login
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}

		List<Admin> admins = adminService.searchByName(query);
		model.addAttribute("admins", admins);

		return "admin-list";
	}

	@GetMapping("/admin/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return "redirect:/";
	}


	// post methods
	@PostMapping("/admin/login")
	public String login(@ModelAttribute Admin admin, RedirectAttributes redirectAttributes, HttpSession session) {

		var auth = adminService.authenticate(admin.getUsername(), admin.getPassword());

		if (auth == null) {
			redirectAttributes.addFlashAttribute("error", "Invalid username or password");
			return "redirect:/admin/login";
		}

		session.setAttribute("admin", auth);

		return "redirect:/admin";
	}

	@PostMapping("/admin/admins/new")
	public String newAdmin(@ModelAttribute Admin admin, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
		// check if username is already taken, return to admin-crud with error msg
		try {
			adminService.create(
					admin.getUsername(),
					admin.getPassword());
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("error", "Admin username taken");
			return "redirect:/admin/admins/new";
		}

		return "redirect:/admin/admins";
	}

	@PostMapping("/admin/admins/edit/{id}")
	public String editAdmin(@ModelAttribute Admin admin, Model model, HttpSession session) {
		// Check for login
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}

		adminService.update(
				admin.getId(),
				admin.getUsername(),
				admin.getPassword());

		return "redirect:/admin/admins";
	}

	@PostMapping("/admin/admins/delete/{id}")
	public String deleteAdminFromList(@PathVariable Long id, HttpSession session) {
		// Check for login
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}

		adminService.delete(id);

		// if admin deletes themselves, log them out
		if (((Admin) session.getAttribute("admin")).getId() == id) {
			return "redirect:/admin/logout";
		}

		return "redirect:/admin/admins";
	}
}
