package is.hi.hbv501g1.hidden_pearls.location;

import java.util.List;

public interface LocationService {

	public List<Location> getAllLocations();

	public Location getLocation(long id);

	public List<Location> searchByName(String name);

	public List<Location> searchByCategory(LocationCategory category);

	public List<Location> searchByPosition(GPSLocation pos, double radius);

	public Location create(String name, GPSLocation location, String description, LocationCategory category,
		int visits);

	public Location update(long id, String name, GPSLocation location, String description, LocationCategory category,
		int visits);

	public void delete(long id);

}
