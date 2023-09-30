package is.hi.hbv501g1.hidden_pearls.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import is.hi.hbv501g1.hidden_pearls.interfaces.LocationService;

@Controller
public class LocationController {
    private LocationService locationService;

    public String getHome(Model model){
        return "";
    }
    
    public String getLocations(Model model){
        return "";
    }

    public String getLocation(@PathVariable long id, Model model){
        return "";
    }
}
