package application;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ControllerStCall implements Initializable {

	@FXML
    private AnchorPane StCall;
	
	@FXML
	private ImageView DragStCall;
	
	@FXML
	private HBox SCParams;


    DraggableMaker draggableMaker = new DraggableMaker();
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       draggableMaker.makeDraggable(DragStCall);

    }
}
