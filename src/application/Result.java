package application;

public class Result {
	
	private String name;
	private Month [] months = new Month[12];
	
	public Result() {
		super();
	}

	public Result(String name, Month[] months) {
		super();
		this.name = name;
		this.months = months;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Month[] getMonths() {
		return months;
	}

	public void setMonths(Month[] months) {
		this.months = months;
	}
	
	

}
