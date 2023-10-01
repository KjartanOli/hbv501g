package is.hi.hbv501g1.hidden_pearls.location;

import java.time.YearMonth;

import jakarta.persistence.Embeddable;

@Embeddable
public class VisitStatistics {
	// Apperently month is a reserved keyword, so we use month_.
	private YearMonth month_;
	private int visitors;

	public YearMonth getMonth() {
		return this.month_;
	}

	public int getVisitors() {
		return this.visitors;
	}

	public void setMonth(YearMonth month) {
		this.month_ = month;
	}

	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}
}
