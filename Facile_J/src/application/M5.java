package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class M5 extends Application {
	 @Override
	    public void start(Stage primaryStage) {
		 try {
				Parent root = FXMLLoader.load(getClass().getResource("Main_scroll.fxml"));
		        Scene scene = new Scene(root, 1080, 720);
		    
		        primaryStage.setTitle("Facile J");
		        primaryStage.setScene(scene);	        
		        primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}

	    }
	    public static void main(String[] args) {
	        launch(args);
	    }

}
