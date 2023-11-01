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

    public List<Location> searchByCategory(LocationCategory category) {
		return repository.findByCategory(category);
    }

    public Location create(String name, String location, String description, LocationCategory category, List<String> tags, List<VisitStatistics> visits, List<Image> images) {
		var loc = new Location();
		loc.setName(name);
		loc.setLocation(location);
		loc.setDescription(description);
		loc.setCategory(category);
		loc.setMonthlyVisits(visits);

		return repository.save(loc);
    }

    public Location update(long id, String name, String location, String description, LocationCategory category, List<String> tags, List<VisitStatistics> visits, List<Image> images) {
		var loc = getLocation(id);

		// spurning með if-klásur til að geta uppfært
        // bara valda hluti
		loc.setName(name);
		loc.setLocation(location);
		loc.setDescription(description);
		loc.setCategory(category);
		loc.setMonthlyVisits(visits);

		return repository.save(loc);
    }

    public void delete(long id){
		var location = repository.findById(id);
		if (location != null)
			repository.delete(location);
    }

}
