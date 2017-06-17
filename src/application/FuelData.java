package application;

import java.math.BigDecimal;

public class FuelData{
	
	private String fuelName;
	private BigDecimal fuelPrice;
	private double fuelAmount;
	
	private BigDecimal totalValue = new BigDecimal("0.000");

	public FuelData(String fuelName, BigDecimal fuelPrice, double fuelAmount){
		this.fuelName = fuelName;
		this.fuelPrice = fuelPrice;
		this.fuelAmount = fuelAmount;
		
	}
	
	public BigDecimal getTotalValue() {
		this.totalValue = new BigDecimal((this.fuelAmount * this.fuelPrice.doubleValue()));
		return this.totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}
	
	public String getFuelName() {
		return fuelName;
	}
	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}
	public BigDecimal getFuelPrice() {
		return fuelPrice;
	}
	public void setFuelPrice(BigDecimal fuelPrice) {
		this.fuelPrice = fuelPrice;
	}
	public double getFuelAmount() {
		return fuelAmount;
	}
	public void setFuelAmount(double fuelAmount) {
		this.fuelAmount = fuelAmount;
	}
	
	
}

