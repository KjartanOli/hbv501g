package is.hi.hbv501g1.hidden_pearls.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

// skeleton of implementation
@Controller
public class AdminController {
    
    @Autowired
    private AdminService adminService;

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

    public String getLocation(HttpSession session, Model model){
        return "";
    }

    public String patchLocation(HttpSession session, Model model){
        return "";
    }

    public String deleteLocation(HttpSession session, Model model){
        return "";
    }
}
