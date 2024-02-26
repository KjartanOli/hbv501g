package is.hi.hbv501g1.hidden_pearls.location;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;

@Entity
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private GPSLocation loc;
	private String description;
	private LocationCategory category;

	@ElementCollection
	private List<VisitStatistics> statistics;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}

	public GPSLocation getLoc() {
		return this.loc;
	}

	public void setLoc(GPSLocation location) {
		this.loc = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocationCategory getCategory() {
		return category;
	}

	public void setCategory(LocationCategory category) {
		this.category = category;
	}

	public List<VisitStatistics> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<VisitStatistics> statistics) {
		this.statistics = statistics;
	}
}
