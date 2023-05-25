package application;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ControllerCall implements Initializable {

	@FXML
    private AnchorPane Call;
	
	@FXML
	private ImageView DragCall;
	
	@FXML
	private HBox CParams;


    DraggableMaker draggableMaker = new DraggableMaker();
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       draggableMaker.makeDraggable(DragCall);

    }
}
