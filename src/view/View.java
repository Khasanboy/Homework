package view;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class View {

	private ArrayList<String> months = new ArrayList<String>(Arrays.asList("January", "February", "March", "April",
			"May", "June", "July", "August", "September", "October", "November", "December"));

	private BorderPane root = new BorderPane();

	private ComboBox<String> gasolines = new ComboBox<>();

	private CategoryAxis xAxis = new CategoryAxis();

	private NumberAxis yAxis = new NumberAxis();

	private NumberFormat format = new DecimalFormat("#0.000€");

	private BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

	private XYChart.Series<String, Number> dataSeries = new XYChart.Series<String, Number>();

	private Label status = new Label();
	private ProgressIndicator progress = new ProgressIndicator();

	private Controller controller;

	public View(Controller controller) {

		this.controller = controller;
		
		this.controller.setView(this);

		createPane();

	}

	public Parent asParent() {
		return this.getRoot();
	}
	
	
	public void showBarChart(){
		controller.finalData(this.controller.getAllData(), "All");

		createAndLayoutControls(this.controller);

		setItemsToCombobox(this.controller);
		this.getBarChart().setTitle("Refueling information of the year");
	}
	

	private void setItemsToCombobox(Controller controller) {
		ObservableList<String> list = FXCollections.observableArrayList(controller.getGasolineTypes());
		list.add("All");
		this.getGasolines().setItems(list);
		this.getGasolines().getSelectionModel().select(list.get(list.size() - 1));

		gasolines.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				controller.finalData(controller.getAllData(), newValue);
				updateLayoutsAndControls(controller);
			}

		});
	}


	private void updateLayoutsAndControls(Controller controller) {

		for (int m = 0; m < this.getDataSeries().getData().size(); m++) {
			Node node = this.getDataSeries().getData().get(m).getNode();
			Parent parent = node.parentProperty().get();
			if (parent != null && parent instanceof Group) {
				Group group = (Group) parent;
				group.getChildren().clear();
			}

		}

		this.getDataSeries().getData().clear();
		this.getBarChart().getData().clear();

		boolean[] maxId = new boolean[12];
		boolean[] minId = new boolean[12];

		BigDecimal max = new BigDecimal("0");
		BigDecimal min = controller.getResult().getMonths()[0].getTotalValue();

		for (int k = 0; k < months.size(); k++) {
			if (controller.getResult().getMonths()[k].getTotalValue().doubleValue() > max.doubleValue()) {
				max = controller.getResult().getMonths()[k].getTotalValue();
				maxId[k] = true;
				for (int c = 0; c < maxId.length; c++) {
					if (c != k) {
						maxId[c] = false;
					} else {
						maxId[c] = true;
					}
				}
			} else if (controller.getResult().getMonths()[k].getTotalValue().doubleValue() == max.doubleValue()) {
				maxId[k] = true;
			} else {
				maxId[k] = false;
			}

			if (controller.getResult().getMonths()[k].getTotalValue().doubleValue() < min.doubleValue()) {
				min = controller.getResult().getMonths()[k].getTotalValue();
				minId[k] = true;
				for (int c = 0; c < minId.length; c++) {
					if (c != k) {
						minId[c] = false;
					} else {
						minId[c] = true;
					}
				}
			} else if (controller.getResult().getMonths()[k].getTotalValue().doubleValue() == min.doubleValue()) {
				minId[k] = true;
			} else {
				minId[k] = false;
			}
		}

		for (int i = 0; i < months.size(); i++) {

			final XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(months.get(i),
					controller.getResult().getMonths()[i].getTotalValue());
			// System.out.println(controller.getResult().getMonths()[i].getTotalValue());

			int d = i;
			data.nodeProperty().addListener(new ChangeListener<Node>() {
				@Override
				public void changed(ObservableValue<? extends Node> ov, Node oldNode, final Node node) {

					if (node != null) {
						displayLabelForData(data);

						if (maxId[d]) {
							node.setStyle("-fx-bar-fill: -fx-max;");
						} else if (minId[d]) {
							node.setStyle("-fx-bar-fill: -fx-min;");
						} else {
							node.setStyle("-fx-bar-fill: -fx-other;");
						}

					}
				}
			});
			this.getDataSeries().getData().add(data);
		}

		// this.getBarChart().getData().retainAll();
		this.getBarChart().getData().add(dataSeries);

	}

	private void displayLabelForData(XYChart.Data<String, Number> data) {
		final Node node = data.getNode();
		final Text dataText = new Text(this.format.format(data.getYValue().doubleValue()));
		node.parentProperty().addListener(new ChangeListener<Parent>() {
			@Override
			public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
				Group parentGroup = (Group) parent;
				if (parentGroup != null)
					parentGroup.getChildren().add(dataText);
			}
		});

		node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
				dataText.setLayoutX(Math.round(bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2));
				dataText.setLayoutY(Math.round(bounds.getMinY() - dataText.prefHeight(-1) * 0.1));
			}
		});
	}

	private void createAndLayoutControls(Controller controller) {

		this.getGasolines().setId("combobox");
		this.getGasolines().setPromptText("Select gasoline type");

		this.getRoot().setLeft(this.getGasolines());

		this.getxAxis().setLabel("Month");

		this.getyAxis().setTickLabelFormatter(new StringConverter<Number>() {

			@Override
			public String toString(Number number) {
				return format.format(number.doubleValue());
			}

			@Override
			public Number fromString(String string) {
				try {
					return format.parse(string);
				} catch (ParseException e) {
					e.printStackTrace();
					return 0;
				}
			}

		});

		this.getyAxis().setLabel("Amount of money spent");

		boolean[] maxId = new boolean[12];
		boolean[] minId = new boolean[12];

		BigDecimal max = new BigDecimal("0");
		BigDecimal min = controller.getResult().getMonths()[0].getTotalValue();

		for (int k = 0; k < months.size(); k++) {
			if (controller.getResult().getMonths()[k].getTotalValue().doubleValue() > max.doubleValue()) {
				max = controller.getResult().getMonths()[k].getTotalValue();
				maxId[k] = true;
				for (int c = 0; c < maxId.length; c++) {
					if (c != k) {
						maxId[c] = false;
					} else {
						maxId[c] = true;
					}
				}
			} else if (controller.getResult().getMonths()[k].getTotalValue().doubleValue() == max.doubleValue()) {
				maxId[k] = true;
			} else {
				maxId[k] = false;
			}

			if (controller.getResult().getMonths()[k].getTotalValue().doubleValue() < min.doubleValue()) {
				min = controller.getResult().getMonths()[k].getTotalValue();
				minId[k] = true;
				for (int c = 0; c < minId.length; c++) {
					if (c != k) {
						minId[c] = false;
					} else {
						minId[c] = true;
					}
				}
			} else if (controller.getResult().getMonths()[k].getTotalValue().doubleValue() == min.doubleValue()) {
				minId[k] = true;
			} else {
				minId[k] = false;
			}
		}

		for (int i = 0; i < months.size(); i++) {

			final XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(months.get(i),
					controller.getResult().getMonths()[i].getTotalValue());
			int d = i;
			data.nodeProperty().addListener(new ChangeListener<Node>() {
				@Override
				public void changed(ObservableValue<? extends Node> ov, Node oldNode, final Node node) {

					if (node != null) {
						displayLabelForData(data);

						if (maxId[d]) {
							node.setStyle("-fx-bar-fill: -fx-max;");
						} else if (minId[d]) {
							node.setStyle("-fx-bar-fill: -fx-min;");
						} else {
							node.setStyle("-fx-bar-fill: -fx-other;");
						}

					}
				}
			});

			this.getDataSeries().getData().add(data);

		}

		this.getBarChart().setAnimated(false);
		this.getBarChart().getData().add(this.getDataSeries());
		this.getBarChart().setLegendVisible(false);
		this.getRoot().setCenter(this.getBarChart());

	}

	private void createPane() {
		
		this.setRoot(new BorderPane());
		VBox bottom = new VBox();
		status.setId("status");
		status.setText("Reading the file");
		bottom.getChildren().add(progress);
		bottom.getChildren().add(status);
		
		this.getRoot().setBottom(bottom);
		
		this.getRoot().setPadding(new Insets(10));
	}

	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}
	

	public ProgressIndicator getProgress() {
		return progress;
	}

	public void setProgress(ProgressIndicator progress) {
		this.progress = progress;
	}

	public ComboBox<String> getGasolines() {
		return gasolines;
	}

	public void setGasolines(ComboBox<String> gasolines) {
		this.gasolines = gasolines;
	}

	public CategoryAxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(CategoryAxis xAxis) {
		this.xAxis = xAxis;
	}

	public NumberAxis getyAxis() {
		return yAxis;
	}

	public void setyAxis(NumberAxis yAxis) {
		this.yAxis = yAxis;
	}

	public BarChart<String, Number> getBarChart() {
		return barChart;
	}

	public void setBarChart(BarChart<String, Number> barChart) {
		this.barChart = barChart;
	}

	public XYChart.Series<String, Number> getDataSeries() {
		return dataSeries;
	}

	public void setDataSeries(XYChart.Series<String, Number> dataSeries) {
		this.dataSeries = dataSeries;
	}

	public Label getStatus() {
		return status;
	}

	public void setStatus(Label status) {
		this.status = status;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

}
