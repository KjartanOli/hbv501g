package is.hi.hbv501g1.hidden_pearls.entities;

import java.time.YearMonth;

public class VisitStatistics {
	private YearMonth month;
	private int visitors;

	public YearMonth getMonth() {
		return this.month;
	}

	public int getVisitors() {
		return this.visitors;
	}

	public void setMonth(YearMonth month) {
		this.month = month;
	}

	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}
}
