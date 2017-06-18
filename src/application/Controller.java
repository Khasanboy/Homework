package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Controller {

	DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	TimeZone tz = TimeZone.getDefault();
	Calendar calendar = Calendar.getInstance(tz);

	public Set<String> gasolineTypes = new HashSet<>();
	public String fileStatus = "";
	public ArrayList<FuelData> allData = new ArrayList<>();
	
	public Result result = new Result();
	
	public Controller(String file){
		
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
						allData.add(new FuelData(parts[0], new BigDecimal(parts[1].replace(",", ".")), Double.parseDouble(parts[2].replace(",", ".")), format.parse(parts[3])));
						
					
					}

				}

			} catch (IOException e) {

				 e.printStackTrace();
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
			} catch (ParseException e) {
				
				e.printStackTrace();
			}

		} else {
			this.fileStatus = "File doesn't exist";
		}
	}
	
	public void finalData(ArrayList<FuelData>data, String name){
		
	     makeResult(FilterDataWithGasolineName(data, name));
		
	}
	
	
	public ArrayList<FuelData> FilterDataWithGasolineName(ArrayList<FuelData>data, String name){
		
		if(name == "All"){
			 ArrayList<FuelData> filteredData = this.allData;
			 return filteredData;
		}
		else{
			ArrayList<FuelData> filteredData = (ArrayList<FuelData>) data.stream().filter(u -> u.getFuelName().equals(name)).collect(Collectors.toList());
			return filteredData;
		}
		
		
	}
	
	public Result makeResult (ArrayList<FuelData> list){
		
		Month allMonths[] = new Month[12];
		
		for(int l = 0; l<result.getMonths().length; l++){
			allMonths[l] = (new Month(l,new BigDecimal("0"), 0.00));
		}
		
		
		for(int i= 0; i<list.size(); i++){
			calendar.setTime(list.get(i).getRefuelingDate());
			
			for(int j=0; j<allMonths.length; j++){
				if(calendar.get(Calendar.MONTH) == allMonths[j].getId()){
					
					allMonths[j].setAmount(allMonths[j].getAmount()+list.get(i).getFuelAmount());
					allMonths[j].setTotalValue(allMonths[j].getTotalValue().add(list.get(i).getTotalValue()));
				}
			}
			
			result.setMonths(allMonths);
		}
		
		return result;
	}

}
