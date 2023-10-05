package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;

public class ControllerStrArgs implements Initializable {

	@FXML
    private AnchorPane StrArgs;
    @FXML
    private AnchorPane DragStrArgs;

    DraggableMaker draggableMaker = new DraggableMaker();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       draggableMaker.makeDraggable(DragStrArgs);

    }
}
