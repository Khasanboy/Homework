package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class Controller {

	DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	TimeZone tz = TimeZone.getDefault();
	Calendar calendar = Calendar.getInstance(tz);

	public Set<String> gasolineTypes = new HashSet<>();
	public String fileStatus = "";
	public FuelData allData = new FuelData("", new BigDecimal("0.000"), 0.00);

	public FuelData jan = new FuelData("", new BigDecimal("0.000"), 0.00);
	public FuelData feb = new FuelData("", new BigDecimal("0.000"), 0.00);
	public FuelData mar = new FuelData("", new BigDecimal("0.000"), 0.00);
	public FuelData apr = new FuelData("", new BigDecimal("0.000"), 0.00);
	public FuelData may = new FuelData("", new BigDecimal("0.000"), 0.00);
	public FuelData jun = new FuelData("", new BigDecimal("0.000"), 0.00);
	public FuelData jul = new FuelData("", new BigDecimal("0.000"), 0.00);
	public FuelData aug = new FuelData("", new BigDecimal("0.000"), 0.00);
	public FuelData sep = new FuelData("", new BigDecimal("0.000"), 0.00);
	public FuelData oct = new FuelData("", new BigDecimal("0.000"), 0.00);
	public FuelData nov = new FuelData("", new BigDecimal("0.000"), 0.00);
	public FuelData dec = new FuelData("", new BigDecimal("0.000"), 0.00);

	public Controller(String file) throws ParseException {
		
		if (Files.exists(Paths.get(file))) {
			this.fileStatus = "File is ok";
			try {
				List<String> lines = Files.readAllLines(Paths.get(file));

				for (String string : lines) {
					
					String[] parts = string.split(Pattern.quote("|"));
					
					if(parts.length!= 4){
						
						this.fileStatus = "File contains some lines without all information";
						
					}
					
					else if (new BigDecimal(parts[1].replace(",", ".")).intValue()<0 || Double.parseDouble(parts[2].replace(",", ".")) < 0) {
						
						this.fileStatus = "File contains negative values";
						
					} else {

						gasolineTypes.add(parts[0]);

						if (allData.getFuelName().equals("")) {
							allData.setFuelName(parts[0].toString());
							allData.setFuelPrice(new BigDecimal(parts[1].replace(",", ".")));
						}

						allData.setFuelAmount(allData.getFuelAmount()+Double.parseDouble(parts[2].replace(",", ".")));
						
						System.out.println(allData.getTotalValue());

						calendar.setTime(format.parse(parts[3]));
						
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
					}

				}

			} catch (IOException e) {

				 e.printStackTrace();
			}

		} else {
			this.fileStatus = "File doesn't exist";
		}
	}

}
