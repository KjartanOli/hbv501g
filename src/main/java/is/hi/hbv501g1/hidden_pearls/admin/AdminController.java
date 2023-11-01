package is.hi.hbv501g1.hidden_pearls.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@GetMapping("/admin/locations/new")
	public String newLocation(HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("user") == null) {
			return "redirect:/admin/login";
		}

		model.addAttribute("location", new Location());
		return "location-crud";
	}

	@GetMapping("/admin/locations/edit/{id}")
	public String editLocation(@PathVariable String id, HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("user") == null) {
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
				location.getLocation(),
				location.getDescription(),
				location.getCategory(),
				null);

		return "redirect:/admin";
	}

	@PostMapping("/admin/locations/edit/{id}")
	public String editLocation(@ModelAttribute Location location, Model model, HttpSession session) {
		locationService.update(
				location.getId(),
				location.getName(),
				location.getLocation(),
				location.getDescription(),
				location.getCategory(),
				null);

		return "redirect:/admin";
	}

	@PostMapping("/admin/locations/delete/{id}")
	public String deleteLocation(@PathVariable String id, HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("user") == null) {
			return "redirect:/admin/login";
		}

		try {
			locationService.delete(Long.parseLong(id));
			return "redirect:/admin";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	// admin management

	// get methods
	@GetMapping("/admin/login")
	public String getLogin(HttpSession session, Model model) {
		model.addAttribute("admin", new Admin());
		return "admin-login";
	}

	@GetMapping("/admin/admins/new")
	public String newAdmin(HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("user") == null) {
			return "redirect:/admin/login";
		}

		model.addAttribute("admin", new Admin());

		return "admin-crud";
	}

	@GetMapping("/admin/admins/edit/{id}")
	public String editAdmin(@PathVariable String id, HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("user") == null) {
			return "redirect:/admin/login";
		}

		var admin = adminService.getAdmin(Long.parseLong(id));
		model.addAttribute("admin", admin);

		return "admin-crud";
	}

	@GetMapping("/admin")
	public String adminPage(HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("user") == null) {
			return "redirect:/admin/login";
		}

		return "admin";
	}

	@GetMapping("/admin/admins")
	public String getAdmins(HttpSession session, Model model) {
		// Check for login
		if (session.getAttribute("user") == null) {
			return "redirect:/admin/login";
		}

		List<Admin> admins = adminService.getAllAdmins();
		model.addAttribute("admins", admins);

		return "admin-list";
	}

	@GetMapping("/admin/admins/search")
	public String searchAdmins(@RequestParam("query") String query, Model model, HttpSession session) {
		// Check for login
		if (session.getAttribute("user") == null) {
			return "redirect:/admin/login";
		}

		List<Admin> admins = adminService.searchByName(query);
		model.addAttribute("admins", admins);

		return "amdin-list";
	}

	// post methods
	@PostMapping("/admin/login")
	public String login(@ModelAttribute Admin admin, Model model, HttpSession session) {
		var auth = adminService.authenticate(admin.getUsername(), admin.getPassword());

		if (auth == null) {
			model.addAttribute("error", "Invalid username or password");
			return "redirect:/admin/login";
		}

		session.setAttribute("admin", auth);

		return "redirect:/admin";
	}

	@PostMapping("/admin/admins/new")
	public String newAdmin(@ModelAttribute Admin admin, Model model, HttpSession session) {
		// check if username is already taken, return to admin-crud with error msg
		try {
			adminService.create(
					admin.getUsername(),
					admin.getPassword());
		} catch (IllegalArgumentException e) {
			model.addAttribute("error", "Admin username taken");
			return "admin-crud";
		}

		return "redirect:/admin";
	}

	@PostMapping("/admin/admins/edit/{id}")
	public String editAdmin(@ModelAttribute Admin admin, Model model, HttpSession session) {
		adminService.update(
				admin.getId(),
				admin.getUsername(),
				admin.getPassword());

		return "redirect:/admin";
	}

	@PostMapping("/admin/admins/edit/delete/{id}")
	public String deleteAdmin(HttpSession session, Model model) {
		Admin adminToDelete = (Admin) model.getAttribute("admin");
		if (adminToDelete != null) {
			adminService.delete(adminToDelete.getId());
			return "redirect:/admin";
		} else
			return "index";
	}
}
