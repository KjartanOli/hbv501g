package is.hi.hbv501g1.hidden_pearls.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImplementation implements LocationService {

	@Autowired
	private LocationRepository repository;

	public List<Location> getAllLocations() {
		Sort sort = Sort.by("name");

		return repository.findAll(sort);
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

	public Location create(String name, GPSLocation location, String description, LocationCategory category,
			List<VisitStatistics> visits) {
		var loc = new Location();
		loc.setName(name);
		loc.setLoc(location);
		loc.setDescription(description);
		loc.setCategory(category);
		loc.setStatistics(visits);

		return repository.save(loc);
	}

	public Location update(long id, String name, GPSLocation location, String description, LocationCategory category,
			List<VisitStatistics> visits) {
		var loc = getLocation(id);
		loc.setName(name);
		loc.setLoc(location);
		loc.setDescription(description);
		loc.setCategory(category);
		loc.setStatistics(visits);

		return repository.save(loc);
	}

	public void delete(long id) {
		var location = repository.findById(id);
		if (location != null)
			repository.delete(location);
	}

}
