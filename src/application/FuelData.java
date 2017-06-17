package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;


public class FuelData{
	
	private String fuelName;
	private Fuel fuelPrice;
	private Fuel fuelAmount;
	private Date refuelingDate;
	
	public FuelData(String fuelName, Fuel fuelPrice, Fuel fuelAmount, Date refuelingDate){
		this.fuelName = fuelName;
		this.fuelPrice = fuelPrice;
		this.fuelAmount = fuelAmount;
		this.refuelingDate = refuelingDate;
		
	}
	
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
		this.refuelingDate = refuelingDate;
	}
	
}

class Fuel {
	
	private double amount;
	
	public Fuel(){
		
	}
	
	public Fuel(String input){
		this.amount = new Double(input.replace(',', '.'));
	}
	
	public double getAmount(String input){
		return new Double(input.replace(',', '.'));
	}

	public void setAmount(String amount) {
		this.amount = new Double(amount.replace(',', '.'));
	}
	
	
}
