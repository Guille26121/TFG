package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControllerElseIf implements Initializable {
	
	@FXML
    private AnchorPane ElseIf;
	@FXML
    private VBox EIContent;
	@FXML
    private HBox EICond;
	
	@FXML
	private ImageView DragElseIf;


    DraggableMaker draggableMaker = new DraggableMaker();
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       draggableMaker.makeDraggable(DragElseIf);
    }


}
