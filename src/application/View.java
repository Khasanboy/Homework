package application;

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
	
	private BorderPane root;
	private HBox top = new HBox();
	private Label logo = new Label("Refueling information");
	
	private VBox left = new VBox();
	private ComboBox<String> gasolines = new ComboBox<>();
	
	private StackPane center = new StackPane();
	private CategoryAxis xAxis = new CategoryAxis();
	private NumberAxis yAxis = new NumberAxis();
	private BarChart barChart = new BarChart<>(xAxis, yAxis);
	private XYChart.Series dataSeries1 = new XYChart.Series();
	
	private HBox bottom = new HBox();
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
				// TODO Auto-generated method stub
				controller.finalData(controller.allData, newValue);
				updateLayoutsAndControls(controller);
				setItemsToCombobox(controller);
			}
			
		});
	}
	
	private void setStatus(Controller model){
			status.setText(model.fileStatus);
	}
	
	
	public void comboChanged(ActionEvent event){
		System.out.println("Selected ");
		this.controller.finalData(this.controller.allData, gasolines.getValue());
		createAndLayoutControls(controller);
	    
	}
	
	private void updateLayoutsAndControls(Controller controller){
		for(int i=0; i<12; i++){
			dataSeries1.getData().add(new XYChart.Data(" "+i, controller.result.getMonths()[i].getTotalValue()));
			barChart.getData().add(dataSeries1);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void createAndLayoutControls(Controller controller){
		
		//top
		//top = new HBox();
		top.setId("top");
		
		//Label logo = new Label("Refueling information");
		logo.setId("logo");
		
		top.getChildren().add(logo);
		
		root.setTop(top);
		
		//left
		//left = new VBox();
		left.setId("left");
		
		//gasolines =  new ComboBox<>();
		gasolines.setId("combobox");
		gasolines.setPromptText("Select gasoline type");
		
		left.getChildren().add(gasolines);
		
		root.setLeft(left);

		//center
		//center = new StackPane();
		center.setId("center");
		
		//xAxis = new CategoryAxis();
		xAxis.setLabel("Month && Amount");

		//yAxis = new NumberAxis();
		yAxis.setLabel("Value");

		//barChart = new BarChart(xAxis, yAxis);

		//dataSeries1 = new XYChart.Series();
		dataSeries1.setName("2014");
		
		for(int i=0; i<12; i++){
			dataSeries1.getData().add(new XYChart.Data(" "+i, controller.result.getMonths()[i].getTotalValue()));
		}
/*
		dataSeries1.getData().add(new XYChart.Data("January", controller.jan.getTotalValue()));
		dataSeries1.getData().add(new XYChart.Data("February", controller.feb.getTotalValue()));
		dataSeries1.getData().add(new XYChart.Data("March", controller.mar.getTotalValue()));
		dataSeries1.getData().add(new XYChart.Data("April", controller.apr.getTotalValue()));
		dataSeries1.getData().add(new XYChart.Data("May", controller.may.getTotalValue()));
		dataSeries1.getData().add(new XYChart.Data("June", controller.jun.getTotalValue()));
		dataSeries1.getData().add(new XYChart.Data("July", controller.jul.getTotalValue()));
		dataSeries1.getData().add(new XYChart.Data("August", controller.aug.getTotalValue()));
		dataSeries1.getData().add(new XYChart.Data("September", controller.sep.getTotalValue()));
		dataSeries1.getData().add(new XYChart.Data("October", controller.oct.getTotalValue()));
		dataSeries1.getData().add(new XYChart.Data("November", controller.nov.getTotalValue()));
		dataSeries1.getData().add(new XYChart.Data("December", controller.dec.getTotalValue()));
*/
		barChart.getData().add(dataSeries1);
		
		center.getChildren().add(barChart);
		
		root.setCenter(center);
		
		//bottom
		//bottom = new HBox();
		bottom.setId("bottom");
		
		//status = new Label();
		status.setId("status");
		
		bottom.getChildren().add(status);
		
		root.setBottom(bottom);
	}
	
	private void createPane(){
		root = new BorderPane();
		root.setPadding(new Insets(10));
	}
	

}
