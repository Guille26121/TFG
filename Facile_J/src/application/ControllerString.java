package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;


public class ControllerString implements Initializable{

    @FXML
    private AnchorPane String;
    @FXML
    private AnchorPane DragString;

    DraggableMaker draggableMaker = new DraggableMaker();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       draggableMaker.makeDraggable(DragString);

    }
}
