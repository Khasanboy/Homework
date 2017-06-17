package application;

import java.io.File;
import java.text.ParseException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	
	static String filePath;

	public static void main(String[] args) {
		
		if(args.length>0){
			 filePath = args[0];
			 System.out.println(filePath);
		}
		launch(args);
			
	}

	@Override
	public void start(Stage primaryStage) throws ParseException {
		//FuelData model = new FuelData();
		Controller controller = new Controller(filePath);
		View view = new View(controller); 
		
		try {			
			Scene scene = new Scene(view.asParent(), 1000, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Barchart generator");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
