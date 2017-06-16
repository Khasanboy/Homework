package application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Model {
	
	private String fuelName;
	private Fuel fuelPrice;
	private Fuel fuelAmount;
	private Date refuelingDate;
	
	public String getFuelName() {
		return fuelName;
	}
	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}
	public Fuel getFuelPrice() {
		return fuelPrice;
	}
	public void setFuelPrice(Fuel fuelPrice) {
		this.fuelPrice = fuelPrice;
	}
	public Fuel getFuelAmount() {
		return fuelAmount;
	}
	public void setFuelAmount(Fuel fuelAmount) {
		this.fuelAmount = fuelAmount;
	}
	public Date getRefuelingDate() {
		return refuelingDate;
	}
	public void setRefuelingDate(Date refuelingDate) {
		//String pattern = "dd.MM.yyyy";
		//SimpleDateFormat format = new SimpleDateFormat(pattern);
		this.refuelingDate = refuelingDate;
	}
	
	

}

class Fuel {
	
	private double amount;
	
	public Fuel(String input){
		this.amount = Double.parseDouble(input);
	}
	
	public double getAmount(){
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = Double.parseDouble(amount);
	}
	
	
}
