package application;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

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
import javafx.scene.layout.BorderPane;
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

	private Controller controller;

	public View(Controller controller) {

		this.controller = controller;

		createPane();

		if (this.controller.isFileOk()) {

			controller.finalData(this.controller.getAllData(), "All");

			createAndLayoutControls(this.controller);

			setItemsToCombobox(this.controller);
		}

		setStatus(this.controller);

	}

	public Parent asParent() {
		return this.getRoot();
	}

	private void setItemsToCombobox(Controller controller) {
		ObservableList<String> list = FXCollections.observableArrayList(controller.getGasolineTypes());
		list.add("All");
		this.getGasolines().setItems(list);

		gasolines.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("chaged changed changed ");
				controller.finalData(controller.getAllData(), newValue);
				updateLayoutsAndControls(controller);
			}

		});
	}

	private void setStatus(Controller controller) {

		this.getStatus().setText(controller.getFileStatus());
		this.getStatus().setId("status");

		this.getRoot().setBottom(status);
	}

	private void updateLayoutsAndControls(Controller controller) {

		this.getDataSeries().getData().clear();
		this.getBarChart().getData().clear();
		System.out.println("data series" + dataSeries.getData().size());
		System.out.println("barchart " + barChart.getData().size());

		for (int i = 0; i < months.size(); i++) {
			final XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(months.get(i), controller.getResult().getMonths()[i].getTotalValue());
			data.nodeProperty().addListener(new ChangeListener<Node>() {
				@Override
				public void changed(ObservableValue<? extends Node> ov, Node oldNode, final Node node) {
					if (node != null) {
						setNodeStyle(data);
						displayLabelForData(data);
					}
				}
			});
			this.getDataSeries().getData().add(data);
		}
		// this.getBarChart().getData().retainAll();
		this.getBarChart().getData().add(dataSeries);

	}

	private void displayLabelForData(XYChart.Data<String, Number> data) {
		DecimalFormat df = new DecimalFormat("0.000");
		final Node node = data.getNode();
		final Text dataText = new Text(df.format(data.getYValue().doubleValue()) + "€");
		node.parentProperty().addListener(new ChangeListener<Parent>() {
			@Override
			public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
				Group parentGroup = (Group) parent;
				parentGroup.getChildren().add(dataText);
			}
		});

		node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
				dataText.setLayoutX(Math.round(bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2));
				dataText.setLayoutY(Math.round(bounds.getMinY() - dataText.prefHeight(-1) * 0.5));
			}
		});
	}

	private void setNodeStyle(XYChart.Data<String, Number> data) {
		Node node = data.getNode();
		if (data.getYValue().intValue() > 8) {
			node.setStyle("-fx-bar-fill: -fx-exceeded;");
		} else if (data.getYValue().intValue() > 5) {
			node.setStyle("-fx-bar-fill: -fx-achieved;");
		} else {
			node.setStyle("-fx-bar-fill: -fx-not-achieved;");
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createAndLayoutControls(Controller controller) {

		this.getGasolines().setId("combobox");
		this.getGasolines().setPromptText("Select gasoline type");

		this.getRoot().setLeft(this.getGasolines());

		this.getxAxis().setLabel("Month");

		// this.getyAxis().forceZeroInRangeProperty().set(true);
		this.getyAxis().setMinHeight(10);

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

		for (int i = 0; i < months.size(); i++) {
			final XYChart.Data<String, Number> data = new XYChart.Data(months.get(i),
					controller.getResult().getMonths()[i].getTotalValue());
			data.nodeProperty().addListener(new ChangeListener<Node>() {
				@Override
				public void changed(ObservableValue<? extends Node> ov, Node oldNode, final Node node) {
					if (node != null) {
						setNodeStyle(data);
						displayLabelForData(data);
					}
				}
			});
			this.getDataSeries().getData().add(data);

		}

		this.getBarChart().setAnimated(false);
		this.getBarChart().getData().add(this.getDataSeries());
		// this.barChart.displayLabelForData(this.getDataSeries());
		this.getBarChart().setLegendVisible(false);
		this.getRoot().setCenter(this.getBarChart());

	}

	private void createPane() {
		this.setRoot(new BorderPane());
		this.getRoot().setPadding(new Insets(10));
	}

	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
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
