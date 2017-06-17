package application;

import java.io.IOException;
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

	private FuelData model;
	DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	TimeZone tz = TimeZone.getDefault();
	Calendar calendar = Calendar.getInstance(tz);

	public Set<String> gasolineTypes = new HashSet<>();
	public String fileStatus = "";
	public FuelData allData = new FuelData("", new Fuel("0.000"), new Fuel("0.00"));

	public FuelData jan = new FuelData("", new Fuel("0.000"), new Fuel("0.00"));
	public FuelData feb = new FuelData("", new Fuel("0.000"), new Fuel("0.00"));
	public FuelData mar = new FuelData("", new Fuel("0.000"), new Fuel("0.00"));
	public FuelData apr = new FuelData("", new Fuel("0.000"), new Fuel("0.00"));
	public FuelData may = new FuelData("", new Fuel("0.000"), new Fuel("0.00"));
	public FuelData jun = new FuelData("", new Fuel("0.000"), new Fuel("0.00"));
	public FuelData jul = new FuelData("", new Fuel("0.000"), new Fuel("0.00"));
	public FuelData aug = new FuelData("", new Fuel("0.000"), new Fuel("0.00"));
	public FuelData sep = new FuelData("", new Fuel("0.000"), new Fuel("0.00"));
	public FuelData oct = new FuelData("", new Fuel("0.000"), new Fuel("0.00"));
	public FuelData nov = new FuelData("", new Fuel("0.000"), new Fuel("0.00"));
	public FuelData dec = new FuelData("", new Fuel("0.000"), new Fuel("0.00"));

	public Controller(String file) throws ParseException {

		System.out.println("Controller is built");

		if (Files.exists(Paths.get(file))) {
			this.fileStatus = "File is ok";
			try {
				List<String> lines = Files.readAllLines(Paths.get(file));

				for (String string : lines) {
					String[] parts = string.split(Pattern.quote("|"));
					System.out.println(parts.length);
					
					if(parts.length!= 4){
						this.fileStatus = "File contains some lines without all information";
					}
					
					else if (new Fuel(parts[1]).getAmount() < 0 || new Fuel(parts[2]).getAmount() < 0) {
						
						this.fileStatus = "File contains negative values";
						
					} else {

						gasolineTypes.add(parts[0]);

						if (allData.getFuelName().equals("")) {
							allData.setFuelName(parts[0].toString());
							allData.setFuelPrice(new Fuel(parts[1]));
						}

						allData.setFuelAmount(allData.updateFuelAmount(allData.getFuelAmount(),new Fuel(parts[2])));
						System.out.println("all fuel amount " + allData.getFuelAmount().amount);

						calendar.setTime(format.parse(parts[3]));

						switch (calendar.get(Calendar.MONTH)) {

						case 0:
							if (jan.getFuelName().equals("")) {
								jan.setFuelName(parts[0].toString());
								jan.setFuelPrice(new Fuel(parts[1]));
							}

							//jan.addFuelAmount(parts[2]);

						case 1:
							if (feb.getFuelName().equals("")) {
								feb.setFuelName(parts[0].toString());
								feb.setFuelPrice(new Fuel(parts[1]));

							}

							//feb.addFuelAmount(parts[2]);
						case 2:
							if (mar.getFuelName().equals("")) {
								mar.setFuelName(parts[0].toString());
								mar.setFuelPrice(new Fuel(parts[1]));
							}

							//mar.addFuelAmount(parts[2]);
						case 3:
							if (apr.getFuelName().equals("")) {
								apr.setFuelName(parts[0].toString());
								apr.setFuelPrice(new Fuel(parts[1]));
							}

							//apr.addFuelAmount(parts[2]);
						case 4:
							if (may.getFuelName().equals("")) {
								may.setFuelName(parts[0].toString());
								may.setFuelPrice(new Fuel(parts[1]));
							}

							//may.addFuelAmount(parts[2]);
						case 5:
							if (jun.getFuelName().equals("")) {
								jun.setFuelName(parts[0].toString());
								jun.setFuelPrice(new Fuel(parts[1]));
							}

							//jun.addFuelAmount(parts[2]);
						case 6:
							if (jul.getFuelName().equals("")) {
								jul.setFuelName(parts[0].toString());
								jul.setFuelPrice(new Fuel(parts[1]));
							}

							//jul.addFuelAmount(parts[2]);
						case 7:
							if (aug.getFuelName().equals("")) {
								aug.setFuelName(parts[0].toString());
								aug.setFuelPrice(new Fuel(parts[1]));
							}

							//aug.addFuelAmount(parts[2]);
						case 8:
							if (sep.getFuelName().equals("")) {
								sep.setFuelName(parts[0].toString());
								sep.setFuelPrice(new Fuel(parts[1]));
							}

							//sep.addFuelAmount(parts[2]);
						case 9:
							if (oct.getFuelName().equals("")) {
								oct.setFuelName(parts[0].toString());
								oct.setFuelPrice(new Fuel(parts[1]));
							}

							//oct.addFuelAmount(parts[2]);
						case 10:
							if (nov.getFuelName().equals("")) {
								nov.setFuelName(parts[0].toString());
								nov.setFuelPrice(new Fuel(parts[1]));
							}

							//nov.addFuelAmount(parts[2]);
						case 11:
							if (dec.getFuelName().equals("")) {
								dec.setFuelName(parts[0].toString());
								dec.setFuelPrice(new Fuel(parts[1]));
							}

							//dec.addFuelAmount(parts[2]);
						}
					}

				}
				// System.out.println(lines.toString());
				// System.out.println(jan.getTotalValue());

			} catch (IOException e) {

				// e.printStackTrace();
			}

			System.out.println(gasolineTypes);
			System.out.println(allData);
			System.out.println(this.fileStatus);
			System.out.println(jan.getTotalValue());

		} else {
			// fileStatus = false;
			this.fileStatus = "File doesn't exist";
		}
	}

}
