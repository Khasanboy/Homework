package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private HBox top;
	
	private VBox left;
	private ComboBox<String> gasolines;
	
	private StackPane center;
	private CategoryAxis xAxis;
	private NumberAxis yAxis;
	private BarChart barChart;
	private XYChart.Series dataSeries1;
	
	private HBox bottom;
	private Label status;
	
	private Controller controller;
	
	public View(Controller controller){
		
		this.controller = controller;
		
		 createPane();

	     createAndLayoutControls(controller);
	     
	     setItemsToCombobox(controller);
	     
	     setStatus(controller);

	     //updateControllerFromListeners();

	     //observeModelAndUpdateControls();
	}
	
	public Parent asParent(){
		return root;
	}
	
	private void setItemsToCombobox(Controller model){
		ObservableList<String> list = FXCollections.observableArrayList(model.gasolineTypes);
		list.add("All");
		gasolines.setItems(list);
	}
	
	private void setStatus(Controller model){
		
			status.setText(model.fileStatus);
		
	}
	
	private void observerModelAndUpdateControls(){
		
	}
	
	
	private void createAndLayoutControls(Controller controller){
		
		//top
		top = new HBox();
		top.setId("top");
		
		Label logo = new Label("Refueling information");
		logo.setId("logo");
		
		top.getChildren().add(logo);
		
		root.setTop(top);
		
		//left
		left = new VBox();
		left.setId("left");
		
		gasolines = new ComboBox<>();
		gasolines.setId("combobox");
		gasolines.setPromptText("Select gasoline type");
		
		left.getChildren().add(gasolines);
		
		root.setLeft(left);

		//center
		center = new StackPane();
		center.setId("center");
		
		xAxis = new CategoryAxis();
		xAxis.setLabel("Month && Amount");

		yAxis = new NumberAxis();
		yAxis.setLabel("Value");

		barChart = new BarChart(xAxis, yAxis);

		dataSeries1 = new XYChart.Series();
		dataSeries1.setName("2014");

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

		barChart.getData().add(dataSeries1);
		
		center.getChildren().add(barChart);
		
		root.setCenter(center);
		
		//bottom
		bottom = new HBox();
		bottom.setId("bottom");
		
		status = new Label();
		status.setId("status");
		
		bottom.getChildren().add(status);
		
		root.setBottom(bottom);
	}
	
	private void createPane(){
		root = new BorderPane();
		root.setPadding(new Insets(10));
	}
	

}
