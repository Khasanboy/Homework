package model;

import java.math.BigDecimal;
import java.util.Date;

public class FuelData{
	
	private String fuelName;
	private BigDecimal fuelPrice;
	private double fuelAmount;
	private Date refuelingDate;
	
	private BigDecimal totalValue = new BigDecimal("0.000");

	public FuelData(String fuelName, BigDecimal fuelPrice, double fuelAmount, Date refuelingDate){
		this.fuelName = fuelName;
		this.fuelPrice = fuelPrice;
		this.fuelAmount = fuelAmount;
		this.refuelingDate = refuelingDate;
		
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

	public Date getRefuelingDate() {
		return refuelingDate;
	}

	public void setRefuelingDate(Date refuelingDate) {
		this.refuelingDate = refuelingDate;
	}
	
	
}

