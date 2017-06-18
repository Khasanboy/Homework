package application;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class View {
	
	private BorderPane root = new BorderPane();
	private Label logo = new Label("Refueling information");
	
	private ComboBox<String> gasolines = new ComboBox<>();
	
	private CategoryAxis xAxis = new CategoryAxis();
	private NumberAxis yAxis = new NumberAxis();
	private BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
	private XYChart.Series dataSeries1 = new XYChart.Series();
	
	private Map<String, XYChart.Series> myMap = new HashMap<String, XYChart.Series>();
	
	private Label status = new Label();
	
	private Controller controller;
	
	public View(Controller controller){
		
		this.controller = controller;
		
		 createPane();
		 
		 controller.finalData(controller.allData, "All");

	     createAndLayoutControls(controller);
	     
	     setItemsToCombobox(controller);
	     
	     setStatus(controller);

	}
	
	public Parent asParent(){
		return root;
	}
	
	private void setItemsToCombobox(Controller model){
		ObservableList<String> list = FXCollections.observableArrayList(model.gasolineTypes);
		list.add("All");
		gasolines.setItems(list);
		
		gasolines.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("chaged changed changed ");
				controller.finalData(controller.allData, newValue);
				updateLayoutsAndControls(controller);
			}
			
		});
	}
	
	private void setStatus(Controller model){
			status.setText(model.fileStatus);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void updateLayoutsAndControls(Controller controller){
		
		dataSeries1.getData().clear();
		barChart.getData().clear();
		System.out.println("data series" + dataSeries1.getData().size());
		System.out.println("barchart "+ barChart.getData().size());
		
		for(int i=0; i<12; i++){
			dataSeries1.getData().add(new XYChart.Data(" "+i, controller.result.getMonths()[i].getTotalValue()));
		}
		barChart.getData().retainAll();
		barChart.getData().add(dataSeries1);
		
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createAndLayoutControls(Controller controller){
		
		logo.setId("logo");
		
		root.setTop(logo);
		
		gasolines.setId("combobox");
		gasolines.setPromptText("Select gasoline type");
		
		root.setLeft(gasolines);
		
		xAxis.setLabel("Month && Amount");

		yAxis.setLabel("Value");

		dataSeries1.setName("2014");
		
		for(int i=0; i<12; i++){
			dataSeries1.getData().add(new XYChart.Data(" "+i, controller.result.getMonths()[i].getTotalValue()));
		}
	
		barChart.setAnimated(false);
		barChart.getData().add(dataSeries1);
		
		
		root.setCenter(barChart);
		
		
		status.setId("status");
		
		root.setBottom(status);
	}
	
	private void createPane(){
		root = new BorderPane();
		root.setPadding(new Insets(10));
	}
	

}
