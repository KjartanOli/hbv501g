package is.hi.hbv501g1.hidden_pearls.location;

import jakarta.persistence.Embeddable;

@Embeddable
public class GPSLocation {
	private double latitude;
	private double longitude;

	public GPSLocation() {
		this.latitude = 0;
		this.longitude = 0;
	}
	public GPSLocation(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public double getLatitude() { return this.latitude; }
	public double getLongitude() { return this.longitude; }
	public void setLatitude(double latitude) { this.latitude = latitude; }
	public void setLongitude(double longitude) { this.longitude = longitude; }
}
