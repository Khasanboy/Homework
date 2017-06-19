package model;

import java.math.BigDecimal;

public class Month {
	
	private int id;
	
	private BigDecimal totalValue;
	
	private double amount;
	
	public Month() {
		
	}

	public Month(int id, BigDecimal totalValue, double amount) {
		super();
		this.id = id;
		this.totalValue = totalValue;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	

}
