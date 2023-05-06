package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;


public class ControllerPlus implements Initializable{

    @FXML
    private AnchorPane Plus;
    @FXML
    private AnchorPane DragPlus;

    DraggableMaker draggableMaker = new DraggableMaker();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       draggableMaker.makeDraggable(DragPlus);

    }
}
