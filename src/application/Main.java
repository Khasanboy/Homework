package application;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	
	static File file;

	public static void main(String[] args) {
		launch(args);
		if(args.length>0){
			 file = new File(args[0]);
			 System.out.println(file.getAbsolutePath());
		}
			
	}

	@Override
	public void start(Stage primaryStage) {
		Model model = new Model();
		Controller controller = new Controller(model, file==null?null:file);
		View view = new View(controller, model); 
		
		try {			
			Scene scene = new Scene(view.asParent(), 1000, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Barchart generator");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
