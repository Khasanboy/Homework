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
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.FuelData;
import model.Month;
import model.Result;
import view.View;

public class Controller{

	private DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	private TimeZone tz = TimeZone.getDefault();
	private Calendar calendar = Calendar.getInstance(tz);

	private Set<String> gasolineTypes = new HashSet<>();
	private ArrayList<FuelData> allData = new ArrayList<>();

	private Result result = new Result();
	private View view;
	private FileReadingServices readingService;
	private List<String> lines;
	
	public Controller(String filePath) {
		
		readingService = new FileReadingServices(this, filePath);
		
		readingService.start();
		
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

	
	public class FileReadingServices extends Service<Void> {
		
		private String filePath;
		private Controller controller;
		
		public FileReadingServices(Controller controller, String filePath){
			this.filePath = filePath;
			this.controller = controller;
		}
		
		@Override
	    protected void succeeded() {
	       this.controller.getView().showBarChart();
	       this.controller.view.getProgress().visibleProperty().set(false);
	       this.controller.view.getStatus().setText("");
	    }

	    @Override
	    protected void failed() {
	    	//System.out.println("File not found");
	    	this.controller.view.getProgress().visibleProperty().set(false);
	    	this.controller.view.getStatus().setText("File not found");
	    }

	    @Override
	    protected void cancelled() {
	        //statusMessagesProperty().set("Connecting cancelled.");
	        //connectedProperty().set(false);
	    }

		@Override
		protected Task<Void> createTask() {
			 return new Task<Void>() {
	             @Override
	             protected Void call() throws Exception {
	            	
	            	 try {
	         			List<String> lines = Files.readAllLines(Paths.get(filePath));

	         			for (String string : lines) {
	         				
	         				String[] parts = string.split(Pattern.quote("|"));
	         				
	         				if(parts.length!= 4){
	         					
	         					 updateMessage("File contains not 4 patrs in the line");
	         				}
	         				
	         				else if (new BigDecimal(parts[1].replace(",", ".")).intValue()<0 || Double.parseDouble(parts[2].replace(",", ".")) < 0) {
	         					 updateMessage("File contains negative values");
	         					
	         				} else {

	         					controller.getGasolineTypes().add(parts[0]);
	         					controller.getAllData().add(new FuelData(parts[0], new BigDecimal(parts[1].replace(",", ".")), Double.parseDouble(parts[2].replace(",", ".")), format.parse(parts[3])));
	         					
	         				}

	         			}
	         			
	         			
	         		} catch (IOException e) {
	         			controller.view.getStatus().setText("File is not found");
	         			System.out.println("File not found");
	         			 e.printStackTrace();
	         		
	         		} catch (NumberFormatException e) {
	         			updateMessage("File containts not number characters for numbers");
	         			//controller.view.getStatus().setText("File containts not number characters for numbers");
	         			e.printStackTrace();
	         		} catch (ParseException e) {
	         			updateMessage("File containts invalid Date format");
	         			//controller.view.getStatus().setText("File containts invalid dateTime format");
	         			e.printStackTrace();
	         		}
					return null;
	             }
	         };
		}

	}
	

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}


	public Set<String> getGasolineTypes() {
		return gasolineTypes;
	}

	public void setGasolineTypes(Set<String> gasolineTypes) {
		this.gasolineTypes = gasolineTypes;
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

	public List<String> getLines() {
		return lines;
	}


	public void setLines(List<String> lines) {
		this.lines = lines;
	}


}
