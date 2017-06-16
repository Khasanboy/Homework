package application;

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
	private StackPane center;
	private HBox bottom;
	
	
	
	private Controller controller;
	private Model model;
	
	public View(Controller controller, Model model){
		
		this.controller = controller;
		this.model = model;
		
		 createPane();

	     createAndLayoutControls();

	     //updateControllerFromListeners();

	     //observeModelAndUpdateControls();
	}
	
	public Parent asParent(){
		return root;
	}
	
	private void observerModelAndUpdateControls(){
		
	}
	
	private void updateIfNeeded(){
		
	}
	
	private void createAndLayoutControls(){
		
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
		ComboBox<String> gasolines = new ComboBox<>();
		gasolines.setId("combobox");
		gasolines.setPromptText("Select gasoline type");
		left.getChildren().add(gasolines);
		root.setLeft(left);

		//center
		center = new StackPane();
		center.setId("center");
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Devices");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Visits");

		BarChart barChart = new BarChart(xAxis, yAxis);

		XYChart.Series dataSeries1 = new XYChart.Series();
		dataSeries1.setName("2014");

		dataSeries1.getData().add(new XYChart.Data("Desktop", 567));
		dataSeries1.getData().add(new XYChart.Data("Phone", 65));
		dataSeries1.getData().add(new XYChart.Data("Tablet", 23));

		barChart.getData().add(dataSeries1);
		center.getChildren().add(barChart);
		root.setCenter(center);
		
		//bottom
		bottom = new HBox();
		bottom.setId("bottom");
		Label status = new Label();
		status.setId("status");
		status.setText("Successful");
		bottom.getChildren().add(status);
		root.setBottom(bottom);
	}
	
	private void createPane(){
		root = new BorderPane();
		root.setPadding(new Insets(10));
		//BorderPane.setMargin(top, new Insets(10));
		//BorderPane.setMargin(left, new Insets(10));
		//BorderPane.setMargin(center, new Insets(10));
		//BorderPane.setMargin(bottom, new Insets(10));
	}
	

}
