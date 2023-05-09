package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;


public class M5 extends Application {
	 @Override
	    public void start(Stage primaryStage) {
		 try {
				Parent root = FXMLLoader.load(getClass().getResource("fxml/Main_scroll.fxml"));
		        Scene scene = new Scene(root, 1080, 720);
		        
		        AnchorPane main = FXMLLoader.load(getClass().getResource("fxml/method.fxml"));
		        TextField name = (TextField)main.lookup("#Fname");
		        CheckBox stat = (CheckBox)main.lookup("#Fstatic");
		        ChoiceBox<String> acc = (ChoiceBox)main.lookup("#Facc");
		    	ObservableList<String> access = FXCollections.observableArrayList("default","public","private","protected");
		        acc.setItems(access);
		        acc.getSelectionModel().select(0);
		        stat.setSelected(true);
		        name.setText("main");
		        primaryStage.setTitle("Facile J");
		        primaryStage.setScene(scene);
		        ScrollPane sp = (ScrollPane)root;
		        AnchorPane ap = (AnchorPane)sp.getContent();
		        AnchorPane cl = (AnchorPane)ap.lookup("#Class");
		        TextField cls = (TextField)cl.lookup("#clss");
		        cls.setText("Sample");
		        TextField pck = (TextField)cl.lookup("#packg");
		        pck.setText("test");
		        VBox vb = (VBox)cl.lookup("#program");
		        vb.getChildren().add(main);
		        primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}

	    }
	    public static void main(String[] args) {
	        launch(args);
	    }

}
