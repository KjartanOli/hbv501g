package is.hi.hbv501g1.hidden_pearls.location;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends CrudRepository<Location, Long> {
	public List<Location> findAll();
	
	public Location findById(long id);
	
	public List<Location> findByNameLike(String name);

	// public List<Location> findByTag(String tag);

	public List<Location> findByCategory(LocationCategory category);

}
