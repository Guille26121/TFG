package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ControllerMethod implements Initializable {

	@FXML
    private AnchorPane method;
	@FXML
    private VBox FCont;
	
	@FXML
	private ImageView DragMethod;


    DraggableMaker draggableMaker = new DraggableMaker();
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       draggableMaker.makeDraggable(DragMethod);
    }
}
