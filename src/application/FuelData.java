package application;

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

/*

if (allData.getFuelName().equals("")) {
	allData.setFuelName(parts[0].toString());
	allData.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));
}

allData.setFuelAmount(allData.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));

*/

//System.out.println(allData.getTotalValue());

//calendar.setTime(format.parse(parts[3]));

/*
switch (calendar.get(Calendar.MONTH)) {

case 0:
	if (jan.getFuelName().equals("")) {
		jan.setFuelName(parts[0].toString());
		jan.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));
	}

	jan.setFuelAmount(jan.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));	
	break;

case 1:
	if (feb.getFuelName().equals("")) {
		feb.setFuelName(parts[0].toString());
		feb.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));

	}

	feb.setFuelAmount(feb.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));
	break;
	
case 2:
	if (mar.getFuelName().equals("")) {
		mar.setFuelName(parts[0].toString());
		mar.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));
	}

	mar.setFuelAmount(mar.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));
	break;
	
case 3:
	if (apr.getFuelName().equals("")) {
		apr.setFuelName(parts[0].toString());
		apr.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));
	}

	apr.setFuelAmount(apr.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));
	break;
	
case 4:
	if (may.getFuelName().equals("")) {
		may.setFuelName(parts[0].toString());
		may.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));
	}

	may.setFuelAmount(may.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));
	break;
	
case 5:
	if (jun.getFuelName().equals("")) {
		jun.setFuelName(parts[0].toString());
		jun.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));
	}

	jun.setFuelAmount(jun.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));
	break;
	
case 6:
	if (jul.getFuelName().equals("")) {
		jul.setFuelName(parts[0].toString());
		jul.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));
	}

	jul.setFuelAmount(jul.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));
	break;
	
case 7:
	if (aug.getFuelName().equals("")) {
		aug.setFuelName(parts[0].toString());
		aug.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));
	}

	aug.setFuelAmount(aug.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));
	break;
	
case 8:
	if (sep.getFuelName().equals("")) {
		sep.setFuelName(parts[0].toString());
		sep.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));
	}

	sep.setFuelAmount(sep.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));
	break;
	
case 9:
	if (oct.getFuelName().equals("")) {
		oct.setFuelName(parts[0].toString());
		oct.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));
	}

	oct.setFuelAmount(oct.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));
	break;
	
case 10:
	if (nov.getFuelName().equals("")) {
		nov.setFuelName(parts[0].toString());
		nov.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));
	}
	
	nov.setFuelAmount(nov.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));
	break;
	
case 11:
	if (dec.getFuelName().equals("")) {
		dec.setFuelName(parts[0].toString());
		dec.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));
	}

	dec.setFuelAmount(dec.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));
	break;
}

*/

