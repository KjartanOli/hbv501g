package is.hi.hbv501g1.hidden_pearls.controllers;

import java.awt.Image;
import java.util.List;
import is.hi.hbv501g1.hidden_pearls.entities.Location;
import is.hi.hbv501g1.hidden_pearls.entities.LocationCategory;
import is.hi.hbv501g1.hidden_pearls.entities.VisitStatistics;

public class LocationService {

    private LocalRepository repository;

    public List<Location> getAllLocations(){

    };

    public Location getLocation(){

    };

    public List<Location> searchByName(String name){
        
    };

    public List<Location> searchByTag(String tag){

    };

    public List<Location> searchByCategory(LocationCategory category){

    };

    public Location create(String name, String location, String description, LocationCategory category, List<String> tags, List<VisitStatistics> visits, List<Image> images){

    };

    public Location update(long id, String name, String location, String description, LocationCategory category, List<String> tags, List<VisitStatistics> visits, List<Image> images){

    };

    public void delete(long id){
        
    };

}
