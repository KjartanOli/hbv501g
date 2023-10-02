package is.hi.hbv501g1.hidden_pearls.location;

import java.awt.Image;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImplementation implements LocationService{
	@Autowired
    private LocationRepository repository;

    public List<Location> getAllLocations() {
		    return repository.findAll();
    }

    public Location getLocation(long id) {
		    return repository.findById(id);
    }

    public List<Location> searchByName(String name) {
        return repository.findByNameLike(name);
    }

    /* 
    public List<Location> searchByTag(String tag) {
        return repository.findByTag(tag);
    }
    */
    public List<Location> searchByCategory(LocationCategory category) {
		    return repository.findByCategory(category);
    }

    public Location create(String name, String location, String description, LocationCategory category, List<String> tags, List<VisitStatistics> visits, List<Image> images) {
		    return null;
    }

    public Location update(long id, String name, String location, String description, LocationCategory category, List<String> tags, List<VisitStatistics> visits, List<Image> images) {
		    return null;
    }

    public void delete(long id){
		var location = repository.findById(id);
		if (location != null)
			  repository.delete(location);
    }

}
