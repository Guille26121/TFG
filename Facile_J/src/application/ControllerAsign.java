package application;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ControllerAsign implements Initializable {

	@FXML
    private AnchorPane Asign;
	
	@FXML
	private ImageView DragAsign;
	
	@FXML
	private HBox ACont;


    DraggableMaker draggableMaker = new DraggableMaker();
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       draggableMaker.makeDraggable(DragAsign);

    }
}
