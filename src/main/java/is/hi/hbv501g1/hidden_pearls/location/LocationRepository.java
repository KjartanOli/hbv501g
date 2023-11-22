package is.hi.hbv501g1.hidden_pearls.location;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
	public List<Location> findAll();

	public List<Location> findAll(Sort sort);

	public Location findById(long id);

	public List<Location> findByNameLike(String name);

	public List<Location> findByCategory(LocationCategory category);

}
