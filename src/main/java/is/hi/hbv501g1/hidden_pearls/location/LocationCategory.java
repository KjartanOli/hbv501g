package is.hi.hbv501g1.hidden_pearls.location;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LocationCategory {
	TRAP(0),
	PEARL(1);

	private final int category;
	LocationCategory(int category) {
		this.category = category;
	}

	@JsonValue
	public int getCategory() { return this.category; }
}
