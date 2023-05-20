package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;

public class ControllerNEqual implements Initializable {

	@FXML
    private AnchorPane NEqual;
    @FXML
    private AnchorPane DragNEqual;

    DraggableMaker draggableMaker = new DraggableMaker();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       draggableMaker.makeDraggable(DragNEqual);

    }
}
