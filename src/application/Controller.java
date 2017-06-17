package application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Controller{
	
	private FuelData model;
	
	public Set<String> gasolineTypes = new HashSet<>();
	public ArrayList<FuelData> allData = new ArrayList<>();
	public String fileStatus="";
	
	DateFormat format = new SimpleDateFormat("dd.MM.yyyy"); 
	
	public Controller(String file) throws ParseException{
		
		System.out.println("Controller is built");
		
		if(Files.exists(Paths.get(file))){
			this.fileStatus = "";
			try {
				List<String> lines = Files.readAllLines(Paths.get(file));
				
				for (String string : lines) {
					String[] parts = string.split(Pattern.quote("|"));
					
					if(new Fuel().getAmount((parts[1]))<0 || new Fuel().getAmount((parts[2]))<0){
						this.fileStatus = "File contains negative values";
					}
					
					gasolineTypes.add(parts[0]);
					allData.add(new FuelData(parts[0].toString(), new Fuel(parts[1]), new Fuel(parts[2]), format.parse(parts[3])));
					
				}
				System.out.println(lines.toString());
				
			} catch (IOException e) {
				
				//e.printStackTrace();
			}
			
			System.out.println(gasolineTypes);
			System.out.println(allData);
			System.out.println(this.fileStatus);
		}else{
			//fileStatus = false;
			this.fileStatus = "File doesn't exist";
		}
	}

}
