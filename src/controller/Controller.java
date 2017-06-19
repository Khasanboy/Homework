package controller;

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

import model.FuelData;
import model.Month;
import model.Result;

public class Controller {

	private DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	private TimeZone tz = TimeZone.getDefault();
	private Calendar calendar = Calendar.getInstance(tz);

	private Set<String> gasolineTypes = new HashSet<>();
	private String fileStatus = "";
	private ArrayList<FuelData> allData = new ArrayList<>();
	
	private boolean fileOk;
	private boolean readingDone = false;
	private Result result = new Result();
	private Thread thread;
	
	public Controller(String filePath){
		
		if (Files.exists(Paths.get(filePath))) {
			this.setFileOk(true);
			this.setFileStatus("");
			
			// thread = new Thread(){
			//	 public void run(){
			    	readFileAndBuildData(filePath);
			//    }
			// };

		 // thread.start();
			
			
		} else {
			this.setFileOk(false);
			this.setFileStatus("File doesn't exist");
		}
	}
	
	private void readFileAndBuildData(String filePath){
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));

			for (String string : lines) {
				
				String[] parts = string.split(Pattern.quote("|"));
				
				if(parts.length!= 4){
					this.setFileOk(false);
					this.setFileStatus("File contains some lines without all information or more than 4 ");
					
				}
				
				else if (new BigDecimal(parts[1].replace(",", ".")).intValue()<0 || Double.parseDouble(parts[2].replace(",", ".")) < 0) {
					this.setFileOk(false);
					this.setFileStatus("File contains negative values");
					
				} else {

					this.getGasolineTypes().add(parts[0]);
					this.getAllData().add(new FuelData(parts[0], new BigDecimal(parts[1].replace(",", ".")), Double.parseDouble(parts[2].replace(",", ".")), format.parse(parts[3])));
					
				}

			}
			
			
		} catch (IOException e) {
			this.setFileOk(false);
			this.setFileStatus("Invalid input");
			 e.printStackTrace();
		
		} catch (NumberFormatException e) {
			this.setFileOk(false);
			this.setFileStatus("File containts not number characters for numbers");
			e.printStackTrace();
		} catch (ParseException e) {
			this.setFileOk(false);
			this.setFileStatus("File containts invalid dateTime format");
			e.printStackTrace();
		}
		
		/*
		  finally{
			this.setReadingDone(true);
		}
		*/

	}
	
	public void finalData(ArrayList<FuelData>data, String name){
		
	     makeResult(FilterDataWithGasolineName(data, name));
		
	}
	
	
	public ArrayList<FuelData> FilterDataWithGasolineName(ArrayList<FuelData>data, String name){
		
		if(name == "All"){
			 ArrayList<FuelData> filteredData = this.getAllData();
			 return filteredData;
		}
		else{
			ArrayList<FuelData> filteredData = (ArrayList<FuelData>) data.stream().filter(u -> u.getFuelName().equals(name)).collect(Collectors.toList());
			return filteredData;
		}
		
		
	}
	
	public Result makeResult (ArrayList<FuelData> list){
		
		Month allMonths[] = new Month[12];
		
		for(int l = 0; l<this.getResult().getMonths().length; l++){
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
			
			this.getResult().setMonths(allMonths);
		}
		
		return this.getResult();
	}
	
	
	
	
	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean isReadingDone() {
		return readingDone;
	}

	public void setReadingDone(boolean readingDone) {
		this.readingDone = readingDone;
	}

	public Set<String> getGasolineTypes() {
		return gasolineTypes;
	}

	public void setGasolineTypes(Set<String> gasolineTypes) {
		this.gasolineTypes = gasolineTypes;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public ArrayList<FuelData> getAllData() {
		return allData;
	}

	public void setAllData(ArrayList<FuelData> allData) {
		this.allData = allData;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public boolean isFileOk() {
		return fileOk;
	}

	public void setFileOk(boolean fileOk) {
		this.fileOk = fileOk;
	}

}
