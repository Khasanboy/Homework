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
	
	private double totalValue; 

	public FuelData(String fuelName, Fuel fuelPrice, Fuel fuelAmount){
		this.fuelName = fuelName;
		this.fuelPrice = fuelPrice;
		this.fuelAmount = fuelAmount;
		
	}
	
	public Fuel updateFuelAmount(Fuel one, Fuel two ){
		return new Fuel(one.getAmount()+two.getAmount()+"");
	}
	
	public double getTotalValue() {
		return this.totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
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
	
	
}

class Fuel {
	
	public double amount;
	
	public Fuel(String input){
		this.amount = new Double(input.replace(',', '.'));
	}
	
	public double getAmount(){
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = new Double(amount.replace(',', '.'));
	}
	
	public Fuel addAmount(double amount2){
		return new Fuel((amount2)+"");
	 
	}
	
	
}
