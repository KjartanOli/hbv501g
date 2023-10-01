package is.hi.hbv501g1.hidden_pearls.location;

import java.util.List;
import java.awt.Image;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;

@Entity
public class Location {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String location;
	private String description;
	private LocationCategory category;
	@ElementCollection
	private List<String> tags;

	@ElementCollection
	private List<VisitStatistics> monthlyVisits;
	// private List<Image> images;

	public Location(String name, String location, String description, LocationCategory category, List<String> tags, List<VisitStatistics> monthlyVisits)
{
		this.name = name;
		this.name = location;
		this.description = description;
		this.category = category;
		this.monthlyVisits = monthlyVisits;
	}
	
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
	public String getLocation() {
		return this.location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<VisitStatistics> getMonthlyVisits() {
		return monthlyVisits;
	}
	public void setMonthlyVisits(List<VisitStatistics> monthlyVisits) {
		this.monthlyVisits = monthlyVisits;
	}
	// public List<Image> getImages() {
	// 	return images;
	// }
	// public void setImages(List<Image> images) {
	// 	this.images = images;
	// }
}
