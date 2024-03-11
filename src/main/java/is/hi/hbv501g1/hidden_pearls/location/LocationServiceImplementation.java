package is.hi.hbv501g1.hidden_pearls.location;

import java.util.List;
import java.util.ArrayList;

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

	private double distance(GPSLocation p1, GPSLocation p2) {
		final var earthRadiusKm = 6371;

		var dLat = Math.toRadians(p2.getLatitude() - p1.getLatitude());
		var dLon = Math.toRadians(p2.getLongitude() - p1.getLongitude());

		var lat1 = Math.toRadians(p1.getLatitude());
		var lat2 = Math.toRadians(p2.getLatitude());

		var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
		var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return earthRadiusKm * c;

	}

	public List<Location> searchByPosition(GPSLocation pos, double radius) {
		var locations = getAllLocations();
		List<Location> res = new ArrayList<Location>();
		for (var location : locations) {
			if (distance(pos, location.getLoc()) < radius)
				res.add(location);
		}
		return res;
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
