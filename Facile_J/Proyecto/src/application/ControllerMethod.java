package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ControllerMethod implements Initializable {

	@FXML
    private AnchorPane method;
	@FXML
    private VBox FCont;
	
	@FXML
	private ChoiceBox Facc;
	
	@FXML
	private ImageView DragMethod;


    DraggableMaker draggableMaker = new DraggableMaker();
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       ObservableList<String> access = FXCollections.observableArrayList("default","public","private","protected");
       Facc.setItems(access);
       draggableMaker.makeDraggable(DragMethod);

    }
}
