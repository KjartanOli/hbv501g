package is.hi.hbv501g1.hidden_pearls.entites;

import java.util.List;
import java.awt.Image;

class Location {
	private long id;
	private String name;
	private String description;
	private LocationCategory category;
	private List<String> tags;
	private List<VisitStatistics> monthlyVisits;
	private List<Image> images;
}
