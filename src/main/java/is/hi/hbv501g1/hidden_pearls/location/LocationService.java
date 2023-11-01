package is.hi.hbv501g1.hidden_pearls.location;

import java.awt.Image;
import java.util.List;

public interface LocationService {

    public List<Location> getAllLocations();

    public Location getLocation(long id);

    public List<Location> searchByName(String name);

    public List<Location> searchByCategory(LocationCategory category);

    public Location create(String name, String location, String description, LocationCategory category, List<VisitStatistics> visits);

    public Location update(long id, String name, String location, String description, LocationCategory category, List<VisitStatistics> visits);

    public void delete(long id);

}
