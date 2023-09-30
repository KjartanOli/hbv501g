package is.hi.hbv501g1.hidden_pearls.entities;

import java.util.List;
import java.awt.Image;

public class Location {
	private long id;
	private String name;
	private String description;
	private LocationCategory category;
	private List<String> tags;
	private List<VisitStatistics> monthlyVisits;
	private List<Image> images;
	
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
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}

	
}
